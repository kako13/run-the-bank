package com.kaue.runthebank.adapters.inboud.controller.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.kaue.runthebank.config.exception.EntidadeNaoEncontradaException;
import com.kaue.runthebank.config.exception.NegocioException;
import com.kaue.runthebank.config.exception.ValidacaoException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno no sistema. Tente novamente e " +
            "se o problema persistir, entre em contato com o administrador do sistema";

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        return handleValidationInternal(ex, ex.getBindingResult(), headers, (HttpStatus) status, request);
    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.DADOS_INVALIDOS;
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
        HttpStatus httpStatus = status;
        Object target = bindingResult.getTarget();
        List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError field) {
                        name = field.getField();
                    }
                    else {
                        assert target != null;
                        Class<?> aClass = target.getClass();
                        if (aClass.isAnnotationPresent(JsonTypeName.class)) {
                            JsonTypeName customName = aClass.getAnnotation(JsonTypeName.class);
                            name = customName.value();
                        }
                    }
                    return Problem.Object.builder()
                        .name(name)
                        .userMessage(message)
                        .build();
                })
                .toList();

        Problem problem = createProblemBuilder(httpStatus, problemType, detail)
                .userMessage(detail)
                .objects(problemObjects)
                .build();
        return handleExceptionInternal(ex, problem, headers, httpStatus, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Throwable throwable = ExceptionUtils.getRootCause(ex);
        if (throwable instanceof InvalidFormatException rootCause) {
            return handleInvalidFormat(rootCause, headers, status, request);
        } else if (throwable instanceof PropertyBindingException rootCause) {
            return handlePropertyBinding(rootCause, headers, status, request);
        }
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
        Problem problem = createProblemBuilder((HttpStatus) status, problemType, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        if (ex instanceof MethodArgumentTypeMismatchException e)
            return handleMethodArgumentTypeMismatch(e, headers, status, request);
        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String detail = String.format("O recurso '%s', que você tentou acessar, é inexistente.", ex.getRequestURL());
        Problem problem = createProblemBuilder((HttpStatus) status, problemType, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException rootCause,
                                                                    HttpHeaders headers, HttpStatusCode status,
                                                                    WebRequest request) {
        ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
        String detail = String.format(" O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido. " +
                "Corrija e informe um valor compatível com o tipo '%s'", rootCause.getPropertyName(),
                rootCause.getValue(), rootCause.getRequiredType().getSimpleName());

        Problem problem = createProblemBuilder((HttpStatus) status, problemType, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();
        return handleExceptionInternal(rootCause, problem, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex, HttpHeaders headers,
                                                         HttpStatusCode status, WebRequest request) {
        String path = joinPath(ex.getPath());
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detail = String.format("A propriedade '%s' não existe. " +
                "Corrija ou remova essa propriedade e tente novamente.", path);
        HttpStatus httpStatus = (HttpStatus) status;
        Problem problem = createProblemBuilder(httpStatus, problemType, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(ref -> {
                    if (ref.getFieldName() == null) {
                        return "[" + ref.getIndex() + "]";
                    } else {
                        return ref.getFieldName();
                    }
                })
                .collect(Collectors.joining("."));
    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, HttpHeaders headers,
                                                       HttpStatusCode status, WebRequest request) {
        String path = joinPath(ex.getPath());
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detail = String.format("A propriedade '%s' recebeu o valor '%s', que é de um tipo inválido. " +
                "Corrija e informe um valor compatível com o tipo '%s'", path, ex.getValue(), ex.getTargetType().getSimpleName());

        Problem problem = createProblemBuilder((HttpStatus) status, problemType, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String detail = ex.getMessage();
        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
        String detail = ex.getMessage();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

//    @ExceptionHandler(EntidadeEmUsoException.class)
//    public ResponseEntity<?> handleEntidadeEmUso(EntidadeEmUsoException ex, WebRequest request) {
//        HttpStatus status = HttpStatus.CONFLICT;
//        ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
//        String detail = ex.getMessage();
//        Problem problem = createProblemBuilder(status, problemType, detail)
//                .userMessage(detail)
//                .build();
//        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
        String detail = MSG_ERRO_GENERICA_USUARIO_FINAL;
        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<Object> handleValidationInternal(ValidacaoException ex, WebRequest request) {
        return handleValidationInternal(ex, ex.getBindingResult(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        HttpStatus status = (HttpStatus) statusCode;

        if (body == null)
            body = Problem.builder()
                    .timeStamp(OffsetDateTime.now())
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                    .build();
        else if (body instanceof String stringBody)
            body = Problem.builder()
                    .timeStamp(OffsetDateTime.now())
                    .title(stringBody)
                    .status(status.value())
                    .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                    .build();
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
        return Problem.builder()
                .timeStamp(OffsetDateTime.now())
                .title(problemType.getTitle())
                .status(status.value())
                .type(problemType.getUri())
                .detail(detail);
    }
}
