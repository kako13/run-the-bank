package com.kaue.runthebank.application.ports.out.estorno;

import com.kaue.runthebank.application.core.domain.Estorno;

public interface EstornoPagamentoPort {
    Estorno registrarEstorno(Estorno estorno);
}
