package com.kaue.runthebank.application.core.service;

import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.core.domain.Estorno;
import com.kaue.runthebank.application.core.domain.Pagamento;
import com.kaue.runthebank.application.ports.in.NotificacaoClienteServicePort;
import com.kaue.runthebank.application.ports.out.NotificacaoClientePort;

public class NotificacaoClienteService implements NotificacaoClienteServicePort {
    public static final String MSG_REMETENTE_PAGAMENTO = "Pagamento realizado no valor de'R$ %.2f' para '%s' na conta" +
            " '%d' e agência '%s'.";
    public static final String MSG_DESTINATARIO_PAGAMENTO = "Você recebeu um pagamento de '%s'.";
    public static final String MSG_REMETENTE_ESTORNO = "Estorno do pagamento '%d' de valor de'R$ %.2f' foi realizado.";
    public static final String MSG_DESTINATARIO_ESTORNO = "O pagamento recebido de '%s' foi desfeito.";
    private final NotificacaoClientePort notificacaoClientePort;

    public NotificacaoClienteService(NotificacaoClientePort notificacaoClientePort) {
        this.notificacaoClientePort = notificacaoClientePort;
    }

    @Override
    public void notificarPagamento(Pagamento pagamento, Cliente clienteRemetente, Cliente clienteDestinatario) {
        notificacaoClientePort.notificarCliente(clienteRemetente,
                String.format(MSG_REMETENTE_PAGAMENTO,
                        pagamento.getValor(),
                        clienteDestinatario.getNome(),
                        pagamento.getContaDestinatario().getId(),
                        pagamento.getContaDestinatario().getAgencia()));
        notificacaoClientePort.notificarCliente(clienteDestinatario,
                String.format(MSG_DESTINATARIO_PAGAMENTO, clienteRemetente.getNome()));
    }

    @Override
    public void notificarEstorno(Estorno estorno, Cliente clienteRemetente, Cliente clienteDestinatario) {
        notificacaoClientePort.notificarCliente(clienteRemetente,
                String.format(MSG_REMETENTE_ESTORNO,
                        estorno.getPagamento().getId(),
                        estorno.getValor()));
        notificacaoClientePort.notificarCliente(clienteDestinatario,
                String.format(MSG_DESTINATARIO_ESTORNO, clienteRemetente.getNome()));
    }
}
