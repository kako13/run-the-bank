package com.kaue.runthebank.adapters.inboud.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kaue.runthebank.adapters.inboud.assembler.pagamento.PagamentoMapper;
import com.kaue.runthebank.adapters.inboud.controller.request.pagamento.PagamentoInput;
import com.kaue.runthebank.adapters.inboud.controller.request.pagamento.PagamentoModel;
import com.kaue.runthebank.adapters.inboud.controller.request.view.PagamentoView;
import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.core.domain.Pagamento;
import com.kaue.runthebank.application.core.exception.NegocioException;
import com.kaue.runthebank.application.ports.in.conta.ConsultaContaClienteServicePort;
import com.kaue.runthebank.application.ports.in.pagamento.ConsultaPagamentoContaServicePort;
import com.kaue.runthebank.application.ports.in.pagamento.PagamentoContaServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.kaue.runthebank.config.openapi.ContaPayloadsExemplos.EXEMPLO_CONTA_NAO_ENCONTRADA;
import static com.kaue.runthebank.config.openapi.PagamentoPayloadsExemplos.*;

@Tag(name = "3. Pagamentos")
@Slf4j
@RestController
@RequestMapping(value = "/contas/{contaRemetenteId}/pagamentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContaPagamentoController {
    @Autowired
    private PagamentoContaServicePort pagamentoContaServicePort;
    @Autowired
    private ConsultaPagamentoContaServicePort consultaPagamentoContaServicePort;
    @Autowired
    private ConsultaContaClienteServicePort consultaContaClienteServicePort;
    @Autowired
    private PagamentoMapper pagamentoMapper;

    @Operation(summary = "Realiza um pagamento (transferência) de uma conta para outra")
    @ApiResponse(responseCode = "201", description = "Pagamento realizado com sucesso",
            content = @Content(
                    schema = @Schema(implementation = PagamentoModel.class),
                    examples = @ExampleObject(EXEMPLO_PAGAMENTO_CADASTRADO)
            ))
    @ApiResponse(responseCode = "400", description = "Dados inválidos/Violação de regra de negócio/Mensagem incompreensível",
            content = @Content(
                    schema = @Schema(implementation = PagamentoModel.class),
                    examples = @ExampleObject(EXEMPLO_PAGAMENTO_DADOS_INVALIDOS)
            ))
    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
            content = @Content(
                    schema = @Schema(implementation = PagamentoModel.class),
                    examples = @ExampleObject(EXEMPLO_CONTA_NAO_ENCONTRADA)
            ))
    @ApiResponse(responseCode = "500", description = "Erro de Sistema",
            content = @Content(
                    schema = @Schema(implementation = PagamentoModel.class),
                    examples = @ExampleObject(EXEMPLO_ERRO_DE_SISTEMA)
            ))
    @PostMapping
    @JsonView(PagamentoView.Detalhe.class)
    @ResponseStatus(HttpStatus.CREATED)
    public PagamentoModel efetuar(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = "application/json", examples = {@ExampleObject(EXEMPLO_PAGAMENTO_INPUT)}))
                               @RequestBody @Valid PagamentoInput pagamentoInput, @PathVariable Long contaRemetenteId) {
        log.info("Realizando pagamento");
        if (!Objects.equals(contaRemetenteId, pagamentoInput.getContaRemetenteId()))
            throw new NegocioException("A conta informada no path não condiz com a conta remetente informada no pagamento");
        /*
         TODO: Considerando que já foi validado cliente e que a conta pertence a ele, *evoluir security*
          Do contrário será possível informar uma conta que não pertence ao cliente (usuário) em questão
        */
        Long contaDestinatarioId = pagamentoInput.getContaDestinatarioId();

        Pagamento pagamento = pagamentoMapper.toDomainObject(pagamentoInput);
        pagamento.setContaDestinatario(Conta.builder().id(contaDestinatarioId).build());
        pagamento.setContaRemetente(Conta.builder().id(contaRemetenteId).build());
        return pagamentoMapper.toModel(pagamentoContaServicePort.transferirValor(pagamento));
    }

    @Operation(summary = "Lista a relação de pagamentos realizados por uma conta")
    @ApiResponse(responseCode = "200", description = "Listagem de pagamentos realizada com sucesso",
            content = @Content(
                    schema = @Schema(implementation = PagamentoModel.class),
                    examples = @ExampleObject(EXEMPLO_PAGAMENTO_LISTA)
            ))
    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
            content = @Content(
                    schema = @Schema(implementation = PagamentoModel.class),
                    examples = @ExampleObject(EXEMPLO_CONTA_NAO_ENCONTRADA)
            ))
    @ApiResponse(responseCode = "500", description = "Erro de Sistema",
            content = @Content(
                    schema = @Schema(implementation = PagamentoModel.class),
                    examples = @ExampleObject(EXEMPLO_ERRO_DE_SISTEMA)
            ))
    @GetMapping
    @JsonView(PagamentoView.Resumo.class)
    public List<PagamentoModel> listar(@PathVariable Long contaRemetenteId) {
        log.info("Listando pagamentos");
        return pagamentoMapper.toCollectionModel(consultaPagamentoContaServicePort.listar(contaRemetenteId));
    }

    @Operation(summary = "Consulta um pagamento realizado por uma conta")
    @ApiResponse(responseCode = "200", description = "Consulta de pagamento realizada com sucesso",
            content = @Content(
                    schema = @Schema(implementation = PagamentoModel.class),
                    examples = @ExampleObject(EXEMPLO_PAGAMENTO_CONSULTA)
            ))
    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
            content = @Content(
                    schema = @Schema(implementation = PagamentoModel.class),
                    examples = @ExampleObject(EXEMPLO_PAGAMENTO_NAO_ENCONTRADO)
            ))
    @ApiResponse(responseCode = "500", description = "Erro de Sistema",
            content = @Content(
                    schema = @Schema(implementation = PagamentoModel.class),
                    examples = @ExampleObject(EXEMPLO_ERRO_DE_SISTEMA)
            ))
    @GetMapping("/{codigoPagamento}")
    @JsonView(PagamentoView.Detalhe.class)
    public PagamentoModel buscar(@PathVariable Long contaRemetenteId, @PathVariable String codigoPagamento) {
        log.info("Buscando pagamento");
        Pagamento buscar = consultaPagamentoContaServicePort.buscarPorCodigoEConta(codigoPagamento, contaRemetenteId);
        return pagamentoMapper.toModel(buscar);
    }
}
