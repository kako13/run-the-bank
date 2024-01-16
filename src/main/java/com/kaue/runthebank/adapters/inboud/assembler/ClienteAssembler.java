package com.kaue.runthebank.adapters.inboud.assembler;

import com.kaue.runthebank.adapters.inboud.controller.request.ClienteModel;
import com.kaue.runthebank.adapters.inboud.entity.ClienteEntity;
import com.kaue.runthebank.application.core.domain.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteAssembler {

    @Autowired
    private ModelMapper modelMapper;


    public ClienteModel toModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteModel.class);
    }

    public List<ClienteModel> toCollectionModel(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::toModel)
                .toList();
    }

    public ClienteEntity toEntity(Cliente cliente) {
        return modelMapper.map(cliente, ClienteEntity.class);
    }

    public List<ClienteEntity> toCollectionEntity(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::toEntity)
                .toList();
    }
}
