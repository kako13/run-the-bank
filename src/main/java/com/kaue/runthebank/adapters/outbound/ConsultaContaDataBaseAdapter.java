package com.kaue.runthebank.adapters.outbound;

import com.kaue.runthebank.adapters.inboud.assembler.conta.ContaMapper;
import com.kaue.runthebank.adapters.inboud.entity.ContaEntity;
import com.kaue.runthebank.adapters.outbound.repository.ContaRepository;
import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.core.exception.ContaNaoEncontradaException;
import com.kaue.runthebank.application.ports.out.conta.ConsultaContaPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultaContaDataBaseAdapter implements ConsultaContaPort {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ContaMapper contaMapper;

    @Override
    public Conta buscar(Long clienteId, Long contaId) {
        ContaEntity contaEntity = contaRepository.findByIdAndClienteId(contaId, clienteId)
                .orElseThrow(() -> new ContaNaoEncontradaException(contaId));
        return contaMapper.toDomainObject(contaEntity);
    }

    @Override
    public Conta buscar(Long contaId) {
        ContaEntity contaEntity = contaRepository.findById(contaId)
                .orElseThrow(() -> new ContaNaoEncontradaException(contaId));
        return contaMapper.toDomainObject(contaEntity);
    }

    @Override
    public List<Conta> listar(Long clienteId) {
        List<ContaEntity> contasEntity = contaRepository.findAllByClienteId(clienteId);
        return contaMapper.toCollectionDomain(contasEntity);
    }
}