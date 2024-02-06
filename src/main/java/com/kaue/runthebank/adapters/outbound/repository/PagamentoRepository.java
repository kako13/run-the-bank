package com.kaue.runthebank.adapters.outbound.repository;

import com.kaue.runthebank.adapters.inboud.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long> {
    List<PagamentoEntity> findAllByContaRemetenteId(Long contaId);

    @Query("from pagamento p " +
            "join fetch p.contaRemetente cr " +
            "join fetch p.contaDestinatario " +
            "where p.codigoPagamento = :codigoPagamento " +
            "and cr.id = :contaId")
    PagamentoEntity findByCodigoPagamentoAndContaRemetenteWithContas(String codigoPagamento, Long contaId);
}
