package com.kaue.runthebank.application.ports.in.conta;

import com.kaue.runthebank.application.core.domain.Conta;

public interface AberturaContaClienteServicePort {
    Conta abrirConta(Long clienteId, Conta conta);
}
