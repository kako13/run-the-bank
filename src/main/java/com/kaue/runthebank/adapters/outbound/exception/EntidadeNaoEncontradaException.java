package com.kaue.runthebank.adapters.outbound.exception;

public abstract class EntidadeNaoEncontradaException extends RuntimeException {
    protected EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
