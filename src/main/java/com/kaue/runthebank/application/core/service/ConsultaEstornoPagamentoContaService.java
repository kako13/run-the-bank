package com.kaue.runthebank.application.core.service;

import com.kaue.runthebank.application.core.domain.Estorno;
import com.kaue.runthebank.application.core.exception.NegocioException;
import com.kaue.runthebank.application.ports.in.estorno.ConsultaEstornoPagamentoContaServicePort;
import com.kaue.runthebank.application.ports.out.estorno.ConsultaEstornoPagamentoPort;

import java.util.List;

public class ConsultaEstornoPagamentoContaService implements ConsultaEstornoPagamentoContaServicePort {
    public static final String MSG_ESTORNO_NAO_ENCONTRADO = "Não foi encontrado nenhum estorno de código '%s' " +
            "realizado por esta conta.";
    private final ConsultaEstornoPagamentoPort consultaEstornoPagamentoPort;

    public ConsultaEstornoPagamentoContaService(ConsultaEstornoPagamentoPort consultaEstornoPagamentoPort) {
        this.consultaEstornoPagamentoPort = consultaEstornoPagamentoPort;
    }

    @Override
    public List<Estorno> listar(Long contaId) {
        return consultaEstornoPagamentoPort.listar(contaId);
    }

    @Override
    public Estorno buscar(Long pagamentoId) {
        return consultaEstornoPagamentoPort.buscar(pagamentoId)
                .orElseThrow(() -> new NegocioException("Estorno não encontrado"));
    }

    @Override
    public Estorno buscarPorCodigoEConta(String codigoEstorno, Long contaRemetenteId) {
        return consultaEstornoPagamentoPort.buscarPorCodigoEConta(codigoEstorno, contaRemetenteId)
                .orElseThrow(() -> new NegocioException(String.format(
                        MSG_ESTORNO_NAO_ENCONTRADO, codigoEstorno)));
    }
}
