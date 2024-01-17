package com.kaue.runthebank.adapters.outbound.exception;

public class ClienteNaoEncontradoException extends EntidadeNaoEncontradaException {
    public static final String MSG_CLIENTE_NAO_ENCONTRADO = "Não existe um cadastro de cliente com id '%d'";

    public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ClienteNaoEncontradoException(Long id) {
        this(String.format(MSG_CLIENTE_NAO_ENCONTRADO, id));
    }
}
