package com.kaue.runthebank.config.exception;

public abstract class EntidadeNaoEncontradaException extends RuntimeException {
    protected EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
