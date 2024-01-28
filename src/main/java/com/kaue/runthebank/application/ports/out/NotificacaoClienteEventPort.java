package com.kaue.runthebank.application.ports.out;

import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.core.domain.Estorno;
import com.kaue.runthebank.application.core.domain.Pagamento;

public interface NotificacaoClienteEventPort {
    void notificarPagamento(Pagamento pagamento, Cliente clienteRemetente, Cliente clienteDestinatario);

    void notificarEstorno(Estorno estorno, Cliente clienteRemetente, Cliente clienteDestinatario);
}
