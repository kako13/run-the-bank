package com.kaue.runthebank.adapters.outbound;

import com.kaue.runthebank.adapters.inboud.assembler.ClienteAssembler;
import com.kaue.runthebank.adapters.inboud.assembler.ClienteDisassembler;
import com.kaue.runthebank.adapters.inboud.entity.ClienteEntity;
import com.kaue.runthebank.adapters.outbound.repository.ClienteRepository;
import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.ports.out.SalvarClientePort;
import org.springframework.stereotype.Component;

@Component
public class SalvarClienteDataBaseAdapter implements SalvarClientePort {

    private ClienteRepository cadastroClienteRepository;
    private ClienteAssembler clienteAssembler;
    private ClienteDisassembler clienteDisassembler;

    public SalvarClienteDataBaseAdapter(ClienteRepository cadastroClienteRepository,
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