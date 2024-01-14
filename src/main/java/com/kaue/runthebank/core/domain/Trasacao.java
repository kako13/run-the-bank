package com.kaue.runthebank.core.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.OffsetDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Getter
public class Trasacao {
    @EqualsAndHashCode.Include
    private Long id;
    private OffsetDateTime dataTransacao;
    private Movimento debito;
    private Movimento credito;
}
