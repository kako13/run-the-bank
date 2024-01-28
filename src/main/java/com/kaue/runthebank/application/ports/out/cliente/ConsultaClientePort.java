package com.kaue.runthebank.application.ports.out.cliente;

import com.kaue.runthebank.application.core.domain.Cliente;

import java.util.Optional;

public interface ConsultaClientePort {
    Optional<Cliente> buscar(Long clienteId);

    Optional<Cliente> buscarPorDocumento(String documento);
}
