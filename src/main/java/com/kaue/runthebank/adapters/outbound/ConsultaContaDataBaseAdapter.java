package com.kaue.runthebank.adapters.outbound;

import com.kaue.runthebank.adapters.inboud.assembler.conta.ContaMapper;
import com.kaue.runthebank.adapters.inboud.entity.ContaEntity;
import com.kaue.runthebank.adapters.outbound.repository.ContaRepository;
import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.ports.out.conta.ConsultaContaPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ConsultaContaDataBaseAdapter implements ConsultaContaPort {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ContaMapper contaMapper;

    @Override
    public Optional<Conta> buscar(Long clienteId, Long contaId) {
        ContaEntity contaEntity = contaRepository.findByIdAndClienteId(contaId, clienteId);
        return Optional.ofNullable(contaMapper.toDomainObject(contaEntity));
    }

    @Override
    public Optional<Conta> buscar(Long contaId) {
        ContaEntity contaEntity = contaRepository.findById(contaId).orElseThrow();
        return Optional.ofNullable(contaMapper.toDomainObject(contaEntity));
    }
}