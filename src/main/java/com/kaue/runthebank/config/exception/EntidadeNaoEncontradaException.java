package com.kaue.runthebank.config.exception;

public abstract class EntidadeNaoEncontradaException extends NegocioException {
    protected EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
