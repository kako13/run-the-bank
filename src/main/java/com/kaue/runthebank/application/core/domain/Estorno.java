package com.kaue.runthebank.application.core.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Getter
@Setter
public class Estorno {
    @EqualsAndHashCode.Include
    private Long id;
    private BigDecimal valor;
    private Conta contaDestinatario;
    private Conta contaRemetente;
    private OffsetDateTime dataEstorno;
    private String codigoEstorno;
    private Pagamento pagamento;
}
