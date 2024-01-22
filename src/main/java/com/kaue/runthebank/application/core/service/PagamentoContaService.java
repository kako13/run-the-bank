package com.kaue.runthebank.application.core.service;

import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.core.domain.Pagamento;
import com.kaue.runthebank.application.core.exception.ContaInativaException;
import com.kaue.runthebank.application.core.exception.NegocioException;
import com.kaue.runthebank.application.ports.in.pagamento.PagamentoContaServicePort;
import com.kaue.runthebank.application.ports.out.pagamento.PagamentoPort;

import java.util.List;

public class PagamentoContaService implements PagamentoContaServicePort {

    private final PagamentoPort pagamentoPort;

    public PagamentoContaService(PagamentoPort pagamentoPort) {
        this.pagamentoPort = pagamentoPort;
    }

    @Override
    public Pagamento transferirValor(Pagamento pagamento) {
        Conta contaRemetente = pagamento.getContaRemetente();
        Conta contaDestinatario = pagamento.getContaDestinataria();

        validarStatusContas(contaRemetente, contaDestinatario);
        // Validação do saldo da conta remetente
        validarSaldoContaRemetente(pagamento, contaRemetente);

        // Retirar saldo remetente e adicionar saldo ao destinatario
        contaRemetente.debitar(pagamento.getValor());
        contaDestinatario.creditar(pagamento.getValor());

        return pagamentoPort.cadastrarPagamento(pagamento);
    }

    @Override
    public List<Pagamento> listar(Long contaId) {
        return pagamentoPort.listar(contaId);
    }

    private static void validarSaldoContaRemetente(Pagamento pagamento, Conta contaRemetente) {
        if (contaRemetente.naoTemSaldoSuficiente(pagamento.getValor()))
            throw new NegocioException("Não existe saldo insuficiente para realizar este pagamento");
    }

    private static void validarStatusContas(Conta contaRemetente, Conta contaDestinatario) {
        if (contaRemetente.estaInativo())
            throw new ContaInativaException(contaRemetente.getId());
        if (contaDestinatario.estaInativo())
            throw new ContaInativaException(contaDestinatario.getId());
    }
}
