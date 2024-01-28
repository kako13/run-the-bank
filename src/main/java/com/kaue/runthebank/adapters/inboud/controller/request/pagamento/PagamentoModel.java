package com.kaue.runthebank.adapters.inboud.controller.request.pagamento;

import com.fasterxml.jackson.annotation.JsonView;
import com.kaue.runthebank.adapters.inboud.controller.request.conta.ContaModel;
import com.kaue.runthebank.adapters.inboud.controller.request.view.EstornoView;
import com.kaue.runthebank.adapters.inboud.controller.request.view.PagamentoView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class PagamentoModel {
    private Long id;
    @JsonView({PagamentoView.Detalhe.class, PagamentoView.Resumo.class, EstornoView.Detalhe.class, EstornoView.Resumo.class})
    private BigDecimal valor;
    @JsonView({PagamentoView.Detalhe.class})
    private ContaModel contaRemetente;
    @JsonView({PagamentoView.Detalhe.class, EstornoView.Detalhe.class})
    private ContaModel contaDestinatario;
    @JsonView({PagamentoView.Detalhe.class, PagamentoView.Resumo.class, EstornoView.Detalhe.class, EstornoView.Resumo.class})
    private OffsetDateTime dataPagamento;
    @JsonView({PagamentoView.Detalhe.class, PagamentoView.Resumo.class, EstornoView.Detalhe.class, EstornoView.Resumo.class})
    private String codigoPagamento;
}
