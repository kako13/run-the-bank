package com.kaue.runthebank.adapters.outbound.repository;

import com.kaue.runthebank.adapters.inboud.entity.EstornoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstornoRepository extends JpaRepository<EstornoEntity, Long> {
    @Query("from estorno e where e.pagamento.id = :pagamentoId")
    EstornoEntity findByPagamentoId(Long pagamentoId);

    @Query("from estorno e join fetch e.pagamento where e.contaRemetente.id = :contaId")
    List<EstornoEntity> findAllByContaRemetenteId(Long contaId);

    @Query("from estorno e join fetch e.pagamento " +
            "join fetch e.contaRemetente cr " +
            "join fetch e.contaDestinatario " +
            "where e.codigoEstorno = :codigoEstorno " +
            "and cr.id = :contaRemetenteId")
    EstornoEntity findByCodigoEstornoAndContaRemetenteId(String codigoEstorno, Long contaRemetenteId);
}
