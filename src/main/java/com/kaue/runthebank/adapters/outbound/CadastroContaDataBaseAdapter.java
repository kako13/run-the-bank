package com.kaue.runthebank.adapters.outbound;

import com.kaue.runthebank.adapters.inboud.assembler.cliente.ClienteMapper;
import com.kaue.runthebank.adapters.inboud.assembler.conta.ContaMapper;
import com.kaue.runthebank.adapters.inboud.entity.ClienteEntity;
import com.kaue.runthebank.adapters.inboud.entity.ContaEntity;
import com.kaue.runthebank.adapters.outbound.repository.ContaRepository;
import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.ports.out.conta.CadastroContaPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CadastroContaDataBaseAdapter implements CadastroContaPort {
    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private ContaMapper contaMapper;
    @Autowired
    private ContaRepository contaRepository;

    @Transactional
    @Override
    public Conta gerarConta(Cliente cliente, Conta conta) {
        ContaEntity contaEntity = montarContaEntity(conta, cliente);
        contaEntity = contaRepository.save(contaEntity);
        return contaMapper.toDomainObjectWithoutCliente(contaEntity);
    }

    private ContaEntity montarContaEntity(Conta conta, Cliente cliente) {
        ContaEntity contaEntity = contaMapper.toEntity(conta);
        ClienteEntity clienteEntity = clienteMapper.toEntity(cliente);
        contaEntity.setCliente(clienteEntity);
        return contaEntity;
    }
}