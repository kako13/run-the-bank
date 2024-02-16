package com.kaue.runthebank.application.ports.out.estorno;

import com.kaue.runthebank.application.core.domain.Estorno;

import java.util.List;
import java.util.Optional;

public interface ConsultaEstornoPagamentoPort {
    List<Estorno> listar(Long contaId);
    Optional<Estorno> buscar(Long pagamentoId);
    Optional<Estorno> buscarPorCodigoEConta(String codigoEstorno, Long contaRemetenteId);
}
