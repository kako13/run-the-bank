package com.kaue.runthebank.adapters.outbound.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.ports.out.NotificacaoClientePort;
import lombok.SneakyThrows;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoClienteRestAdapter implements NotificacaoClientePort {
    @Autowired
    private ProducerTemplate producerTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void notificarCliente(Cliente cliente, String mensagem) {
        NotificacaoSMSModel notificacaoSMSRecord = new NotificacaoSMSModel(cliente.getCelular(), mensagem);
        String jsonNotificacao = objectMapper.writeValueAsString(notificacaoSMSRecord);
        producerTemplate.send("direct:sendNotification", exchange -> exchange.getMessage().setBody(jsonNotificacao));
    }
}