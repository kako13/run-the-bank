package com.kaue.runthebank.core.domain;

import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.core.domain.StatusConta;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ContaTest {
    private static Conta contaPf;
    private static Conta contaPj;

    @BeforeEach
    void setUp() {
        preparDados();
    }

    @Test
    void deveRetornarTrue_QuandoTemSaldoSuficienteParaDebitarUmValor() {
        Assertions.assertThat(contaPj.temSaldoSuficiente(BigDecimal.TEN)).isTrue();
    }

    @Test
    void deveRetornarTrue_QuandoNaoTemSaldoSuficienteParaDebitarUmValor() {
        Assertions.assertThat(contaPf.naoTemSaldoSuficiente(BigDecimal.TEN)).isTrue();
    }

    @Test
    void deveDeduzirDoSaldo_QuandoForDebitado() {
        contaPf.debitar(BigDecimal.TWO);
        Assertions.assertThat(contaPf.getSaldo()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    void deveAdicionarAoSaldo_QuandoForCreditado() {
        contaPf.creditar(BigDecimal.TWO);
        Assertions.assertThat(contaPf.getSaldo()).isEqualTo(BigDecimal.valueOf(4));
    }

    @Test
    void deveRetornarTrue_QuandoUsuarioEstaAtivo() {
        Assertions.assertThat(contaPf.estaAtivo()).isTrue();
    }

    @Test
    void deveRetornarTrue_QuandoUsuarioEstaInativo() {
        Assertions.assertThat(contaPj.estaInativo()).isTrue();
    }

    private static void preparDados() {
        contaPf = Conta.builder()
                .saldo(BigDecimal.valueOf(2))
                .status(StatusConta.ATIVA)
                .build();
        contaPj = Conta.builder()
                .saldo(BigDecimal.valueOf(1000.50))
                .status(StatusConta.INATIVA)
                .build();
    }
}