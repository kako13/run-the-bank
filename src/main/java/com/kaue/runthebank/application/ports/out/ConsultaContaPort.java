package com.kaue.runthebank.application.ports.out;

import com.kaue.runthebank.application.core.domain.Conta;

public interface ConsultaContaPort {
    Conta buscar(Long clienteId, Long contaId);
}
