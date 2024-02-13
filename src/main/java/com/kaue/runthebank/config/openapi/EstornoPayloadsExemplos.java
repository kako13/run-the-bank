package com.kaue.runthebank.config.openapi;

public class EstornoPayloadsExemplos {
    public static final String EXEMPLO_ESTORNO_DADOS_INVALIDOS = "{\n" +
            "    \"status\": 400,\n" +
            "    \"type\": \"https://run-the-bank.kaue.com/banking/dados-invalidos\",\n" +
            "    \"title\": \"Dados inválidos\",\n" +
            "    \"detail\": \"Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.\",\n" +
            "    \"userMessage\": \"Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.\",\n" +
            "    \"timeStamp\": \"2024-02-12T02:12:36.54975Z\",\n" +
            "    \"objects\": [\n" +
            "        {\n" +
            "            \"name\": \"codigoPagamento\",\n" +
            "            \"userMessage\": \"codigoPagamento é obrigatório.\"\n" +
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
    public static final String EXEMPLO_ESTORNO_NAO_ENCONTRADO = "{\n" +
            "    \"status\": 400,\n" +
            "    \"type\": \"https://run-the-bank.kaue.com/banking/erro-negocio\",\n" +
            "    \"title\": \"Violação de regra de negócio\",\n" +
            "    \"detail\": \"Não foi encontrado nenhum estorno de código 'fd69d05c-9d56-4288-88d9-05d33761b407' realizado por esta conta.\",\n" +
            "    \"userMessage\": \"Não foi encontrado nenhum estorno de código 'fd69d05c-9d56-4288-88d9-05d33761b407' realizado por esta conta.\",\n" +
            "    \"timeStamp\": \"2024-02-12T04:32:48.0160184Z\"\n" +
            "}";
    public static final String EXEMPLO_ESTORNO_INPUT = "{\n" +
            "    \"codigoPagamento\": \"39417fcc-42cd-4d86-b9e1-a7319be61b15\"\n" +
            "}";
    public static final String EXEMPLO_ESTORNO_CADASTRADO = "{\n" +
            "    \"valorEstornado\": 100.00,\n" +
            "    \"pagamento\": {\n" +
            "        \"valor\": 100.00,\n" +
            "        \"contaRemetente\": {\n" +
            "            \"id\": 1,\n" +
            "            \"agencia\": \"004\"\n" +
            "        },\n" +
            "        \"contaDestinatario\": {\n" +
            "            \"id\": 2,\n" +
            "            \"agencia\": \"016\"\n" +
            "        },\n" +
            "        \"dataPagamento\": \"2024-02-12T02:14:41.388225Z\",\n" +
            "        \"codigoPagamento\": \"877145bf-60da-41f7-821c-6c7270a14c82\"\n" +
            "    },\n" +
            "    \"dataEstorno\": \"2024-02-12T02:14:44.648764Z\",\n" +
            "    \"codigoEstorno\": \"39417fcc-42cd-4d86-b9e1-a7319be61b15\"\n" +
            "}";
    public static final String EXEMPLO_ESTORNO_LISTA = "[\n" +
            "    {\n" +
            "        \"valorEstornado\": 100.00,\n" +
            "        \"pagamento\": {\n" +
            "            \"valor\": 100.00,\n" +
            "            \"dataPagamento\": \"2024-02-12T02:16:05.167429Z\",\n" +
            "            \"codigoPagamento\": \"20e039b1-7e47-4073-993b-566280153c36\"\n" +
            "        },\n" +
            "        \"dataEstorno\": \"2024-02-12T02:16:07.389859Z\",\n" +
            "        \"codigoEstorno\": \"eba38016-8dcc-4bb8-bb86-a26d99ff26c7\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"valorEstornado\": 100.00,\n" +
            "        \"pagamento\": {\n" +
            "            \"valor\": 100.00,\n" +
            "            \"dataPagamento\": \"2024-02-12T02:16:35.126867Z\",\n" +
            "            \"codigoPagamento\": \"5592fca9-cb8c-4001-afa1-95641580efea\"\n" +
            "        },\n" +
            "        \"dataEstorno\": \"2024-02-12T02:16:38.878255Z\",\n" +
            "        \"codigoEstorno\": \"43a8f7fb-d83f-44a3-ad0b-f3b5a136af88\"\n" +
            "    }\n" +
            "]";
    public static final String EXEMPLO_ESTORNO_CONSULTA = "{\n" +
            "    \"valorEstornado\": 100.00,\n" +
            "    \"pagamento\": {\n" +
            "        \"valor\": 100.00,\n" +
            "        \"contaRemetente\": {\n" +
            "            \"id\": 1,\n" +
            "            \"agencia\": \"004\"\n" +
            "        },\n" +
            "        \"contaDestinatario\": {\n" +
            "            \"id\": 2,\n" +
            "            \"agencia\": \"016\"\n" +
            "        },\n" +
            "        \"dataPagamento\": \"2024-02-12T04:24:56.111589Z\",\n" +
            "        \"codigoPagamento\": \"3e31918a-11a0-44f8-baba-305d8c92586e\"\n" +
            "    },\n" +
            "    \"dataEstorno\": \"2024-02-12T04:24:58.524405Z\",\n" +
            "    \"codigoEstorno\": \"fd69d05c-9d56-4288-88d9-05d33761b407\"\n" +
            "}";
}
