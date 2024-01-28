package com.kaue.runthebank.adapters.inboud.assembler.estorno;

import com.kaue.runthebank.adapters.inboud.controller.request.estorno.EstornoInput;
import com.kaue.runthebank.adapters.inboud.controller.request.estorno.EstornoModel;
import com.kaue.runthebank.adapters.inboud.entity.EstornoEntity;
import com.kaue.runthebank.application.core.domain.Estorno;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstornoMapper {
    Estorno toDomainObject(EstornoInput estornoInput);
    EstornoEntity toEntity(Estorno estorno);
    Estorno toDomainObject(EstornoEntity estornoEntity);
    EstornoModel toModel(Estorno estorno);
    List<Estorno> toCollectionDomain(List<EstornoEntity> estornosEntity);
    List<EstornoModel> toCollectionModel(List<Estorno> estornos);
}
