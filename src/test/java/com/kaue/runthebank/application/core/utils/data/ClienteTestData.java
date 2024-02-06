package com.kaue.runthebank.application.core.utils.data;

import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.core.domain.TipoDocumento;

import java.util.HashSet;

public class ClienteTestData {

    public static Cliente.ClienteBuilder umClienteExistente() {
        return Cliente.builder()
                .id(1L)
                .nome("João")
                .documento("123.456.789-01")
                .endereco("Rua A")
                .celular("13986534218")
                .senha("senha123")
                .contas(new HashSet<>());
    }

    public static Cliente.ClienteBuilder umClienteNovo() {
        return Cliente.builder()
                .nome("João")
                .documento("077.083.120-60")
                .tipoDocumento(TipoDocumento.CPF)
                .endereco("Rua A")
                .celular("13986534218")
                .senha("senha123")
                .contas(new HashSet<>());
    }
}
