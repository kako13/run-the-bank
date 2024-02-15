package com.kaue.runthebank.config.openapi;

public class ClientePayloadsExemplos {
    public static final String EXEMPLO_CLIENTE_DADOS_INVALIDOS = "{\n" +
            "    \"status\": 400,\n" +
            "    \"type\": \"https://run-the-bank.kaue.com/banking/dados-invalidos\",\n" +
            "    \"title\": \"Dados inválidos\",\n" +
            "    \"detail\": \"Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.\",\n" +
            "    \"userMessage\": \"Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.\",\n" +
            "    \"timeStamp\": \"2024-02-11T23:21:06.303167Z\",\n" +
            "    \"objects\": [\n" +
            "        {\n" +
            "            \"name\": \"cliente\",\n" +
            "            \"userMessage\": \"Documento não corresponde ao tipo de documento informado, ou é inválido\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    public static final String EXEMPLO_ERRO_DE_SISTEMA = "{\n" +
            "    \"status\": 500,\"type\": \"https://run-the-bank.kaue.com/banking/erro-de-sistema\",\n" +
            "    \"title\": \"Erro de sistema\",\n" +
            "    \"detail\": \"Ocorreu um erro interno no sistema. Tente novamente e se o problema persistir, entre em contato com o administrador do sistema\",\n" +
            "    \"userMessage\": \"Ocorreu um erro interno no sistema. Tente novamente e se o problema persistir, entre em contato com o administrador do sistema\",\n" +
            "    \"timeStamp\": \"2024-02-11T06:03:26.4820352Z\"\n" +
            "}";
    public static final String EXEMPLO_CLIENTE_NAO_ENCONTRADO = "{\n" +
            "    \"status\": 404,\n" +
            "    \"type\": \"https://run-the-bank.kaue.com/banking/recurso-nao-encontrado\",\n" +
            "    \"title\": \"Recurso não encontrado\",\n" +
            "    \"detail\": \"Não existe um cadastro de cliente com id '40'\",\n" +
            "    \"userMessage\": \"Não existe um cadastro de cliente com id '40'\",\n" +
            "    \"timeStamp\": \"2024-02-11T22:17:21.9833233Z\"\n" +
            "}";
    public static final String EXEMPLO_CLIENTE_INPUT = "{\n" +
            "    \"nome\": \"Juca Oliveira\",\n" +
            "    \"documento\": 456789012490,\n" +
            "    \"endereco\": \"Rua Camaleao\",\n" +
            "    \"celular\": \"13988294673\",\n" +
            "    \"senha\": \"123456\",\n" +
            "    \"tipoDocumento\": \"cpf\"\n" +
            "}";
    public static final String EXEMPLO_CLIENTE_CADASTRADO = "{\n" +
            "    \"id\": 4,\n" +
            "    \"nome\": \"Juca Oliveira\",\n" +
            "    \"celular\": \"13988294673\",\n" +
            "    \"tipoDocumento\": \"CPF\",\n" +
            "    \"dataCadastro\": \"2024-02-11T23:19:03.281493Z\"\n" +
            "}";
    public static final String EXEMPLO_CLIENTE_CONSULTA = "{\n" +
            "    \"id\": 1,\n" +
            "    \"nome\": \"Kayque Lucas\",\n" +
            "    \"endereco\": \"Rua da Formiga, 730\",\n" +
            "    \"celular\": \"11989562374\",\n" +
            "    \"tipoDocumento\": \"CPF\",\n" +
            "    \"documento\": \"123.456.789-09\",\n" +
            "    \"dataCadastro\": \"2024-02-11T23:21:39.081127Z\",\n" +
            "    \"contas\": [\n" +
            "    ]\n" +
            "}";
}
