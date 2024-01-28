package com.kaue.runthebank.adapters.outbound.repository;

import com.kaue.runthebank.adapters.inboud.entity.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<ContaEntity, Long> {
    ContaEntity findByIdAndClienteId(Long contaId, Long clienteId);
    List<ContaEntity> findAllByClienteId(Long clienteId);
}
