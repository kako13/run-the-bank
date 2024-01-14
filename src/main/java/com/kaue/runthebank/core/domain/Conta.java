package com.kaue.runthebank.core.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Getter
public class Conta {
    @EqualsAndHashCode.Include
    private Long id;
    private String agencia;
    private String numero;
    private BigDecimal saldo;
    private Situacao status;
    private List<Movimento> movimentacoes;

    public boolean temSaldoSuficiente(BigDecimal valorDebito) {
        return valorDebito.compareTo(saldo) < 0;
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

    public boolean estaAtivo() {
        return status.equals(Situacao.ATIVA);
    }

    public boolean estaInativo() {
        return !estaAtivo();
    }
}