package com.kaue.runthebank.adapters.outbound.rest;

import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.core.domain.Estorno;
import com.kaue.runthebank.application.core.domain.Pagamento;
import com.kaue.runthebank.application.core.exception.NegocioException;
import com.kaue.runthebank.application.ports.out.NotificacaoClienteEventPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Component
public class NotificacaoRestAdapter  implements NotificacaoClienteEventPort {
    public static final String MSG_REMETENTE_PAGAMENTO = "Pagamento realizado no valor de'R$ %.2f' para '%s' na conta" +
            " '%d' e agência '%s'.";
    public static final String MSG_DESTINATARIO_PAGAMENTO = "Você recebeu um pagamento de '%s'.";
    public static final String MSG_REMETENTE_ESTORNO = "Estorno do pagamento '%d' de valor de'R$ %.2f' foi realizado.";
    public static final String MSG_DESTINATARIO_ESTORNO = "O pagamento recebido de '%s' foi desfeito.";

    @Autowired
    private RestTemplate restTemplate;
    @Value("${external.notification.service}")
    private String urlNotificacao;
    Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void notificarPagamento(Pagamento pagamento, Cliente clienteRemetente, Cliente clienteDestinatario) {
        Conta contaDestinatario = pagamento.getContaDestinatario();
        notificarPagamento(clienteRemetente, String.format(MSG_REMETENTE_PAGAMENTO, pagamento.getValor(),
                clienteDestinatario.getNome(), contaDestinatario.getId(), contaDestinatario.getAgencia()));
        notificarPagamento(clienteDestinatario, String.format(MSG_DESTINATARIO_PAGAMENTO, clienteRemetente.getNome()));
    }

    @Override
    public void notificarEstorno(Estorno estorno, Cliente clienteRemetente, Cliente clienteDestinatario) {
        notificarPagamento(clienteRemetente, String.format(MSG_REMETENTE_ESTORNO, estorno.getPagamento().getId(),
                estorno.getValor()));
        notificarPagamento(clienteDestinatario, String.format(MSG_DESTINATARIO_ESTORNO, clienteRemetente.getNome()));
    }

    public void notificarPagamento(Cliente cliente, String mensagem) {
        try {
            NotificacaoSMSModel notificacaoSMSModel = new NotificacaoSMSModel(cliente.getCelular(), mensagem);

            ResponseEntity<String> notificacaoResponse =
                    restTemplate.postForEntity(urlNotificacao, notificacaoSMSModel, String.class);

            if (!notificacaoResponse.getStatusCode().is2xxSuccessful()) {
                String msgFormatada = String.format("Não foi possível notificar o cliente '%s'", cliente.getId());
                logger.info(msgFormatada);
                throw new NegocioException(msgFormatada);
            }

            logger.info("Cliente notificado\n" + notificacaoResponse.getBody());

        } catch (RestClientException e) {
            throw new NegocioException("Serviço de notificação está indisponível");
        }
    }
}