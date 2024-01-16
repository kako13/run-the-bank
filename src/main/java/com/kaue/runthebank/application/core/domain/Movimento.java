package com.kaue.runthebank.application.core.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Getter
public class Movimento {
    @EqualsAndHashCode.Include
    private Long id;
    private TipoMovimento tipoMovimento;
    private BigDecimal valor;
}