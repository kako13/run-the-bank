package com.kaue.runthebank.application.ports.in.cliente;

import com.kaue.runthebank.application.core.domain.Cliente;

public interface CadastroClienteServicePort {
    Cliente cadastrarCliente(Cliente cliente);
}
