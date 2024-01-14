package com.kaue.runthebank.core.domain;

import lombok.Getter;

@Getter
public enum Situacao {
    ATIVA("Ativa"),
    INATIVA("Inativa");

    private final String descricao;

    Situacao(String descricao) {
        this.descricao = descricao;
    }
}