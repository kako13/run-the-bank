package com.kaue.runthebank.application.core.service;

import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.core.exception.ContaNaoEncontradaException;
import com.kaue.runthebank.application.ports.in.cliente.ConsultaClienteServicePort;
import com.kaue.runthebank.application.ports.in.conta.ConsultaContaClienteServicePort;
import com.kaue.runthebank.application.ports.out.conta.ConsultaContaPort;

import java.util.List;

public class ConsultaContaClienteService implements ConsultaContaClienteServicePort {

    public static final String MSG_CONTA_INEXISTENTE = "Não existe um cadastro de conta com id '%d' para o cliente '%d'";
    private final ConsultaContaPort consultaContaPort;
    private final ConsultaClienteServicePort consultaClienteServicePort;

    public ConsultaContaClienteService(ConsultaContaPort consultaContaPort,
                                       ConsultaClienteServicePort consultaClienteServicePort) {
        this.consultaContaPort = consultaContaPort;
        this.consultaClienteServicePort = consultaClienteServicePort;
    }

    @Override
    public Conta buscar(Long clienteId, Long contaId) {
        return consultaContaPort.buscar(clienteId, contaId)
                .orElseThrow(() -> new ContaNaoEncontradaException(
                        String.format(MSG_CONTA_INEXISTENTE,
                                contaId, clienteId)));
    }

    @Override
    public Conta buscar(Long contaId) {
        return consultaContaPort.buscar(contaId)
                .orElseThrow(() -> new ContaNaoEncontradaException(contaId));
    }

    @Override
    public List<Conta> listar(Long clienteId) {
        consultaClienteServicePort.buscar(clienteId);
        return consultaContaPort.listar(clienteId);
    }
}
