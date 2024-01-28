package com.kaue.runthebank.adapters.inboud.assembler.conta;

import com.kaue.runthebank.adapters.inboud.controller.request.conta.ContaInput;
import com.kaue.runthebank.adapters.inboud.controller.request.conta.ContaModel;
import com.kaue.runthebank.adapters.inboud.entity.ContaEntity;
import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.core.domain.StatusConta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContaMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "status", target = "status", qualifiedByName = "toStatusConta")
    Conta toDomainObject(ContaInput contaInput);

    @Named("toStatusConta")
    default StatusConta toStatusConta(String statusContaInput) {
        return StatusConta.valueOf(statusContaInput.toUpperCase());
    }
    Conta toDomainObject(ContaEntity contaEntity);

    ContaEntity toEntity(Conta conta);

    ContaModel toModel(Conta conta);

    List<ContaModel> toCollectionModel(List<Conta> conta);

    List<Conta> toCollectionDomain(List<ContaEntity> contasEntity);

    @Mapping(target = "id", ignore = true)
    void updateContaEntityFromDomain(@MappingTarget ContaEntity contaEntity, Conta conta);

}
