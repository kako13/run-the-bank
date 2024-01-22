package com.kaue.runthebank.application.ports.in.pagamento;

import com.kaue.runthebank.application.core.domain.Pagamento;

import java.util.List;

public interface PagamentoContaServicePort {
    Pagamento transferirValor(Pagamento pagamento);
    List<Pagamento> listar(Long contaId);
}
