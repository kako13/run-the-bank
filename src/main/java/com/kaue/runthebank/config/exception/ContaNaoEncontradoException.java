package com.kaue.runthebank.config.exception;

public class ContaNaoEncontradoException extends EntidadeNaoEncontradaException {
    public static final String MSG_CONTA_NAO_ENCONTRADO = "Não existe um cadastro de conta com id '%d'";

    public ContaNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ContaNaoEncontradoException(Long id) {
        this(String.format(MSG_CONTA_NAO_ENCONTRADO, id));
    }
}
