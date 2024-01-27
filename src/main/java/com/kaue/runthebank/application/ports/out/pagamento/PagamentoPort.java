package com.kaue.runthebank.application.ports.out.pagamento;

import com.kaue.runthebank.application.core.domain.Pagamento;

public interface PagamentoPort {
    Pagamento registrarPagamento(Pagamento pagamento);
}
