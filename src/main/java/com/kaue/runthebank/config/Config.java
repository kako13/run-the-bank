package com.kaue.runthebank.config;

import com.kaue.runthebank.adapters.outbound.*;
import com.kaue.runthebank.application.core.service.*;
import com.kaue.runthebank.application.ports.in.cliente.CadastroClienteServicePort;
import com.kaue.runthebank.application.ports.in.cliente.ConsultaClienteServicePort;
import com.kaue.runthebank.application.ports.in.conta.AberturaContaClienteServicePort;
import com.kaue.runthebank.application.ports.in.conta.ConsultaContaClienteServicePort;
import com.kaue.runthebank.application.ports.in.pagamento.PagamentoContaServicePort;
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
    @Bean
    public AberturaContaClienteServicePort aberturaContaService(CadastroContaDataBaseAdapter cadastroContaDataBaseAdapter) {
        return new AberturaContaClienteService(cadastroContaDataBaseAdapter);
    }
    @Bean
    public ConsultaContaClienteServicePort consultaContaService(ConsultaContaDataBaseAdapter consultaContaDataBaseAdapter) {
        return new ConsultaContaClienteService(consultaContaDataBaseAdapter);
    }
    @Bean
    public PagamentoContaServicePort pagamentoContaService(PagamentoDataBaseAdapter pagamentoDataBaseAdapter) {
        return new PagamentoContaService(pagamentoDataBaseAdapter);
    }
}
