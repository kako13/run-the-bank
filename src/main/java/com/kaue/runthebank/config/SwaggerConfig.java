package com.kaue.runthebank.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi clientesGroup() {
        return GroupedOpenApi.builder()
                .group("1 - Clientes")
                .pathsToMatch(
                        "/clientes",
                        "/clientes/{clienteId}"
                )
                .build();
    }

    @Bean
    public GroupedOpenApi contasGroup() {
        return GroupedOpenApi.builder()
                .group("2 - Contas")
                .pathsToMatch(
                        "/clientes/{clienteId}/contas",
                        "/clientes/{clienteId}/contas/{contaId}"
                )
                .build();
    }

    @Bean
    public GroupedOpenApi pagamentosGroup() {
        return GroupedOpenApi.builder()
                .group("3 - Pagamentos")
                .pathsToMatch(
                        "/contas/{contaRemetenteId}/pagamentos",
                        "/contas/{contaRemetenteId}/pagamentos/{codigoPagamento}"
                )
                .build();
    }

    @Bean
    public GroupedOpenApi estornosGroup() {
        return GroupedOpenApi.builder()
                .group("4 - Estornos")
                .pathsToMatch(
                        "/contas/{contaRemetenteId}/estornos",
                        "/contas/{contaRemetenteId}/estornos/{codigoEstorno}"
                )
                .build();
    }

    @Bean
    public GroupedOpenApi todosGroup() {
        return GroupedOpenApi.builder()
                .group("5 - Todos")
                .pathsToMatch(
                        "/**"
                )
                .build();
    }
}
