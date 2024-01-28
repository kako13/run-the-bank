package com.kaue.runthebank.application.core.service;

import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.core.exception.ClienteNaoEncontradoException;
import com.kaue.runthebank.application.core.exception.NegocioException;
import com.kaue.runthebank.application.ports.in.cliente.ConsultaClienteServicePort;
import com.kaue.runthebank.application.ports.out.cliente.ConsultaClientePort;

public class ConsultaClienteService implements ConsultaClienteServicePort {

    private final ConsultaClientePort consultaClientePort;

    public ConsultaClienteService(ConsultaClientePort consultaClientePort) {
        this.consultaClientePort = consultaClientePort;
    }

    @Override
    public Cliente buscar(Long clienteId) {
        return consultaClientePort.buscar(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));
    }

    @Override
    public void buscarPorDocumento(String documento) {
        consultaClientePort.buscarPorDocumento(documento).ifPresent(cliente -> {
            throw new NegocioException("Já existe um cadastro de cliente com o documento informado");
        });
    }
}
