package com.kaue.runthebank.adapters.inboud.controller.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
//    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de Sistema"),
    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos");

    private final String title;
    private final String uri;

    ProblemType(String path, String title) {
        this.uri = "https://run-the-bank.kaue.com"+path;
        this.title = title;
    }
}
