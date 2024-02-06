package com.kaue.runthebank.adapters.outbound.repository;

import com.kaue.runthebank.adapters.inboud.entity.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<ContaEntity, Long> {
    ContaEntity findByIdAndClienteId(Long contaId, Long clienteId);

    @Query("from conta c join fetch c.cliente where c.id = :contaId")
    ContaEntity findByIdWithCliente(Long contaId);
}
