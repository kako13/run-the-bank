package com.kaue.runthebank.adapters.inboud.controller.request.conta;

import com.fasterxml.jackson.annotation.JsonView;
import com.kaue.runthebank.adapters.inboud.controller.request.view.ClienteView;
import com.kaue.runthebank.adapters.inboud.controller.request.view.ContaView;
import com.kaue.runthebank.adapters.inboud.controller.request.view.PagamentoView;
import com.kaue.runthebank.application.core.domain.StatusConta;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class ContaModel {
    @JsonView({ClienteView.Resumo.class, ContaView.Cadastro.class, ContaView.Listagem.class, PagamentoView.Resumo.class,
            PagamentoView.Listagem.class})
    private Long id;
    @JsonView({ClienteView.Resumo.class, ContaView.Cadastro.class, ContaView.Listagem.class, PagamentoView.Resumo.class})
    private String agencia;
    private BigDecimal saldo;
    @JsonView({ClienteView.Resumo.class, ContaView.Cadastro.class, ContaView.Listagem.class})
    private StatusConta status;
    @JsonView({ClienteView.Resumo.class, ContaView.Cadastro.class})
    private OffsetDateTime dataCadastro;
}
