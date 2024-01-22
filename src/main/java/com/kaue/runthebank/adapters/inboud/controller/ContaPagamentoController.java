package com.kaue.runthebank.adapters.inboud.controller;

import com.kaue.runthebank.adapters.inboud.assembler.pagamento.PagamentoMapper;
import com.kaue.runthebank.adapters.inboud.controller.request.pagamento.PagamentoInput;
import com.kaue.runthebank.adapters.inboud.controller.request.pagamento.PagamentoModel;
import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.core.domain.Pagamento;
import com.kaue.runthebank.application.core.exception.NegocioException;
import com.kaue.runthebank.application.ports.in.conta.ConsultaContaClienteServicePort;
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
    private ConsultaContaClienteServicePort consultaContaClienteServicePort;
    @Autowired
    private PagamentoMapper pagamentoMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PagamentoModel efetuar(@PathVariable Long contaRemetenteId,
                                        @RequestBody @Valid PagamentoInput pagamentoInput) {

        if (!Objects.equals(contaRemetenteId, pagamentoInput.getContaRemetenteId()))
            throw new NegocioException("A conta informada no path não condiz com a que foi informada no pagamento");

        // TODO - Considerando que já foi validado cliente e que a conta pertence a ele, *evoluir security*
        // TODO - Do contrário será possível informar uma conta que não pertence ao cliente (usuário) em questão
        Long contaDestinatarioId = pagamentoInput.getContaDestinatarioId();
        Conta contaRemetente = consultaContaClienteServicePort.buscar(contaRemetenteId);
        Conta contaDestinatario = consultaContaClienteServicePort.buscar(contaDestinatarioId);

        Pagamento pagamento = pagamentoMapper.toDomainObject(pagamentoInput);
        pagamento.setContaDestinataria(contaDestinatario);
        pagamento.setContaRemetente(contaRemetente);
        return pagamentoMapper.toModel(pagamentoContaServicePort.transferirValor(pagamento));
    }
    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<PagamentoModel> listar(@PathVariable Long contaRemetenteId) {
        return pagamentoMapper.toCollectionModel(pagamentoContaServicePort.listar(contaRemetenteId));
    }
}
