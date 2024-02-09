package com.kaue.runthebank.application.core.service;

import com.kaue.runthebank.application.core.domain.*;
import com.kaue.runthebank.application.core.exception.ContaInativaException;
import com.kaue.runthebank.application.core.exception.NegocioException;
import com.kaue.runthebank.application.ports.in.NotificacaoClienteServicePort;
import com.kaue.runthebank.application.ports.in.conta.ConsultaContaClienteServicePort;
import com.kaue.runthebank.application.ports.in.estorno.EstornoPagamentoContaServicePort;
import com.kaue.runthebank.application.ports.in.movimento.MovimentoContaServicePort;
import com.kaue.runthebank.application.ports.out.estorno.EstornoPagamentoPort;

public class EstornoPagamentoContaService implements EstornoPagamentoContaServicePort {
    private final EstornoPagamentoPort estornoPagamentoPort;
    private final MovimentoContaServicePort movimentoContaServicePort;
    private final ConsultaContaClienteServicePort consultaContaClienteServicePort;
    private final NotificacaoClienteServicePort notificacaoClienteServicePort;

    public EstornoPagamentoContaService(EstornoPagamentoPort estornoPagamentoPort,
                                        MovimentoContaServicePort movimentoContaServicePort,
                                        ConsultaContaClienteServicePort consultaContaClienteServicePort,
                                        NotificacaoClienteServicePort notificacaoClienteServicePort) {
        this.estornoPagamentoPort = estornoPagamentoPort;
        this.movimentoContaServicePort = movimentoContaServicePort;
        this.notificacaoClienteServicePort = notificacaoClienteServicePort;
        this.consultaContaClienteServicePort = consultaContaClienteServicePort;
    }

    private static void validarStatusContas(Conta contaRemetente, Conta contaDestinatario) {
        if (contaRemetente.estaInativo())
            throw new ContaInativaException(contaRemetente.getId());
        if (contaDestinatario.estaInativo())
            throw new ContaInativaException(contaDestinatario.getId());
    }

    @Override
    public Estorno estornarPagamento(Pagamento pagamento) {
        validaCondicaoParaEstorno(pagamento);

        Conta contaRemetente = consultaContaClienteServicePort.buscar(pagamento.getContaRemetente().getId());
        Conta contaDestinatario = consultaContaClienteServicePort.buscar(pagamento.getContaDestinatario().getId());
        pagamento.setContaRemetente(contaRemetente);
        pagamento.setContaDestinatario(contaDestinatario);

        validarStatusContas(contaRemetente, contaDestinatario);

        contaDestinatario.debitar(pagamento.getValor());
        contaRemetente.creditar(pagamento.getValor());

        Estorno estornoRegistrado = estornoPagamentoPort.registrarEstorno(Estorno.builder()
                .valor(pagamento.getValor())
                .contaDestinatario(pagamento.getContaDestinatario())
                .contaRemetente(pagamento.getContaRemetente())
                .pagamento(pagamento)
                .build());

        movimentoContaServicePort.gerarMovimentoDebito(estornoRegistrado.getPagamento(), contaDestinatario);
        movimentoContaServicePort.gerarMovimentoCredito(estornoRegistrado.getPagamento(), contaRemetente);

        notificacaoClienteServicePort.notificarEstorno(
                estornoRegistrado, contaRemetente.getCliente(), contaDestinatario.getCliente());
        return estornoRegistrado;
    }

    private void validaCondicaoParaEstorno(Pagamento pagamento) {
        if (Boolean.TRUE.equals(pagamento.foiEstornado()))
            throw new NegocioException("Já foi realizado um estorno para este pagamento.");
    }
}
