package com.kaue.runthebank.adapters.outbound;

import com.kaue.runthebank.adapters.inboud.assembler.cliente.ClienteMapper;
import com.kaue.runthebank.adapters.inboud.entity.ClienteEntity;
import com.kaue.runthebank.adapters.outbound.repository.ClienteRepository;
import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.ports.out.cliente.ConsultaClientePort;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
    public Optional<Cliente> buscar(Long clienteId) {
        ClienteEntity clienteEntity = cadastroClienteRepository.findByIdJoinContas(clienteId);
        return Optional.ofNullable(clienteMapper.toDomainObject(clienteEntity));
    }

    @Override
    public Optional<Cliente> buscarPorDocumento(String documento) {
        ClienteEntity clienteEntity = cadastroClienteRepository.findByDocumento(documento);
        return Optional.ofNullable(clienteMapper.toDomainObject(clienteEntity));
    }
}