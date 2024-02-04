package com.kaue.runthebank.adapters.inboud.controller.request;

import com.kaue.runthebank.adapters.inboud.controller.request.conta.ContaModel;
import com.kaue.runthebank.adapters.inboud.controller.request.pagamento.PagamentoModel;
import com.kaue.runthebank.application.core.domain.TipoMovimento;

import java.math.BigDecimal;

public class MovimentoModel {
    private Long id;
    private TipoMovimento tipoMovimento;
    private BigDecimal valor;
    private ContaModel conta;
    private PagamentoModel pagamento;
}
