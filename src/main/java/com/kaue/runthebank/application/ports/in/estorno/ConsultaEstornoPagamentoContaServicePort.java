package com.kaue.runthebank.application.ports.in.estorno;

import com.kaue.runthebank.application.core.domain.Estorno;

import java.util.List;

public interface ConsultaEstornoPagamentoContaServicePort {
    List<Estorno> listar(Long contaId);
    Estorno buscar(Long pagamentoId);
    Estorno buscarPorCodigoEConta(String codigoEstorno, Long contaRemetenteId);
}
