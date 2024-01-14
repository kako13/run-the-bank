package com.kaue.runthebank.core.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@EqualsAndHashCode
@Builder
@Getter
public class Movimento {
    @EqualsAndHashCode.Include
    private Long id;
    private Conta conta;
    private TipoMovimento tipoMovimento;
    private BigDecimal valor;
}