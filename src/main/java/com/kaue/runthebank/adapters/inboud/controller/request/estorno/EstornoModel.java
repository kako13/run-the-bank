package com.kaue.runthebank.adapters.inboud.controller.request.estorno;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.kaue.runthebank.adapters.inboud.controller.request.conta.ContaModel;
import com.kaue.runthebank.adapters.inboud.controller.request.pagamento.PagamentoModel;
import com.kaue.runthebank.adapters.inboud.controller.request.view.EstornoView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class EstornoModel {
    private Long id;
    @JsonView({EstornoView.Detalhe.class, EstornoView.Resumo.class})
    @JsonProperty(value = "valorEstornado", index = 0)
    private BigDecimal valor;
    private ContaModel contaDestinatario;
    private ContaModel contaRemetente;
    @JsonView({EstornoView.Detalhe.class, EstornoView.Resumo.class})
    private PagamentoModel pagamento;
    @JsonView({EstornoView.Detalhe.class, EstornoView.Resumo.class})
    private OffsetDateTime dataEstorno;
    @JsonView({EstornoView.Detalhe.class, EstornoView.Resumo.class})
    private String codigoEstorno;
}
