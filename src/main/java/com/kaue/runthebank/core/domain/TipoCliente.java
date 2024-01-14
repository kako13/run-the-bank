package com.kaue.runthebank.core.domain;

import lombok.Getter;

@Getter
public enum TipoCliente {
    PESSOA_FISICA("Pessoa física"), 
    PESSOA_JURIDICA("Pessoa jurídica");

    private final String descricao;

    TipoCliente(String descricao) {
        this.descricao = descricao;
    }
}