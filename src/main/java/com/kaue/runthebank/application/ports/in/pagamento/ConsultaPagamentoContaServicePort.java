package com.kaue.runthebank.application.ports.in.pagamento;

import com.kaue.runthebank.application.core.domain.Pagamento;

import java.util.List;

public interface ConsultaPagamentoContaServicePort {
    List<Pagamento> listar(Long contaId);
    Pagamento buscarPorCodigoEConta(String codigoPagamento, Long contaId);
}
