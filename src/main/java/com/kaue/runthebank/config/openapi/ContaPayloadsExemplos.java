package com.kaue.runthebank.config.openapi;

public class ContaPayloadsExemplos {
    public static final String EXEMPLO_CONTA_DADOS_INVALIDOS = "{\n" +
            "    \"status\": 400,\n" +
            "    \"type\": \"https://run-the-bank.kaue.com/banking/dados-invalidos\",\n" +
            "    \"title\": \"Dados inválidos\",\n" +
            "    \"detail\": \"Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.\",\n" +
            "    \"userMessage\": \"Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.\",\n" +
            "    \"timeStamp\": \"2024-02-11T22:25:00.5936088Z\",\n" +
            "    \"objects\": [\n" +
            "        {\n" +
            "            \"name\": \"saldo\",\n" +
            "            \"userMessage\": \"Saldo da conta precisar ser maior ou igual a zero.\"\n" +
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
    public static final String EXEMPLO_CONTA_NAO_ENCONTRADA = "{\n" +
            "    \"status\": 404,\n" +
            "    \"type\": \"https://run-the-bank.kaue.com/banking/recurso-nao-encontrado\",\n" +
            "    \"title\": \"Recurso não encontrado\",\n" +
            "    \"detail\": \"Não existe um cadastro de conta com id '10' para o cliente '1'\",\n" +
            "    \"userMessage\": \"Não existe um cadastro de conta com id '10' para o cliente '1'\",\n" +
            "    \"timeStamp\": \"2024-02-11T22:44:40.138704Z\"\n" +
            "}";
    public static final String EXEMPLO_CONTA_INPUT = "{\n" +
            "    \"agencia\": \"016\",\n" +
            "    \"saldo\": 100000,\n" +
            "    \"status\": \"ativa\"\n" +
            "}";
    public static final String EXEMPLO_CONTA_CADASTRADA = "{\n" +
            "    \"id\": 2,\n" +
            "    \"agencia\": \"016\",\n" +
            "    \"status\": \"ATIVA\",\n" +
            "    \"dataCadastro\": \"2024-02-11T22:49:16.165262Z\"\n" +
            "}";
    public static final String EXEMPLO_CONTA_LISTA = "[\n" +
            "    {\n" +
            "        \"id\": 2,\n" +
            "        \"agencia\": \"016\",\n" +
            "        \"status\": \"ATIVA\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 1,\n" +
            "        \"agencia\": \"004\",\n" +
            "        \"status\": \"ATIVA\"\n" +
            "    }\n" +
            "]";
    public static final String EXEMPLO_CONTA_CONSULTA = "{\n" +
            "    \"id\": 2,\n" +
            "    \"agencia\": \"004\",\n" +
            "    \"saldo\": 100.00,\n" +
            "    \"status\": \"ATIVA\",\n" +
            "    \"dataCadastro\": \"2024-02-12T03:17:43.398191Z\"\n" +
            "}";
}
