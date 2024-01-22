package com.kaue.runthebank.application.core.service;

import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.ports.in.cliente.ConsultaClienteServicePort;
import com.kaue.runthebank.application.ports.out.cliente.ConsultaClientePort;

public class ConsultaClienteService implements ConsultaClienteServicePort {

    private final ConsultaClientePort consultaClientePort;

    public ConsultaClienteService(ConsultaClientePort consultaClientePort) {
        this.consultaClientePort = consultaClientePort;
    }

    @Override
    public Cliente buscar(Long clienteId) {
        return consultaClientePort.buscar(clienteId);
    }
}
