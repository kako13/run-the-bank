package com.kaue.runthebank.application.ports.in.conta;

import com.kaue.runthebank.application.core.domain.Conta;

public interface ConsultaContaClienteServicePort {
    Conta buscar(Long clienteId, Long contaId);
}
