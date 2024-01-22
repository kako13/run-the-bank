package com.kaue.runthebank.adapters.outbound.repository;

import com.kaue.runthebank.adapters.inboud.entity.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<ContaEntity, Long> {
    Optional<ContaEntity> findByIdAndClienteId(Long contaId, Long clienteId);
    List<ContaEntity> findAllByClienteId(Long clienteId);
}
