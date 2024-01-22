package com.kaue.runthebank.application.core.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Conta {
    @EqualsAndHashCode.Include
    private Long id;
    private String agencia;
    private BigDecimal saldo;
    private StatusConta status;
    private OffsetDateTime dataCadastro;

    public boolean temSaldoSuficiente(BigDecimal valorDebito) {
        return valorDebito.compareTo(saldo) <= 0;
    }

    public boolean naoTemSaldoSuficiente(BigDecimal valorDebito) {
        return !temSaldoSuficiente(valorDebito);
    }

    public void debitar(BigDecimal valorDebito) {
        this.saldo=saldo.subtract(valorDebito);
    }

    public void creditar(BigDecimal valorCredito) {
        this.saldo=saldo.add(valorCredito);
    }

    public boolean estaAtiva() {
        return status.equals(StatusConta.ATIVA);
    }

    public boolean estaInativo() {
        return !estaAtiva();
    }
}