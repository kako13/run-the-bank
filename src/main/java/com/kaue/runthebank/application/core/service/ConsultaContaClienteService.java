package com.kaue.runthebank.application.core.service;

import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.ports.in.conta.ConsultaContaClienteServicePort;
import com.kaue.runthebank.application.ports.out.ConsultaContaPort;

public class ConsultaContaClienteService implements ConsultaContaClienteServicePort {

    private final ConsultaContaPort consultaContaPort;

    public ConsultaContaClienteService(ConsultaContaPort consultaContaPort) {
        this.consultaContaPort = consultaContaPort;
    }

    @Override
    public Conta buscar(Long clienteId, Long contaId) {
        return consultaContaPort.buscar(clienteId, contaId);
    }
}
