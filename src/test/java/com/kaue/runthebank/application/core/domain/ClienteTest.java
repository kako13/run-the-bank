package com.kaue.runthebank.application.core.domain;

import com.kaue.runthebank.application.core.utils.data.ClienteTestData;
import com.kaue.runthebank.application.core.utils.data.ContaTestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

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
        cliente = ClienteTestData
                .umClienteExistente()
                .contas(Set.of(ContaTestData
                        .umaContaAtivaExistente()
                        .build()))
                .build();
    }
}