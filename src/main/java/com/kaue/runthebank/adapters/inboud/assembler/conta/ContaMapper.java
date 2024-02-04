package com.kaue.runthebank.adapters.inboud.assembler.conta;

import com.kaue.runthebank.adapters.inboud.assembler.estorno.EstornoMapper;
import com.kaue.runthebank.adapters.inboud.assembler.movimento.MovimentoMapper;
import com.kaue.runthebank.adapters.inboud.assembler.pagamento.PagamentoMapper;
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

@Mapper(componentModel = "spring", uses = {PagamentoMapper.class, EstornoMapper.class, MovimentoMapper.class})
public interface ContaMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "status", target = "status", qualifiedByName = "toStatusConta")
    Conta toDomainObject(ContaInput contaInput);

    @Named("toStatusConta")
    default StatusConta toStatusConta(String statusContaInput) {
        return StatusConta.valueOf(statusContaInput.toUpperCase());
    }

    @Mapping(target = "movimentos", ignore = true)
    Conta toDomainObject(ContaEntity contaEntity);

    ContaEntity toEntity(Conta conta);

    ContaModel toModel(Conta conta);

    List<ContaModel> toCollectionModel(List<Conta> conta);

    @Mapping(target = "movimentos", ignore = true)
    List<Conta> toCollectionDomain(List<ContaEntity> contasEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "movimentos", ignore = true)
    void updateContaEntityFromDomain(@MappingTarget ContaEntity contaEntity, Conta conta);

}
