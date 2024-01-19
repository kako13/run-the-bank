package com.kaue.runthebank.adapters.outbound.repository;

import com.kaue.runthebank.adapters.inboud.entity.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<ContaEntity, Long> {
    ContaEntity findByIdAndClienteId(Long clienteId, Long contaId);
}
