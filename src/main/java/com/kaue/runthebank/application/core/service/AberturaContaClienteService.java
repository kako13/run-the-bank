package com.kaue.runthebank.application.core.service;

import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.ports.in.conta.AberturaContaClienteServicePort;
import com.kaue.runthebank.application.ports.out.conta.CadastroContaPort;

public class AberturaContaClienteService implements AberturaContaClienteServicePort {

    private final CadastroContaPort cadastroContaPort;

    public AberturaContaClienteService(CadastroContaPort cadastroContaPort) {
        this.cadastroContaPort = cadastroContaPort;
    }

    @Override
    public Conta abrirConta(Long clienteId, Conta conta) {
        return cadastroContaPort.gerarConta(clienteId, conta);
    }
}
