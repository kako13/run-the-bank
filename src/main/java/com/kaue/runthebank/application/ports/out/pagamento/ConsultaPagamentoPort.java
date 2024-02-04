package com.kaue.runthebank.application.ports.out.pagamento;

import com.kaue.runthebank.application.core.domain.Pagamento;

import java.util.List;
import java.util.Optional;

public interface ConsultaPagamentoPort {
    List<Pagamento> listar(Long contaId);
    Optional<Pagamento> buscarPorCodigoEConta(String codigoPagamento, Long contaId);
}
