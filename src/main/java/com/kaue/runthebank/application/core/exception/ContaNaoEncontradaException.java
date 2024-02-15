package com.kaue.runthebank.application.core.exception;

public class ContaNaoEncontradaException extends EntidadeNaoEncontradaException {
    public static final String MSG_CONTA_NAO_ENCONTRADO = "Não existe um cadastro de conta com id '%d'";

    public ContaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public ContaNaoEncontradaException(Long id) {
        this(String.format(MSG_CONTA_NAO_ENCONTRADO, id));
    }
}
