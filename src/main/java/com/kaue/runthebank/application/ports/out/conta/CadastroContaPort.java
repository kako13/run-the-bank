package com.kaue.runthebank.application.ports.out.conta;

import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.core.domain.Conta;

public interface CadastroContaPort {
    Conta gerarConta(Cliente cliente, Conta agencia);
}
