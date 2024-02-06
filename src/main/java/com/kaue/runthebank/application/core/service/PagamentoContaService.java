package com.kaue.runthebank.application.core.service;

import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.core.domain.Pagamento;
import com.kaue.runthebank.application.core.exception.ContaInativaException;
import com.kaue.runthebank.application.core.exception.NegocioException;
import com.kaue.runthebank.application.ports.in.conta.ConsultaContaClienteServicePort;
import com.kaue.runthebank.application.ports.in.movimento.MovimentoContaServicePort;
import com.kaue.runthebank.application.ports.in.pagamento.PagamentoContaServicePort;
import com.kaue.runthebank.application.ports.out.NotificacaoClienteEventPort;
import com.kaue.runthebank.application.ports.out.pagamento.PagamentoPort;

public class PagamentoContaService implements PagamentoContaServicePort {
    private final PagamentoPort pagamentoPort;
    private final ConsultaContaClienteServicePort consultaContaClienteServicePort;
    private final MovimentoContaServicePort movimentoContaServicePort;
    private final NotificacaoClienteEventPort notificacaoClienteEventPort;

    public PagamentoContaService(PagamentoPort pagamentoPort,
                                 ConsultaContaClienteServicePort consultaContaClienteServicePort,
                                 MovimentoContaServicePort movimentoContaServicePort,
                                 NotificacaoClienteEventPort notificacaoClienteEventPort) {
        this.pagamentoPort = pagamentoPort;
        this.consultaContaClienteServicePort = consultaContaClienteServicePort;
        this.movimentoContaServicePort = movimentoContaServicePort;
        this.notificacaoClienteEventPort = notificacaoClienteEventPort;
    }

    @Override
    public Pagamento transferirValor(Pagamento pagamento) {
        Conta contaRemetente = consultaContaClienteServicePort.buscar(pagamento.getContaRemetente().getId());
        Conta contaDestinatario = consultaContaClienteServicePort.buscar(pagamento.getContaDestinatario().getId());
        pagamento.setContaRemetente(contaRemetente);
        pagamento.setContaDestinatario(contaDestinatario);

        validarStatusContas(contaRemetente, contaDestinatario);
        validarSaldoContaRemetente(pagamento, contaRemetente);

        contaRemetente.debitar(pagamento.getValor());
        contaDestinatario.creditar(pagamento.getValor());

        Pagamento pagamentoRegistrado = pagamentoPort.registrarPagamento(pagamento);

        movimentoContaServicePort.gerarMovimentoDebito(pagamentoRegistrado, contaRemetente);
        movimentoContaServicePort.gerarMovimentoCredito(pagamentoRegistrado, contaDestinatario);

        notificacaoClienteEventPort.notificarPagamento(
                pagamento, contaRemetente.getCliente(), contaDestinatario.getCliente());
        return pagamentoRegistrado;
    }

    private static void validarSaldoContaRemetente(Pagamento pagamento, Conta contaRemetente) {
        if (contaRemetente.naoTemSaldoSuficiente(pagamento.getValor()))
            throw new NegocioException("Saldo insuficiente para realizar este pagamento");
    }

    private static void validarStatusContas(Conta contaRemetente, Conta contaDestinatario) {
        if (contaRemetente.estaInativo())
            throw new ContaInativaException(contaRemetente.getId());
        if (contaDestinatario.estaInativo())
            throw new ContaInativaException(contaDestinatario.getId());
    }
}
