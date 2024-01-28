package com.kaue.runthebank.application.core.service;

import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.ports.in.cliente.CadastroClienteServicePort;
import com.kaue.runthebank.application.ports.in.cliente.ConsultaClienteServicePort;
import com.kaue.runthebank.application.ports.out.cliente.CadastroClientePort;

public class CadastroClienteService implements CadastroClienteServicePort {

    private final CadastroClientePort cadastroClientePort;
    private final ConsultaClienteServicePort consultaClienteServicePort;

    public CadastroClienteService(CadastroClientePort cadastroClientePort,
                                  ConsultaClienteServicePort consultaClienteServicePort) {
        this.cadastroClientePort = cadastroClientePort;
        this.consultaClienteServicePort = consultaClienteServicePort;
    }

    @Override
    public Cliente cadastrarCliente(Cliente cliente) {
        consultaClienteServicePort.buscarPorDocumento(cliente.getDocumento());
        return cadastroClientePort.salvar(cliente);
    }
}
