package com.kaue.runthebank.adapters.outbound;

import com.kaue.runthebank.adapters.inboud.assembler.pagamento.PagamentoMapper;
import com.kaue.runthebank.adapters.inboud.entity.PagamentoEntity;
import com.kaue.runthebank.adapters.outbound.repository.PagamentoRepository;
import com.kaue.runthebank.application.core.domain.Pagamento;
import com.kaue.runthebank.application.ports.out.pagamento.ConsultaPagamentoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ConsultaPagamentoDataBaseAdapter implements ConsultaPagamentoPort {
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private PagamentoMapper pagamentoMapper;

    @Override
    public List<Pagamento> listar(Long contaId) {
        List<PagamentoEntity> pagamentos = pagamentoRepository.findAllByContaRemetenteId(contaId);
        return pagamentoMapper.toCollectionDomain(pagamentos);
    }

    @Override
    public Optional<Pagamento> buscarPorCodigoEConta(String codigoPagamento, Long contaId) {
        PagamentoEntity pagamentoEntity = pagamentoRepository
                .findByCodigoPagamentoAndContaRemetenteWithContas(codigoPagamento, contaId);
        return Optional.ofNullable(pagamentoMapper.toDomainObject(pagamentoEntity));
    }
}
