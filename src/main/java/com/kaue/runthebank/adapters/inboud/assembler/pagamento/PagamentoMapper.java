package com.kaue.runthebank.adapters.inboud.assembler.pagamento;

import com.kaue.runthebank.adapters.inboud.controller.request.pagamento.PagamentoModel;
import com.kaue.runthebank.adapters.inboud.controller.request.pagamento.PagamentoInput;
import com.kaue.runthebank.adapters.inboud.entity.PagamentoEntity;
import com.kaue.runthebank.application.core.domain.Pagamento;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {
    Pagamento toDomainObject(PagamentoInput pagamentoInput);
    PagamentoEntity toEntity(Pagamento pagamento);
    Pagamento toDomainObject(PagamentoEntity pagamentoEntity);
    PagamentoModel toModel(Pagamento pagamento);
    List<Pagamento> toCollectionDomain(List<PagamentoEntity> pagamentosEntity);
    List<PagamentoModel> toCollectionModel(List<Pagamento> pagamentos);
}
