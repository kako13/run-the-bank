package com.kaue.runthebank.adapters.inboud.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kaue.runthebank.adapters.inboud.assembler.estorno.EstornoMapper;
import com.kaue.runthebank.adapters.inboud.controller.request.estorno.EstornoInput;
import com.kaue.runthebank.adapters.inboud.controller.request.estorno.EstornoModel;
import com.kaue.runthebank.adapters.inboud.controller.request.view.EstornoView;
import com.kaue.runthebank.application.core.domain.Estorno;
import com.kaue.runthebank.application.core.domain.Pagamento;
import com.kaue.runthebank.application.ports.in.estorno.ConsultaEstornoPagamentoContaServicePort;
import com.kaue.runthebank.application.ports.in.estorno.EstornoPagamentoContaServicePort;
import com.kaue.runthebank.application.ports.in.pagamento.ConsultaPagamentoContaServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "4. Estornos")
@Slf4j
@RestController
@RequestMapping(value = "/contas/{contaRemetenteId}/estornos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContaEstornoController {
    private static final String EXEMPLO_ESTORNO = "{\"codigoPagamento\": \"preencher com código de pagamento\"}";
    @Autowired
    private EstornoPagamentoContaServicePort estornoPagamentoContaServicePort;
    @Autowired
    private ConsultaPagamentoContaServicePort consultaPagamentoContaServicePort;
    @Autowired
    private ConsultaEstornoPagamentoContaServicePort consultaEstornoPagamentoContaServicePort;
    @Autowired
    private EstornoMapper estornoMapper;

    @Operation(summary = "Realiza o estorno de um pagamento")
    @ApiResponse(responseCode = "201", description = "Estorno realizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos/Violação de regra de negócio/Mensagem incompreensível")
    @ApiResponse(responseCode = "404", description = "Recurso não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de Sistema")
    @PostMapping
    @JsonView(EstornoView.Detalhe.class)
    @ResponseStatus(HttpStatus.CREATED)
    public EstornoModel efetuar(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = EXEMPLO_ESTORNO)}))
                              @RequestBody @Valid EstornoInput estornoInput, @PathVariable Long contaRemetenteId) {
        /*
         TODO: Considerando que já foi validado cliente e que a conta pertence a ele, *evoluir security*
          Do contrário será possível informar uma conta que não pertence ao cliente (usuário) em questão
        */
        log.info("Estornando pagamento");
        Pagamento pagamentoParaEstornar = consultaPagamentoContaServicePort
                .buscarPorCodigoEConta(estornoInput.getCodigoPagamento(), contaRemetenteId);
        Estorno estorno = estornoPagamentoContaServicePort.estornarPagamento(pagamentoParaEstornar);
        return estornoMapper.toModel(estorno);
    }

    @Operation(summary = "Lista a relação de estornos realizados por uma conta")
    @ApiResponse(responseCode = "200", description = "Listagem de estornos realizada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos/Violação de regra de negócio/Mensagem incompreensível")
    @ApiResponse(responseCode = "404", description = "Recurso não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de Sistema")
    @GetMapping
    @JsonView(EstornoView.Resumo.class)
    public List<EstornoModel> listar(@PathVariable Long contaRemetenteId) {
        log.info("Listando estornos");
        List<Estorno> estornos = consultaEstornoPagamentoContaServicePort.listar(contaRemetenteId);
        return estornoMapper.toCollectionModel(estornos);
    }

    @Operation(summary = "Consulta um estorno realizado por uma conta")
    @ApiResponse(responseCode = "200", description = "Consulta de estorno realizada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos/Violação de regra de negócio/Mensagem incompreensível")
    @ApiResponse(responseCode = "404", description = "Recurso não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de Sistema")
    @GetMapping("/{codigoEstorno}")
    @JsonView(EstornoView.Detalhe.class)
    public EstornoModel buscar(@PathVariable Long contaRemetenteId, @PathVariable String codigoEstorno) {
        log.info("Buscando estorno");
        Estorno estorno = consultaEstornoPagamentoContaServicePort.buscarPorCodigoEConta(codigoEstorno, contaRemetenteId);
        return estornoMapper.toModel(estorno);
    }
}
