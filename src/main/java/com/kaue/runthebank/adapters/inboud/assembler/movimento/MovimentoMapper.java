package com.kaue.runthebank.adapters.inboud.assembler.movimento;

import com.kaue.runthebank.adapters.inboud.controller.request.MovimentoModel;
import com.kaue.runthebank.adapters.inboud.entity.MovimentoEntity;
import com.kaue.runthebank.application.core.domain.Movimento;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovimentoMapper {
    MovimentoEntity toEntity(Movimento movimento);
    Movimento toDomainObject(MovimentoEntity movimentoEntity);
    MovimentoModel toModel(Movimento movimento);
    List<Movimento> toCollectionDomain(List<MovimentoEntity> movimentosEntity);
    List<MovimentoModel> toCollectionModel(List<Movimento> movimentos);
}
