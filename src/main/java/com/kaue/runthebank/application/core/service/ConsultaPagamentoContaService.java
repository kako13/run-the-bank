package com.kaue.runthebank.application.core.service;

import com.kaue.runthebank.application.core.domain.Pagamento;
import com.kaue.runthebank.application.core.exception.NegocioException;
import com.kaue.runthebank.application.ports.in.pagamento.ConsultaPagamentoContaServicePort;
import com.kaue.runthebank.application.ports.out.pagamento.ConsultaPagamentoPort;

import java.util.List;

public class ConsultaPagamentoContaService implements ConsultaPagamentoContaServicePort {
    public static final String PAGAMENTO_NAO_ENCONTRADO = "Não foi encontrado nenhum pagamento de código '%s' " +
            "realizado por esta conta.";
    private final ConsultaPagamentoPort consultaPagamentoPort;

    public ConsultaPagamentoContaService(ConsultaPagamentoPort consultaPagamentoPort) {
        this.consultaPagamentoPort = consultaPagamentoPort;
    }

    @Override
    public List<Pagamento> listar(Long contaId) {
        return consultaPagamentoPort.listar(contaId);
    }

    @Override
    public Pagamento buscar(String codigoPagamento) {
        return consultaPagamentoPort.buscar(codigoPagamento)
                .orElseThrow(() -> new NegocioException(String.format(
                        PAGAMENTO_NAO_ENCONTRADO, codigoPagamento)));
    }

    @Override
    public Pagamento buscarPorCodigoEConta(String codigoPagamento, Long contaId) {
        return consultaPagamentoPort.buscarPorCodigoEConta(codigoPagamento, contaId)
                .orElseThrow(() -> new NegocioException(String.format(
                        PAGAMENTO_NAO_ENCONTRADO, codigoPagamento)));
    }
}
