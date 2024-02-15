package com.kaue.runthebank.application.ports.in.pagamento;

import com.kaue.runthebank.application.core.domain.Pagamento;

public interface PagamentoContaServicePort {
    Pagamento transferirValor(Pagamento pagamento);
}
