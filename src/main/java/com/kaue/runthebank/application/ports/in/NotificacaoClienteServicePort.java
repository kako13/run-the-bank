package com.kaue.runthebank.application.ports.in;

import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.core.domain.Estorno;
import com.kaue.runthebank.application.core.domain.Pagamento;

public interface NotificacaoClienteServicePort {
    void notificarPagamento(Pagamento pagamento, Cliente clienteRemetente, Cliente clienteDestinatario);
    void notificarEstorno(Estorno estorno, Cliente clienteRemetente, Cliente clienteDestinatario);
}
