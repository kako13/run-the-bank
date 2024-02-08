package com.kaue.runthebank.adapters.outbound.rest;

import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.core.exception.NegocioException;
import com.kaue.runthebank.application.ports.out.NotificacaoClientePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Component
public class NotificacaoClienteRestAdapter implements NotificacaoClientePort {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${external.notification.service}")
    private String urlNotificacao;
    Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void notificarCliente(Cliente cliente, String mensagem) {
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