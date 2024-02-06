package com.kaue.runthebank.adapters.outbound;

import com.kaue.runthebank.adapters.inboud.assembler.conta.ContaMapper;
import com.kaue.runthebank.adapters.inboud.assembler.estorno.EstornoMapper;
import com.kaue.runthebank.adapters.inboud.entity.ContaEntity;
import com.kaue.runthebank.adapters.inboud.entity.EstornoEntity;
import com.kaue.runthebank.adapters.inboud.entity.PagamentoEntity;
import com.kaue.runthebank.adapters.outbound.repository.ContaRepository;
import com.kaue.runthebank.adapters.outbound.repository.EstornoRepository;
import com.kaue.runthebank.adapters.outbound.repository.PagamentoRepository;
import com.kaue.runthebank.application.core.domain.Estorno;
import com.kaue.runthebank.application.ports.out.estorno.EstornoPagamentoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EstornoPagamentoDataBaseAdapter implements EstornoPagamentoPort {
    @Autowired
    private EstornoRepository estornoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private EstornoMapper estornoMapper;
    @Autowired
    private ContaMapper contaMapper;

    @Transactional
    @Override
    public Estorno registrarEstorno(Estorno estorno) {
        EstornoEntity estornoEntity = estornoMapper.toEntity(estorno);
        montarEstornoEntity(estorno, estornoEntity);
        EstornoEntity estornoPersistido = estornoRepository.save(estornoEntity);
        estornoRepository.flush();
        return estornoMapper.toDomainObject(estornoPersistido);
    }

    private void montarEstornoEntity(Estorno estorno, EstornoEntity estornoEntity) {
        ContaEntity contaRemetenteEntity = contaRepository.getReferenceById(estorno.getContaRemetente().getId());
        ContaEntity contaDestinatarioEntity = contaRepository.getReferenceById(estorno.getContaDestinatario().getId());
        PagamentoEntity pagamentoEntity = pagamentoRepository.findByCodigoPagamentoAndContaRemetenteWithContas(
                estorno.getPagamento().getCodigoPagamento(), contaRemetenteEntity.getId());

        contaMapper.updateContaEntityFromDomain(contaRemetenteEntity, estorno.getContaRemetente());
        contaMapper.updateContaEntityFromDomain(contaDestinatarioEntity, estorno.getContaDestinatario());
        estornoEntity.setContaRemetente(contaRemetenteEntity);
        estornoEntity.setContaDestinatario(contaDestinatarioEntity);
        estornoEntity.setPagamento(pagamentoEntity);
    }
}
