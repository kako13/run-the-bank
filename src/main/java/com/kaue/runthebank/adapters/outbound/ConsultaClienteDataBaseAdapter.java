package com.kaue.runthebank.adapters.outbound;

import com.kaue.runthebank.adapters.inboud.assembler.cliente.ClienteMapper;
import com.kaue.runthebank.adapters.inboud.entity.ClienteEntity;
import com.kaue.runthebank.adapters.outbound.repository.ClienteRepository;
import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.core.exception.ClienteNaoEncontradoException;
import com.kaue.runthebank.application.ports.out.cliente.ConsultaClientePort;
import org.springframework.stereotype.Component;

@Component
public class ConsultaClienteDataBaseAdapter implements ConsultaClientePort {

    private final ClienteRepository cadastroClienteRepository;
    private final ClienteMapper clienteMapper;

    public ConsultaClienteDataBaseAdapter(ClienteRepository cadastroClienteRepository,
                                          ClienteMapper clienteMapper) {
        this.cadastroClienteRepository = cadastroClienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public Cliente buscar(Long clienteId) {
        ClienteEntity clienteEntity = cadastroClienteRepository.findByIdJoinContas(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));
        return clienteMapper.toDomainObject(clienteEntity);
    }
}