package com.kaue.runthebank.application.core.domain;

import lombok.Getter;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum StatusConta {
    ATIVA("ATIVA"),
    INATIVA("INATIVA");

    private final String descricao;
    private static final Map<String, StatusConta> mapStatus = new HashMap<>();

    static {
        for (StatusConta status : StatusConta.values()) {
            mapStatus.put(status.descricao, status);
        }
    }
    StatusConta(String descricao) {
        this.descricao = descricao;
    }

    public static StatusConta pegarPorDescricao(String descricao) {
        return mapStatus.get(descricao);
    }
}