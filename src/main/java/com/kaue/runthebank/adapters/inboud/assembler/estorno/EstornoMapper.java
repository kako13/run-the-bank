package com.kaue.runthebank.adapters.inboud.assembler.estorno;

import com.kaue.runthebank.adapters.inboud.controller.request.estorno.EstornoInput;
import com.kaue.runthebank.adapters.inboud.controller.request.estorno.EstornoModel;
import com.kaue.runthebank.adapters.inboud.entity.EstornoEntity;
import com.kaue.runthebank.application.core.domain.Estorno;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstornoMapper {
    Estorno toDomainObject(EstornoInput estornoInput);
    EstornoEntity toEntity(Estorno estorno);
    @Mapping(target = "contaRemetente", ignore = true)
    @Mapping(target = "contaDestinatario", ignore = true)
    @Mapping(target = "pagamento.contaRemetente.movimentos", ignore = true)
    @Mapping(target = "pagamento.contaDestinatario.movimentos", ignore = true)
    Estorno toDomainObject(EstornoEntity estornoEntity);

    @Named("toCollectionDomain")
    @Mapping(target = "contaRemetente", ignore = true)
    @Mapping(target = "contaDestinatario", ignore = true)
    @Mapping(target = "pagamento.contaRemetente", ignore = true)
    @Mapping(target = "pagamento.contaDestinatario", ignore = true)
    Estorno toDomainObjectWithoutPagamentoContas(EstornoEntity estornoEntity);
    EstornoModel toModel(Estorno estorno);
    @IterableMapping(qualifiedByName = "toCollectionDomain")
    List<Estorno> toCollectionDomain(List<EstornoEntity> estornosEntity);
    List<EstornoModel> toCollectionModel(List<Estorno> estornos);
}
