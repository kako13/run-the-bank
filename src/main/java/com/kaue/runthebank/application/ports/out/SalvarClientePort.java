package com.kaue.runthebank.application.ports.out;

import com.kaue.runthebank.application.core.domain.Cliente;

public interface SalvarClientePort {
    Cliente salvar(Cliente cliente);
}
