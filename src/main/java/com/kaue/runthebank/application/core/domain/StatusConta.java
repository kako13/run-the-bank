package com.kaue.runthebank.application.core.domain;

import lombok.Getter;

@Getter
public enum StatusConta {
    ATIVA("Ativa"),
    INATIVA("Inativa");

    private final String descricao;

    StatusConta(String descricao) {
        this.descricao = descricao;
    }
}