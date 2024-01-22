package com.kaue.runthebank.adapters.outbound;

import com.kaue.runthebank.adapters.inboud.assembler.conta.ContaMapper;
import com.kaue.runthebank.adapters.inboud.assembler.pagamento.PagamentoMapper;
import com.kaue.runthebank.adapters.inboud.entity.ContaEntity;
import com.kaue.runthebank.adapters.inboud.entity.PagamentoEntity;
import com.kaue.runthebank.adapters.outbound.repository.ContaRepository;
import com.kaue.runthebank.adapters.outbound.repository.PagamentoRepository;
import com.kaue.runthebank.application.core.domain.Pagamento;
import com.kaue.runthebank.application.ports.out.pagamento.PagamentoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PagamentoDataBaseAdapter implements PagamentoPort {
    @Autowired
    private ContaMapper contaMapper;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private PagamentoMapper pagamentoMapper;

    @Transactional
    @Override
    public Pagamento cadastrarPagamento(Pagamento pagamento) {
        ContaEntity contaRemetenteEntity = contaRepository.getReferenceById(pagamento.getContaRemetente().getId());
        ContaEntity contaDestinatarioEntity = contaRepository.getReferenceById(pagamento.getContaDestinataria().getId());

        PagamentoEntity pagamentoEntity = pagamentoMapper.toEntity(pagamento);
        contaMapper.updateContaFromDomain(contaRemetenteEntity, pagamento.getContaRemetente());
        contaMapper.updateContaFromDomain(contaDestinatarioEntity, pagamento.getContaDestinataria());
        pagamentoEntity.setContaRemetente(contaRemetenteEntity);
        pagamentoEntity.setContaDestinataria(contaDestinatarioEntity);

        return pagamentoMapper.toDomainObject(pagamentoRepository.save(pagamentoEntity));
    }

    @Override
    public List<Pagamento> listar(Long contaId) {
        List<PagamentoEntity> pagamentos = pagamentoRepository.findAllByContaRemetenteId(contaId);
        return pagamentoMapper.toCollectionDomain(pagamentos);
    }
}
