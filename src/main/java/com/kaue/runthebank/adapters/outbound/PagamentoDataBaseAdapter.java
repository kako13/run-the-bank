package com.kaue.runthebank.adapters.outbound;

import com.kaue.runthebank.adapters.inboud.assembler.cliente.ClienteMapper;
import com.kaue.runthebank.adapters.inboud.assembler.conta.ContaMapper;
import com.kaue.runthebank.adapters.inboud.assembler.pagamento.PagamentoMapper;
import com.kaue.runthebank.adapters.inboud.entity.ContaEntity;
import com.kaue.runthebank.adapters.inboud.entity.PagamentoEntity;
import com.kaue.runthebank.adapters.outbound.repository.ContaRepository;
import com.kaue.runthebank.adapters.outbound.repository.PagamentoRepository;
import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.core.domain.Pagamento;
import com.kaue.runthebank.application.ports.out.NotificacaoClienteEventPort;
import com.kaue.runthebank.application.ports.out.pagamento.PagamentoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PagamentoDataBaseAdapter implements PagamentoPort {
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private NotificacaoClienteEventPort notificacaoClienteEventPort;
    @Autowired
    private ContaMapper contaMapper;
    @Autowired
    private PagamentoMapper pagamentoMapper;
    @Autowired
    private ClienteMapper clienteMapper;

    @Transactional
    @Override
    public Pagamento registrarPagamento(Pagamento pagamento) {
        ContaEntity contaRemetenteEntity = contaRepository.getReferenceById(pagamento.getContaRemetente().getId());
        ContaEntity contaDestinatarioEntity = contaRepository.getReferenceById(pagamento.getContaDestinatario().getId());
        PagamentoEntity pagamentoEntity = pagamentoMapper.toEntity(pagamento);
        montarPagamentoEntity(pagamento, contaRemetenteEntity, contaDestinatarioEntity, pagamentoEntity);
        pagamentoEntity = pagamentoRepository.save(pagamentoEntity);
        enviarNotificacao(pagamentoEntity);
        return pagamentoMapper.toDomainObject(pagamentoEntity);
    }

    private void montarPagamentoEntity(Pagamento pagamento, ContaEntity contaRemetenteEntity, ContaEntity contaDestinatarioEntity, PagamentoEntity pagamentoEntity) {
        contaMapper.updateContaEntityFromDomain(contaRemetenteEntity, pagamento.getContaRemetente());
        contaMapper.updateContaEntityFromDomain(contaDestinatarioEntity, pagamento.getContaDestinatario());
        pagamentoEntity.setContaRemetente(contaRemetenteEntity);
        pagamentoEntity.setContaDestinatario(contaDestinatarioEntity);
    }

    private void enviarNotificacao(PagamentoEntity pagamentoPersistido) {
        Cliente clienteRemetente = clienteMapper.toDomainObject(pagamentoPersistido.getContaRemetente().getCliente());
        Cliente clienteDestinatario = clienteMapper.toDomainObject(pagamentoPersistido.getContaDestinatario().getCliente());
        Pagamento pagamento = pagamentoMapper.toDomainObject(pagamentoPersistido);
        notificacaoClienteEventPort.notificarPagamento(pagamento, clienteRemetente, clienteDestinatario);
    }
}
