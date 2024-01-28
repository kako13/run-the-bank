package com.kaue.runthebank.adapters.outbound;

import com.kaue.runthebank.adapters.inboud.assembler.cliente.ClienteMapper;
import com.kaue.runthebank.adapters.inboud.entity.ClienteEntity;
import com.kaue.runthebank.adapters.outbound.repository.ClienteRepository;
import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.ports.out.cliente.CadastroClientePort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CadastroClienteDataBaseAdapter implements CadastroClientePort {

    private final ClienteRepository cadastroClienteRepository;
    private final ClienteMapper clienteMapper;

    public CadastroClienteDataBaseAdapter(ClienteRepository cadastroClienteRepository,
                                          ClienteMapper clienteMapper) {
        this.cadastroClienteRepository = cadastroClienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Transactional
    @Override
    public Cliente salvar(Cliente cliente) {
        ClienteEntity entity = clienteMapper.toEntity(cliente);
        return clienteMapper.toDomainObject(cadastroClienteRepository.save(entity));
    }
}