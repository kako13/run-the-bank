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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas/{contaRemetenteId}/estornos")
public class ContaEstornoController {
    @Autowired
    private EstornoPagamentoContaServicePort estornoPagamentoContaServicePort;
    @Autowired
    private ConsultaPagamentoContaServicePort consultaPagamentoContaServicePort;
    @Autowired
    private ConsultaEstornoPagamentoContaServicePort consultaEstornoPagamentoContaServicePort;
    @Autowired
    private EstornoMapper estornoMapper;

    @PostMapping
    @JsonView(EstornoView.Detalhe.class)
    @ResponseStatus(HttpStatus.CREATED)
    public EstornoModel efetuar(@PathVariable Long contaRemetenteId,
                                @RequestBody @Valid EstornoInput estornoInput) {
        /*
         TODO: Considerando que já foi validado cliente e que a conta pertence a ele, *evoluir security*
          Do contrário será possível informar uma conta que não pertence ao cliente (usuário) em questão
        */
        Pagamento pagamentoParaEstornar = consultaPagamentoContaServicePort
                .buscarPorCodigoEConta(estornoInput.getCodigoPagamento(), contaRemetenteId);
        Estorno estorno = estornoPagamentoContaServicePort.estornarPagamento(pagamentoParaEstornar);
        return estornoMapper.toModel(estorno);
    }

    @GetMapping
    @JsonView(EstornoView.Resumo.class)
    public List<EstornoModel> listar(@PathVariable Long contaRemetenteId) {
        List<Estorno> estornos = consultaEstornoPagamentoContaServicePort.listar(contaRemetenteId);
        return estornoMapper.toCollectionModel(estornos);
    }

    @GetMapping("/{codigoEstorno}")
    @JsonView(EstornoView.Detalhe.class)
    public EstornoModel buscar(@PathVariable Long contaRemetenteId, @PathVariable String codigoEstorno) {
        Estorno estorno = consultaEstornoPagamentoContaServicePort.buscarPorCodigoEConta(codigoEstorno, contaRemetenteId);
        return estornoMapper.toModel(estorno);
    }
}
