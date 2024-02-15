package com.kaue.runthebank.adapters.outbound;

import com.kaue.runthebank.adapters.inboud.assembler.estorno.EstornoMapper;
import com.kaue.runthebank.adapters.outbound.repository.EstornoRepository;
import com.kaue.runthebank.application.core.domain.Estorno;
import com.kaue.runthebank.application.ports.out.estorno.ConsultaEstornoPagamentoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ConsultaEstornoPagamentoDataBaseAdapter implements ConsultaEstornoPagamentoPort {
    @Autowired
    private EstornoRepository estornoRepository;
    @Autowired
    private EstornoMapper estornoMapper;

    @Override
    public List<Estorno> listar(Long contaId) {
        return estornoMapper.toCollectionDomain(estornoRepository.findAllByContaRemetenteId(contaId));
    }

    @Override
    public Optional<Estorno> buscar(Long pagamentoId) {
        return Optional.ofNullable(estornoMapper.toDomainObject(estornoRepository.findByPagamentoId(pagamentoId)));
    }

    @Override
    public Optional<Estorno> buscarPorCodigoEConta(String codigoEstorno, Long contaRemetenteId) {
        return Optional.ofNullable(estornoMapper.toDomainObject(estornoRepository
                .findByCodigoEstornoAndContaRemetenteId(codigoEstorno, contaRemetenteId)));
    }
}
