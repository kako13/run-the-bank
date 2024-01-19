package com.kaue.runthebank.adapters.inboud.assembler.conta;

import com.kaue.runthebank.adapters.inboud.controller.request.conta.ContaModel;
import com.kaue.runthebank.adapters.inboud.entity.ContaEntity;
import com.kaue.runthebank.application.core.domain.Conta;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContaAssembler {

    @Autowired
    private ModelMapper modelMapper;


    public ContaModel toModel(Conta conta) {
        return modelMapper.map(conta, ContaModel.class);
    }

    public List<ContaModel> toCollectionModel(List<Conta> contas) {
        return contas.stream()
                .map(this::toModel)
                .toList();
    }

    public ContaEntity toEntity(Conta conta) {
        return modelMapper.map(conta, ContaEntity.class);
    }

    public List<ContaEntity> toCollectionEntity(List<Conta> contas) {
        return contas.stream()
                .map(this::toEntity)
                .toList();
    }
}
