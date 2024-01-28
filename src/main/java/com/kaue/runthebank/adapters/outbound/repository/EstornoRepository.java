package com.kaue.runthebank.adapters.outbound.repository;

import com.kaue.runthebank.adapters.inboud.entity.EstornoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstornoRepository extends JpaRepository<EstornoEntity, Long> {
    @Query("from estorno e where e.pagamento.id = :pagamentoId")
    EstornoEntity findByPagamentoId(Long pagamentoId);

    List<EstornoEntity> findAllByContaRemetenteId(Long contaId);

    EstornoEntity findByCodigoEstornoAndContaRemetenteId(String codigoEstorno, Long contaRemetenteId);
}
