package com.kaue.runthebank.application.ports.out.cliente;

import com.kaue.runthebank.application.core.domain.Cliente;

public interface ConsultaClientePort {
    Cliente buscar(Long clienteId);
}
