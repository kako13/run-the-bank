package com.kaue.runthebank.application.core.utils.data;

import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.core.domain.TipoDocumento;

import java.util.Set;

public class ClienteTestData {

    public static Cliente.ClienteBuilder umClienteExistente() {
        return Cliente.builder()
                .id(1L)
                .nome("João")
                .documento("123.456.789-01")
                .endereco("Rua A")
                .senha("senha123")
                .contas(Set.of(ContaTestData.umaContaAtivaExistente().build()));
    }    public static Cliente.ClienteBuilder umClienteNovo() {
        return Cliente.builder()
                .nome("João")
                .documento("123.456.789-01")
                .tipoDocumento(TipoDocumento.CPF)
                .endereco("Rua A")
                .senha("senha123")
                .contas(Set.of(ContaTestData.umaContaAtivaExistente().build()));
    }
}
