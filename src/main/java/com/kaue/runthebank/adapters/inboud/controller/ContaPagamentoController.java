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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/contas/{contaRemetenteId}/pagamentos")
public class ContaPagamentoController {
    @Autowired
    private PagamentoContaServicePort pagamentoContaServicePort;
    @Autowired
    private ConsultaPagamentoContaServicePort consultaPagamentoContaServicePort;
    @Autowired
    private ConsultaContaClienteServicePort consultaContaClienteServicePort;
    @Autowired
    private PagamentoMapper pagamentoMapper;

    @PostMapping
    @JsonView(PagamentoView.Resumo.class)
    @ResponseStatus(HttpStatus.CREATED)
    public PagamentoModel efetuar(@PathVariable Long contaRemetenteId,
                                        @RequestBody @Valid PagamentoInput pagamentoInput) {

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
    @GetMapping
    @JsonView(PagamentoView.Listagem.class)
    public List<PagamentoModel> listar(@PathVariable Long contaRemetenteId) {
        return pagamentoMapper.toCollectionModel(consultaPagamentoContaServicePort.listar(contaRemetenteId));
    }
    @GetMapping("/{codigoPagamento}")
    @JsonView(PagamentoView.Resumo.class)
    public PagamentoModel buscar(@PathVariable Long contaRemetenteId, @PathVariable String codigoPagamento) {
        consultaContaClienteServicePort.buscar(contaRemetenteId);
        Pagamento buscar = consultaPagamentoContaServicePort.buscarPorCodigoEConta(codigoPagamento, contaRemetenteId);
        return pagamentoMapper.toModel(buscar);
    }
}
