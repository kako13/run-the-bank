package com.kaue.runthebank.application.ports.in;

import com.kaue.runthebank.application.core.domain.Cliente;

public interface CadastrarClienteServicePort {
    Cliente cadastrarCliente(Cliente cliente);
}
