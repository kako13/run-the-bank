package com.kaue.runthebank.config.openapi;

public class PagamentoPayloadsExemplos {
    public static final String EXEMPLO_PAGAMENTO_DADOS_INVALIDOS = "{\n" +
            "    \"status\": 400,\n" +
            "    \"type\": \"https://run-the-bank.kaue.com/banking/dados-invalidos\",\n" +
            "    \"title\": \"Dados inválidos\",\n" +
            "    \"detail\": \"Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.\",\n" +
            "    \"userMessage\": \"Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.\",\n" +
            "    \"timeStamp\": \"2024-02-12T02:01:17.8954799Z\",\n" +
            "    \"objects\": [\n" +
            "        {\n" +
            "            \"name\": \"valor\",\n" +
            "            \"userMessage\": \"Valor do pagamento precisar ser maior ou igual a zero.\"\n" +
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
    public static final String EXEMPLO_PAGAMENTO_NAO_ENCONTRADO = "{\n" +
            "    \"status\": 400,\n" +
            "    \"type\": \"https://run-the-bank.kaue.com/banking/erro-negocio\",\n" +
            "    \"title\": \"Violação de regra de negócio\",\n" +
            "    \"detail\": \"Não foi encontrado nenhum pagamento de código '5592fca9-cb8c-4001-afa1-95641580efea' realizado por esta conta.\",\n" +
            "    \"userMessage\": \"Não foi encontrado nenhum pagamento de código '5592fca9-cb8c-4001-afa1-95641580efea' realizado por esta conta.\",\n" +
            "    \"timeStamp\": \"2024-02-12T04:23:54.1821279Z\"\n" +
            "}";
    public static final String EXEMPLO_PAGAMENTO_INPUT = "{\n" +
            "  \"valor\": 100.00,\n" +
            "  \"contaRemetenteId\": 1,\n" +
            "  \"contaDestinatarioId\": 2\n" +
            "}";
    public static final String EXEMPLO_PAGAMENTO_CADASTRADO = "{\n" +
            "    \"valor\": 150,\n" +
            "    \"contaRemetente\": {\n" +
            "        \"id\": 2,\n" +
            "        \"agencia\": \"016\"\n" +
            "    },\n" +
            "    \"contaDestinatario\": {\n" +
            "        \"id\": 1,\n" +
            "        \"agencia\": \"004\"\n" +
            "    },\n" +
            "    \"dataPagamento\": \"2024-02-12T02:08:43.445712Z\",\n" +
            "    \"codigoPagamento\": \"663b6268-b80f-4ada-9000-15ed22c3db35\"\n" +
            "}";
    public static final String EXEMPLO_PAGAMENTO_LISTA = "[\n" +
            "    {\n" +
            "        \"valor\": 100.00,\n" +
            "        \"dataPagamento\": \"2024-02-12T02:11:05.36318Z\",\n" +
            "        \"codigoPagamento\": \"3d28d907-5398-4eb4-97b0-f8ed038e54c4\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"valor\": 100.00,\n" +
            "        \"dataPagamento\": \"2024-02-12T02:11:16.611667Z\",\n" +
            "        \"codigoPagamento\": \"0d60421b-ddf9-44c0-b5b1-642ba1afbe22\"\n" +
            "    }\n" +
            "]";
    public static final String EXEMPLO_PAGAMENTO_CONSULTA = "{\n" +
            "    \"valor\": 100.00,\n" +
            "    \"contaRemetente\": {\n" +
            "        \"id\": 1,\n" +
            "        \"agencia\": \"004\"\n" +
            "    },\n" +
            "    \"contaDestinatario\": {\n" +
            "        \"id\": 2,\n" +
            "        \"agencia\": \"016\"\n" +
            "    },\n" +
            "    \"dataPagamento\": \"2024-02-12T04:27:47.403794Z\",\n" +
            "    \"codigoPagamento\": \"b3b6f441-3cbd-4943-85e5-5841604d2615\"\n" +
            "}";
}
