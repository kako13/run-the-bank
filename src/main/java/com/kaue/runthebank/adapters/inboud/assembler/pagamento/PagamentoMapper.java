package com.kaue.runthebank.adapters.inboud.assembler.pagamento;

import com.kaue.runthebank.adapters.inboud.assembler.conta.ContaMapper;
import com.kaue.runthebank.adapters.inboud.assembler.estorno.EstornoMapper;
import com.kaue.runthebank.adapters.inboud.assembler.movimento.MovimentoMapper;
import com.kaue.runthebank.adapters.inboud.controller.request.pagamento.PagamentoInput;
import com.kaue.runthebank.adapters.inboud.controller.request.pagamento.PagamentoModel;
import com.kaue.runthebank.adapters.inboud.entity.PagamentoEntity;
import com.kaue.runthebank.application.core.domain.Pagamento;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ContaMapper.class, EstornoMapper.class, MovimentoMapper.class})
public interface PagamentoMapper {
    Pagamento toDomainObject(PagamentoInput pagamentoInput);
    PagamentoEntity toEntity(Pagamento pagamento);
    Pagamento toDomainObject(PagamentoEntity pagamentoEntity);
    @Named("toCollectionDomain")
    @Mapping(target = "contaDestinatario", ignore = true)
    @Mapping(target = "contaRemetente", ignore = true)
    Pagamento toDomainObjectWhitoutContas(PagamentoEntity pagamentoEntity);
    PagamentoModel toModel(Pagamento pagamento);
    @IterableMapping(qualifiedByName = "toCollectionDomain")
    List<Pagamento> toCollectionDomain(List<PagamentoEntity> pagamentosEntity);
    List<PagamentoModel> toCollectionModel(List<Pagamento> pagamentos);
}
