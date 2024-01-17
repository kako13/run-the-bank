package com.kaue.runthebank.adapters.outbound;

import com.kaue.runthebank.adapters.inboud.assembler.cliente.ClienteDisassembler;
import com.kaue.runthebank.adapters.inboud.entity.ClienteEntity;
import com.kaue.runthebank.adapters.outbound.repository.ClienteRepository;
import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.ports.out.cliente.ConsultaClientePort;
import com.kaue.runthebank.config.exception.ClienteNaoEncontradoException;
import org.springframework.stereotype.Component;

@Component
public class ConsultaClienteDataBaseAdapter implements ConsultaClientePort {

    private final ClienteRepository cadastroClienteRepository;
    private final ClienteDisassembler clienteDisassembler;

    public ConsultaClienteDataBaseAdapter(ClienteRepository cadastroClienteRepository,
                                          ClienteDisassembler clienteDisassembler) {
        this.cadastroClienteRepository = cadastroClienteRepository;
        this.clienteDisassembler = clienteDisassembler;
    }

    @Override
    public Cliente buscar(Long clienteId) {
        ClienteEntity clienteEntity = cadastroClienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));
        return clienteDisassembler.toDomainObject(clienteEntity);
    }
}