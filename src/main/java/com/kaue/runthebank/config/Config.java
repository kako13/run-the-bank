package com.kaue.runthebank.config;

import com.kaue.runthebank.adapters.outbound.CadastroClienteDataBaseAdapter;
import com.kaue.runthebank.adapters.outbound.ConsultaClienteDataBaseAdapter;
import com.kaue.runthebank.application.core.service.CadastroClienteService;
import com.kaue.runthebank.application.core.service.ConsultaClienteService;
import com.kaue.runthebank.application.ports.in.cliente.CadastroClienteServicePort;
import com.kaue.runthebank.application.ports.in.cliente.ConsultaClienteServicePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public CadastroClienteServicePort cadastroClienteService(CadastroClienteDataBaseAdapter cadastroClienteDataBaseAdapter) {
        return new CadastroClienteService(cadastroClienteDataBaseAdapter);
    }
    @Bean
    public ConsultaClienteServicePort consultaClienteService(ConsultaClienteDataBaseAdapter consultaClienteDataBaseAdapter) {
        return new ConsultaClienteService(consultaClienteDataBaseAdapter);
    }
//    @Bean
//    public AberturaContaClienteServicePort aberturaContaService(ContaDataBaseAdapter contaDataBaseAdapter) {
//        return new AberturaContaClienteService(contaDataBaseAdapter);
//    }
}
