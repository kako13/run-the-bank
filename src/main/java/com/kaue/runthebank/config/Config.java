package com.kaue.runthebank.config;

import com.kaue.runthebank.adapters.outbound.SalvarClienteDataBaseAdapter;
import com.kaue.runthebank.application.core.service.CadastrarClienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public CadastrarClienteService cadastroClienteService(SalvarClienteDataBaseAdapter salvarClienteDataBaseAdapter) {
        return new CadastrarClienteService(salvarClienteDataBaseAdapter);
    }
}
