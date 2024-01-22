package com.kaue.runthebank.adapters.inboud.controller.request.pagamento;

import com.kaue.runthebank.adapters.inboud.controller.request.conta.ContaModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class PagamentoModel {
    private Long id;
    private BigDecimal valor;
    private ContaModel contaDestinataria;
    private ContaModel contaRemetente;
    private OffsetDateTime dataPagamento;
    private String codigoPagamento;
}
