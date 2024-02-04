package com.kaue.runthebank.application.core.utils.data;

import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.core.domain.StatusConta;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;

public class ContaTestData {

    public static Conta.ContaBuilder umaContaAtivaExistente() {
        return Conta.builder()
                .id(1L)
                .agencia("09852-2")
                .status(StatusConta.ATIVA)
                .dataCadastro(OffsetDateTime.now())
                .movimentos(new HashSet<>())
                .saldo(BigDecimal.valueOf(2));
    }
    public static Conta.ContaBuilder umaContaInativaNova() {
        return Conta.builder()
                .agencia("09852-2")
                .status(StatusConta.INATIVA)
                .dataCadastro(OffsetDateTime.now())
                .movimentos(new HashSet<>())
                .saldo(BigDecimal.valueOf(1000.50));
    }
}
