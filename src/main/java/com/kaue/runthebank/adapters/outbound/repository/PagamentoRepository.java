package com.kaue.runthebank.adapters.outbound.repository;

import com.kaue.runthebank.adapters.inboud.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long> {
    List<PagamentoEntity> findAllByContaRemetenteId(Long contaId);

    Optional<PagamentoEntity> findByCodigoPagamento(String codigoPagamento);
    Optional<PagamentoEntity> findByCodigoPagamentoAndContaRemetenteId(String codigoPagamento, Long contaId);
}
