package com.kaue.runthebank.application.ports.out.conta;

import com.kaue.runthebank.application.core.domain.Conta;

import java.util.List;

public interface ConsultaContaPort {
    Conta buscar(Long clienteId, Long contaId);

    Conta buscar(Long contaId);

    List<Conta> listar(Long clienteId);
}
