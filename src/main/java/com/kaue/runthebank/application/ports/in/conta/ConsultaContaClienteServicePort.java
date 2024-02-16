package com.kaue.runthebank.application.ports.in.conta;

import com.kaue.runthebank.application.core.domain.Conta;

import java.util.List;

public interface ConsultaContaClienteServicePort {
    Conta buscar(Long clienteId, Long contaId);
    Conta buscar(Long contaId);
    List<Conta> listar(Long clienteId);
}
