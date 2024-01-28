package com.kaue.runthebank.application.core.service;

import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.ports.in.cliente.ConsultaClienteServicePort;
import com.kaue.runthebank.application.ports.in.conta.AberturaContaClienteServicePort;
import com.kaue.runthebank.application.ports.out.conta.CadastroContaPort;

public class AberturaContaClienteService implements AberturaContaClienteServicePort {
    private final CadastroContaPort cadastroContaPort;
    private final ConsultaClienteServicePort consultaClienteServicePort;

    public AberturaContaClienteService(CadastroContaPort cadastroContaPort,
                                       ConsultaClienteServicePort consultaClienteServicePort) {
        this.cadastroContaPort = cadastroContaPort;
        this.consultaClienteServicePort = consultaClienteServicePort;
    }

    @Override
    public Conta abrirConta(Long clienteId, Conta conta) {
        Cliente cliente = consultaClienteServicePort.buscar(clienteId);
        return cadastroContaPort.gerarConta(cliente, conta);
    }
}
