package com.kaue.runthebank.application.ports.out;

import com.kaue.runthebank.application.core.domain.Conta;

public interface CadastroContaPort {
    Conta gerarConta(Long clienteId, Conta agencia);
}
