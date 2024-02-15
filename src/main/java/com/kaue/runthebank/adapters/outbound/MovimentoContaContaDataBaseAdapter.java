package com.kaue.runthebank.adapters.outbound;

import com.kaue.runthebank.adapters.inboud.assembler.movimento.MovimentoMapper;
import com.kaue.runthebank.adapters.inboud.entity.MovimentoEntity;
import com.kaue.runthebank.adapters.outbound.repository.MovimentoRepository;
import com.kaue.runthebank.application.core.domain.Movimento;
import com.kaue.runthebank.application.ports.out.MovimentoContaPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MovimentoContaContaDataBaseAdapter implements MovimentoContaPort {
    @Autowired
    private MovimentoRepository movimentoRepository;
    @Autowired
    private MovimentoMapper movimentoMapper;

    @Transactional
    @Override
    public Movimento registrarMovimento(Movimento movimento) {
        MovimentoEntity movimentoEntity = movimentoMapper.toEntity(movimento);
        return movimentoMapper.toDomainObject(movimentoRepository.save(movimentoEntity));
    }
}
