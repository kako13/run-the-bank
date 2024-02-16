package com.kaue.runthebank.application.core.exception;

public class ContaInativaException extends NegocioException {
    private static final String MSG_CONTA_INATIVA = "Conta de id '%d' consta como inativa";

    public ContaInativaException(String mensagem) {
        super(mensagem);
    }

    public ContaInativaException(Long id) {
        this(String.format(MSG_CONTA_INATIVA, id));
    }
}
