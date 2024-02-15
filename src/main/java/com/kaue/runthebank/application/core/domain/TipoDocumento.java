package com.kaue.runthebank.application.core.domain;

import lombok.Getter;

@Getter
public enum TipoDocumento {
    CPF("Documento pessoa física"),
    CNPJ("Documento pessoa jurídica");

    private final String descricao;

    TipoDocumento(String descricao) {
        this.descricao = descricao;
    }
}