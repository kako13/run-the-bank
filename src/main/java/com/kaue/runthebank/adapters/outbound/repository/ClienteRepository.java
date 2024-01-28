package com.kaue.runthebank.adapters.outbound.repository;

import com.kaue.runthebank.adapters.inboud.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    @Query("from cliente c left join fetch c.contas where c.id = :clienteId ")
    ClienteEntity findByIdJoinContas(Long clienteId);

    ClienteEntity findByDocumento(String documento);
}