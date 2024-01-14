package com.kaue.runthebank.core.domain;

import lombok.Getter;

@Getter
public enum TipoMovimento {
    CREDITO("Crédito"),
    DEBITO("Débito")
    ;

    private final String descricao;

    TipoMovimento(String descricao) {
        this.descricao = descricao;
    }
}