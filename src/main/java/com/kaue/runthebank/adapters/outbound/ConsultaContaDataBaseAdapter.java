package com.kaue.runthebank.adapters.outbound;

import com.kaue.runthebank.adapters.inboud.assembler.conta.ContaDisassembler;
import com.kaue.runthebank.adapters.inboud.entity.ContaEntity;
import com.kaue.runthebank.adapters.outbound.repository.ContaRepository;
import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.ports.out.ConsultaContaPort;
import com.kaue.runthebank.config.exception.ContaNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaContaDataBaseAdapter implements ConsultaContaPort {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ContaDisassembler contaDisassembler;

    @Override
    public Conta buscar(Long clienteId, Long contaId) {
        ContaEntity contaEntity = contaRepository.findByIdAndClienteId(clienteId, contaId)
                .orElseThrow(() -> new ContaNaoEncontradoException(contaId));
        return contaDisassembler.toDomainObject(contaEntity);
    }
}