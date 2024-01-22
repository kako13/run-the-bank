package com.kaue.runthebank.adapters.inboud.controller.request.pagamento;

import com.kaue.runthebank.application.core.domain.Conta;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class PagamentoModel {
    private Long id;
    private BigDecimal valor;
    private Conta contaDestinataria;
    private Conta contaRemetente;
    private OffsetDateTime dataPagamento;
    private String codigoPagamento;
}
