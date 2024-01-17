package com.kaue.runthebank.adapters.outbound;

import com.kaue.runthebank.adapters.inboud.assembler.cliente.ClienteAssembler;
import com.kaue.runthebank.adapters.inboud.assembler.cliente.ClienteDisassembler;
import com.kaue.runthebank.adapters.inboud.entity.ClienteEntity;
import com.kaue.runthebank.adapters.outbound.repository.ClienteRepository;
import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.ports.out.cliente.CadastroClientePort;
import org.springframework.stereotype.Component;

@Component
public class CadastroClienteDataBaseAdapter implements CadastroClientePort {

    private final ClienteRepository cadastroClienteRepository;
    private final ClienteAssembler clienteAssembler;
    private final ClienteDisassembler clienteDisassembler;

    public CadastroClienteDataBaseAdapter(ClienteRepository cadastroClienteRepository,
                                          ClienteAssembler clienteAssembler,
                                          ClienteDisassembler clienteDisassembler) {
        this.cadastroClienteRepository = cadastroClienteRepository;
        this.clienteAssembler = clienteAssembler;
        this.clienteDisassembler = clienteDisassembler;
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        ClienteEntity entity = clienteAssembler.toEntity(cliente);
        return clienteDisassembler.toDomainObject(cadastroClienteRepository.save(entity));
    }
}