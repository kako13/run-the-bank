package com.kaue.runthebank.core.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class ClienteTest {
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        prepararDados();
    }

    @Test
    void deveRetornarTrue_QuandoPossuirConta() {
        Assertions.assertThat(cliente.possuiConta(1L)).isTrue();
    }

    @Test
    void deveRetornarTrue_QuandoNaoPossuirConta() {
        Assertions.assertThat(cliente.naoPossuiConta(2L)).isTrue();
    }

    private void prepararDados() {
        cliente = ClientePF.builder()
                .id(1L)
                .nome("João")
                .endereco("Rua A")
                .senha("senha123")
                .cpf("123.456.789-01")
                .contas(List.of(Conta.builder()
                                    .id(1L)
                                    .saldo(BigDecimal.valueOf(1000.50))
                                    .build()))
                .build();
    }
}