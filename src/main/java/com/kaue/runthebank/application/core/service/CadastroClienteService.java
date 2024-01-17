package com.kaue.runthebank.application.core.service;

import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.ports.in.cliente.CadastroClienteServicePort;
import com.kaue.runthebank.application.ports.out.cliente.CadastroClientePort;

public class CadastroClienteService implements CadastroClienteServicePort {

    private final CadastroClientePort cadastroClientePort;

    public CadastroClienteService(CadastroClientePort cadastroClientePort) {
        this.cadastroClientePort = cadastroClientePort;
    }

    @Override
    public Cliente cadastrarCliente(Cliente cliente) {
        return cadastroClientePort.salvar(cliente);
    }
}
