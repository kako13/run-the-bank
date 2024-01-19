package com.kaue.runthebank.adapters.inboud.assembler.conta;

import com.kaue.runthebank.adapters.inboud.controller.request.conta.ContaInput;
import com.kaue.runthebank.adapters.inboud.entity.ContaEntity;
import com.kaue.runthebank.application.core.domain.Conta;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContaDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Conta toDomainObject(ContaEntity contaEntity) {
        return modelMapper.map(contaEntity, Conta.class);
    }
    public Conta toDomainObject(ContaInput contaInput) {
        formatDocument(contaInput);
        return modelMapper.map(contaInput, Conta.class);
    }

    private void formatDocument(ContaInput contaInput) {
        contaInput.setStatus(contaInput.getStatus().toUpperCase());
    }
}
