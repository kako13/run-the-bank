package com.kaue.runthebank.adapters.outbound.rest;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class CamelConfig {
    @Bean
    public RouteBuilder notificationRoute(@Value("${external.notification.service}") String urlNotificacao) {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:sendNotification")
                        .log("Mensagem recebida pela rota do Camel: ${body}")
                .to("seda:asyncNotification");
            }
        };
    }

    @Bean
    public RouteBuilder asyncNotificationRoute(@Value("${external.notification.service}") String urlNotificacao) {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("seda:asyncNotification")
                        .log("Mensagem que será encaminhada ao serviço externo: ${body}")
                        .onException(Exception.class)
                            .maximumRedeliveries(2)
                            .redeliveryDelay(1000)
                            .retryAttemptedLogLevel(LoggingLevel.ERROR)
                            .end()
                        .setHeader("CamelHttpMethod", constant("POST"))
                        .setHeader("Content-Type", constant("application/json"))
                .toF("ahc:%s?async=true", urlNotificacao) // Chama o serviço externo de forma assíncrona
                        .log("Notificação enviada ao serviço externo\n ${body}");
            }
        };
    }
}