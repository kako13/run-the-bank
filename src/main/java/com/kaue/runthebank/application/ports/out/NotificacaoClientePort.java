package com.kaue.runthebank.application.ports.out;

import com.kaue.runthebank.application.core.domain.Cliente;

public interface NotificacaoClientePort {
    void notificarCliente(Cliente cliente, String mensagem);
}
