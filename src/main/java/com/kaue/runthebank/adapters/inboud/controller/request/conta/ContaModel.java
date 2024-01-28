package com.kaue.runthebank.adapters.inboud.controller.request.conta;

import com.fasterxml.jackson.annotation.JsonView;
import com.kaue.runthebank.adapters.inboud.controller.request.view.ClienteView;
import com.kaue.runthebank.adapters.inboud.controller.request.view.ContaView;
import com.kaue.runthebank.adapters.inboud.controller.request.view.EstornoView;
import com.kaue.runthebank.adapters.inboud.controller.request.view.PagamentoView;
import com.kaue.runthebank.application.core.domain.StatusConta;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class ContaModel {
    @JsonView({ClienteView.Detalhe.class, ContaView.Cadastro.class, ContaView.Resumo.class, PagamentoView.Detalhe.class,
            PagamentoView.Resumo.class, EstornoView.Detalhe.class})
    private Long id;
    @JsonView({ClienteView.Detalhe.class, ContaView.Cadastro.class, ContaView.Resumo.class, PagamentoView.Detalhe.class,
            EstornoView.Detalhe.class})
    private String agencia;
    private BigDecimal saldo;
    @JsonView({ClienteView.Detalhe.class, ContaView.Cadastro.class, ContaView.Resumo.class})
    private StatusConta status;
    @JsonView({ClienteView.Detalhe.class, ContaView.Cadastro.class})
    private OffsetDateTime dataCadastro;
}
