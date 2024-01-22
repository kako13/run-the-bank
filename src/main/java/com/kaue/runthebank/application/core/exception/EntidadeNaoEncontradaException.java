package com.kaue.runthebank.application.core.exception;

public abstract class EntidadeNaoEncontradaException extends NegocioException {
    protected EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
