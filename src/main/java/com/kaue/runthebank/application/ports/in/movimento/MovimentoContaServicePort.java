package com.kaue.runthebank.application.ports.in.movimento;

import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.core.domain.Pagamento;

public interface MovimentoContaServicePort {
    void gerarMovimentoDebito(Pagamento pagamento, Conta contaRemetente);

    void gerarMovimentoCredito(Pagamento pagamento, Conta contaRemetente);
}
