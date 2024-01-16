package com.kaue.runthebank.application.core.service;

import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.ports.in.CadastrarClienteServicePort;
import com.kaue.runthebank.application.ports.out.SalvarClientePort;

public class CadastrarClienteService implements CadastrarClienteServicePort {

    private SalvarClientePort salvarClientePort;

    public CadastrarClienteService(SalvarClientePort salvarClientePort) {
        this.salvarClientePort = salvarClientePort;
    }

    @Override
    public Cliente cadastrarCliente(Cliente cliente) {
        return salvarClientePort.salvar(cliente);
    }
}
