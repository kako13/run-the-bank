_______________________________________________________________________________
<p>
 <img src="https://img.shields.io/static/v1?label=Tipo&message=Desafio&color=8257E5&labelColor=000000" alt="Desafio " />
</p>

###### Projeto destinado ao desafio [Back-End Challenge - Contas Correntes](https://github.com/vieiraitalo/Back-End-Challenge/blob/main/readme.md) convertido para fins de estudos dos paradigmas da implementação da arquitetura hexagonal. 

###
##### Projeto Spring-Boot para cadastro de:
_______________________________________________________________________________
- Clientes
- Contas
- Pagamentos (transferência entre contas)
- Estornos de pagamentos
- Notificar as partes através de um serviço REST externo (terceiro)

###
#### Tecnologias
_______________________________________________________________________________
* [JDK 21](https://jdk.java.net/21/)
* [Spring Boot 3.2.1](https://spring.io/projects/spring-boot)
* [Spring Web](https://docs.spring.io/spring-boot/docs/current/reference/html/web.html)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa#overview)
* [H2](https://www.h2database.com/html/main.html)
* [Flyway](https://mvnrepository.com/artifact/org.flywaydb/flyway-core)
* [REST Assured]()
* [MapStruct](https://mapstruct.org)
* [Lombok](https://projectlombok.org/features/)
* [Spring Docs - OpenAPI](https://springdoc.org)
* [Docker Maven Plugin](https://github.com/spotify/dockerfile-maven)
* [Apache Camel](https://camel.apache.org/camel-spring-boot/next/spring-boot.html#_auto_configured_consumer_and_producer_templates)
    * [Direct](https://camel.apache.org/components/3.14.x/direct-component.html)
    * [SEDA](https://camel.apache.org/components/3.14.x/seda-component.html)
    * [Async HTTP Client (AHC)](https://camel.apache.org/components/3.14.x/ahc-component.html)

###
### Como rodar
_______________________________________________________________________________
##### **Docker** (Dispensa JDK 21)

```
docker pull kaue13/public-dev:run-the-bank
```

```
docker run --rm -p 8080:8080 kaue13/public-dev:run-the-bank
```

###
#### Maven embedado (Requer JDK 21)

```
./mvnw mvn spring-boot:run
```

Ao iniciar a aplicação será possível visualizar:

```
2024-02-16T06:31:12.684Z  INFO 1896 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/banking'
2024-02-16T06:31:13.340Z  INFO 1896 --- [  restartedMain] o.a.c.impl.engine.AbstractCamelContext   : Apache Camel 4.0.0-RC1 (camel-1) is starting
2024-02-16T06:31:13.670Z  INFO 1896 --- [  restartedMain] o.a.c.impl.engine.AbstractCamelContext   : Routes startup (started:2)
2024-02-16T06:31:13.670Z  INFO 1896 --- [  restartedMain] o.a.c.impl.engine.AbstractCamelContext   :     Started route1 (seda://asyncNotification)
2024-02-16T06:31:13.671Z  INFO 1896 --- [  restartedMain] o.a.c.impl.engine.AbstractCamelContext   :     Started route2 (direct://sendNotification)
2024-02-16T06:31:13.671Z  INFO 1896 --- [  restartedMain] o.a.c.impl.engine.AbstractCamelContext   : Apache Camel 4.0.0-RC1 (camel-1) started in 330ms (build:0ms init:0ms start:330ms)
2024-02-16T06:31:13.674Z  INFO 1896 --- [  restartedMain] c.kaue.runthebank.RunTheBankApplication  : Started RunTheBankApplication in 37.014 seconds (process running for 38.253)

```

###
### Sobre os serviços
_______________________________________________________________________________

Uma vez que a aplicação esteja rodando, basta realizar as requisições disponíveis na pasta [src/test/resources/json], ou utilizar a página do swagger da documentação [spring doc](http://localhost:8080/banking/swagger-ui/index.html#/1.%20Clientes/buscar). Vez que estamos utilizando H2, alguns dados já estarão cadastrados.

- É permitido apenas um cliente por documento, seja CPF ou CNPJ

```
POST http://localhost:8080/banking/clientes
```

```
{
    "nome": "Juca Oliveira",
    "documento": 45678901249,
    "endereco": "Rua Camaleao",
    "celular": "13988294673",
    "senha": "123456",
    "tipoDocumento": "cpf"
}
```

```
{
    "status": 400,
    "type": "https://run-the-bank.fly.dev/banking/erro-negocio",
    "title": "Violação de regra de negócio",
    "detail": "Já existe um cadastro de cliente com o documento informado",
    "userMessage": "Já existe um cadastro de cliente com o documento informado",
    "timeStamp": "2024-02-16T07:28:38.990137775Z"
}
```
###

- São permitidas N contas por cliente

```
GET http://localhost:8080/banking/clientes/1
```

```
{
    "id": 1,
    "nome": "Kayque Lucas",
    "endereco": "Rua da Formiga, 730",
    "celular": "11989562374",
    "tipoDocumento": "CPF",
    "documento": "123.456.789-09",
    "dataCadastro": "2024-02-16T06:30:52.427161Z",
    "contas": [
        {
            "id": 2,
            "agencia": "016",
            "status": "ATIVA",
            "dataCadastro": "2024-02-16T06:30:52.430543Z"
        },
        {
            "id": 1,
            "agencia": "004",
            "status": "ATIVA",
            "dataCadastro": "2024-02-16T06:30:52.42905Z"
        }
    ]
}
```
###

- Ao criar uma conta é possível definir o valor do saldo

```
GET http://localhost:8080/banking/clientes/1/contas/1
```

```
{
    "id": 1,
    "agencia": "004",
    "saldo": 0.00,
    "status": "ATIVA",
    "dataCadastro": "2024-02-16T06:30:52.42905Z"
}
```
###

- Se possuir saldo, uma conta pode fazer um pagamento (transferência)

```
POST http://localhost:8080/banking/contas/1/pagamentos
```

```
{
  "valor": 100.00,
  "contaRemetenteId": 1,
  "contaDestinatarioId": 2
}
```

```
{
    "valor": 100.00,
    "contaRemetente": {
        "id": 1,
        "agencia": "004"
    },
    "contaDestinatario": {
        "id": 2,
        "agencia": "016"
    },
    "dataPagamento": "2024-02-16T07:38:33.256537Z",
    "codigoPagamento": "bf788dd4-78ef-49af-acda-093e68a1625e"
}
```
###

- Qualquer pagamento pode ser estornado através do código de pagamento

```
POST http://localhost:8080/banking/contas/1/estornos
```

```
{

    "codigoPagamento": "bf788dd4-78ef-49af-acda-093e68a1625e"

}
```

```
{
    "valorEstornado": 100.00,
    "pagamento": {
        "valor": 100.00,
        "contaRemetente": {
            "id": 1,
            "agencia": "004"
        },
        "contaDestinatario": {
            "id": 2,
            "agencia": "016"
        },
        "dataPagamento": "2024-02-16T07:38:33.256537Z",
        "codigoPagamento": "bf788dd4-78ef-49af-acda-093e68a1625e"
    },
    "dataEstorno": "2024-02-16T07:39:59.929704Z",
    "codigoEstorno": "ba3ecf90-91f8-40f0-b19a-f22cca2aee2e"
}
```
###

- Ao realizar um pagamento ou estorno ambas as partes serão notificadas, e as mensagens serão apresentadas no log (apenas para fins de estudos), assim como o resultados de chamada do serviço REST externo Mocky ([utilizando mocky próprio](https://run.mocky.io/v3/c9eadae1-d5fb-44a5-aa7c-292d93522094)).

```
2024-02-16T07:43:06.727Z  INFO 1896 --- [yncNotification] route1                                   : Mensagem que será encaminhada ao serviço externo: {"celular":"11989562374","mensagem":"Pagamento realizado no valor de'R$ 100.00' para 'Kayque Lucas' na conta '2' e agência '016'."}
2024-02-16T07:43:06.743Z  INFO 1896 --- [yncNotification] route1                                   : Mensagem que será encaminhada ao serviço externo: {"celular":"11989562374","mensagem":"Você recebeu um pagamento de 'Kayque Lucas'."}
2024-02-16T07:43:07.602Z  INFO 1896 --- [- AhcWorkerPool] route1                                   : Notificação enviada ao serviço externo
 {
  "messageSent": true
}
2024-02-16T07:43:07.602Z  INFO 1896 --- [- AhcWorkerPool] route1                                   : Notificação enviada ao serviço externo
 {
  "messageSent": true
}
```

###
### Sobre o serviço de notificação
_______________________________________________________________________________

Foi sugerido pelo desafio a chamada do serviço REST externo Mocky ([utilizando mocky próprio](https://run.mocky.io/v3/c9eadae1-d5fb-44a5-aa7c-292d93522094)), que em casos positivos retorna "200":
```
{
  "messageSent": true
}
```
###
**Utilizando rota `direct`:**

O componente Direct fornece invocação síncrona de qualquer consumidor quando um produtor envia uma troca de mensagens,
neste caso via `ProducerTemplate`. 

###
**Utilizando rota fila de assíncrona `seda`:** 

O componente SEDA fornece invocação assíncrona de qualquer consumidor quando um produtor envia uma troca de mensagens, 
neste caso via `direct`.

###
**Utilizando componente `AHC`:**

O componente AHC fornece terminais baseados em HTTP para consumir recursos HTTP externos (como um cliente para chamar 
servidores externos usando HTTP).

* [Apache Camel](https://camel.apache.org/camel-spring-boot/next/spring-boot.html#_auto_configured_consumer_and_producer_templates)
  * [Direct](https://camel.apache.org/components/3.14.x/direct-component.html)
  * [SEDA](https://camel.apache.org/components/3.14.x/seda-component.html)
  * [Async HTTP Client (AHC)](https://camel.apache.org/components/3.14.x/ahc-component.html)

####
#### _* É possível que o projeto sofra melhorias para fins de estudo._


