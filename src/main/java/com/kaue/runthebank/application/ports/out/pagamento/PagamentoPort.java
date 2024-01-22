package com.kaue.runthebank.application.ports.out.pagamento;

import com.kaue.runthebank.application.core.domain.Pagamento;

import java.util.List;

public interface PagamentoPort {
    Pagamento cadastrarPagamento(Pagamento pagamento);

    List<Pagamento> listar(Long contaId);
}
