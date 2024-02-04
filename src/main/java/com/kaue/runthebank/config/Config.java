package com.kaue.runthebank.config;

import com.kaue.runthebank.adapters.outbound.*;
import com.kaue.runthebank.application.core.service.*;
import com.kaue.runthebank.application.ports.in.cliente.CadastroClienteServicePort;
import com.kaue.runthebank.application.ports.in.cliente.ConsultaClienteServicePort;
import com.kaue.runthebank.application.ports.in.conta.AberturaContaClienteServicePort;
import com.kaue.runthebank.application.ports.in.conta.ConsultaContaClienteServicePort;
import com.kaue.runthebank.application.ports.in.estorno.ConsultaEstornoPagamentoContaServicePort;
import com.kaue.runthebank.application.ports.in.estorno.EstornoPagamentoContaServicePort;
import com.kaue.runthebank.application.ports.in.movimento.MovimentoContaServicePort;
import com.kaue.runthebank.application.ports.in.pagamento.ConsultaPagamentoContaServicePort;
import com.kaue.runthebank.application.ports.in.pagamento.PagamentoContaServicePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public CadastroClienteServicePort cadastroClienteService(CadastroClienteDataBaseAdapter cadastroClienteDataBaseAdapter,
                                                             ConsultaClienteServicePort consultaClienteServicePort) {
        return new CadastroClienteService(cadastroClienteDataBaseAdapter, consultaClienteServicePort);
    }
    @Bean
    public ConsultaClienteServicePort consultaClienteService(ConsultaClienteDataBaseAdapter consultaClienteDataBaseAdapter) {
        return new ConsultaClienteService(consultaClienteDataBaseAdapter);
    }
    @Bean
    public AberturaContaClienteServicePort aberturaContaService(
            CadastroContaDataBaseAdapter cadastroContaDataBaseAdapter,
            ConsultaClienteServicePort consultaClienteServicePort) {
        return new AberturaContaClienteService(cadastroContaDataBaseAdapter, consultaClienteServicePort);
    }
    @Bean
    public ConsultaContaClienteServicePort consultaContaService(
            ConsultaContaDataBaseAdapter consultaContaDataBaseAdapter,
            ConsultaClienteServicePort consultaClienteServicePort) {
        return new ConsultaContaClienteService(consultaContaDataBaseAdapter, consultaClienteServicePort);
    }
    @Bean
    public PagamentoContaServicePort pagamentoContaService(PagamentoDataBaseAdapter pagamentoDataBaseAdapter,
                                                           ConsultaContaClienteServicePort consultaContaClienteServicePort,
                                                           MovimentoContaServicePort movimentoContaServicePort) {
        return new PagamentoContaService(pagamentoDataBaseAdapter, consultaContaClienteServicePort, movimentoContaServicePort);
    }
    @Bean
    public ConsultaPagamentoContaServicePort consultaPagamentoContaService(ConsultaPagamentoDataBaseAdapter
                                                                                    consultaPagamentoDataBaseAdapter) {
        return new ConsultaPagamentoContaService(consultaPagamentoDataBaseAdapter);
    }
    @Bean
    public EstornoPagamentoContaServicePort estornoPagamentoContaService(
                                        EstornoPagamentoDataBaseAdapter estornoPagamentoDataBaseAdapter,
                                        MovimentoContaServicePort movimentoContaServicePort) {
        return new EstornoPagamentoContaService(estornoPagamentoDataBaseAdapter, movimentoContaServicePort);
    }
    @Bean
    public ConsultaEstornoPagamentoContaServicePort consultaEstornoPagamentoContaService(
            ConsultaEstornoPagamentoDataBaseAdapter consultaEstornoPagamentoDataBaseAdapter) {
        return new ConsultaEstornoPagamentoContaService(consultaEstornoPagamentoDataBaseAdapter);
    }
    @Bean
    public MovimentoContaServicePort movimentoService(MovimentoContaContaDataBaseAdapter movimentoContaDataBaseAdapter) {
        return new MovimentoContaContaService(movimentoContaDataBaseAdapter);
    }
}
