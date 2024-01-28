package com.kaue.runthebank.application.ports.out.conta;

import com.kaue.runthebank.application.core.domain.Conta;

import java.util.List;
import java.util.Optional;

public interface ConsultaContaPort {
    Optional<Conta> buscar(Long clienteId, Long contaId);

    Optional<Conta> buscar(Long contaId);

    List<Conta> listar(Long clienteId);
}
