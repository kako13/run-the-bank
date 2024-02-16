package com.kaue.runthebank.application.ports.in.cliente;

import com.kaue.runthebank.application.core.domain.Cliente;

public interface ConsultaClienteServicePort {
    Cliente buscar(Long clienteId);

    void buscarPorDocumento(String documento);
}
