package com.kaue.runthebank.adapters.inboud.controller.request.conta;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.kaue.runthebank.adapters.inboud.controller.validation.ValueOfEnum;
import com.kaue.runthebank.application.core.domain.StatusConta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@JsonTypeName("conta")
public class ContaInput {
    @NotBlank
    private String agencia;
    @NotNull
    private BigDecimal saldo;
    @NotBlank
    @ValueOfEnum(enumClass = StatusConta.class)
    private String status;
}
