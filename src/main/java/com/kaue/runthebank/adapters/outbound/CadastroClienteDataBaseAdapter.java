package com.kaue.runthebank.adapters.outbound;

import com.kaue.runthebank.adapters.inboud.assembler.cliente.ClienteAssembler;
import com.kaue.runthebank.adapters.inboud.assembler.cliente.ClienteDisassembler;
import com.kaue.runthebank.adapters.inboud.entity.ClienteEntity;
import com.kaue.runthebank.adapters.outbound.repository.ClienteRepository;
import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.ports.out.cliente.CadastroClientePort;
import com.kaue.runthebank.config.exception.NegocioException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public Cliente salvar(Cliente cliente) {
        try {
            ClienteEntity entity = clienteAssembler.toEntity(cliente);
            return clienteDisassembler.toDomainObject(cadastroClienteRepository.save(entity));
        } catch (DataIntegrityViolationException e) {
            throw new NegocioException("Já existe um cadastro de cliente com o documento informado");
        }
    }
}