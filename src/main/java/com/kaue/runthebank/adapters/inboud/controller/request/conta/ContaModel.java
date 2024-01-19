package com.kaue.runthebank.adapters.inboud.controller.request.conta;

import com.kaue.runthebank.application.core.domain.StatusConta;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class ContaModel {
    private Long id;
    private String agencia;
    private BigDecimal saldo;
    private StatusConta status;
    private OffsetDateTime dataCadastro;
}
