package com.kaue.runthebank.application.ports.in.estorno;

import com.kaue.runthebank.application.core.domain.Estorno;
import com.kaue.runthebank.application.core.domain.Pagamento;

public interface EstornoPagamentoContaServicePort {
    Estorno estornarPagamento(Pagamento pagamento);
}
