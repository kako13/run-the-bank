package com.kaue.runthebank.application.integration.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaue.runthebank.adapters.inboud.controller.request.cliente.ClienteInput;
import com.kaue.runthebank.application.core.utils.data.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CadastroClienteIT {
    @LocalServerPort
    private int port;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/clientes";
    }

    @Test
    void deveRetornarStatus201_QuandoCadastrarClienteComCnpjComFormatacao() throws JsonProcessingException {
        String jsonCorretoClienteCnpjComFormatacao = ResourceUtils.getContentFromResource(
                "/json/correto/cliente/cliente-cnpj-com-formatação.json");
        ClienteInput clienteInput = objectMapper.readValue(jsonCorretoClienteCnpjComFormatacao, ClienteInput.class);
        given()
                .body(jsonCorretoClienteCnpjComFormatacao)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", Matchers.isA(Number.class))
                .body("nome", Matchers.equalTo(clienteInput.getNome()))
                .body("celular", Matchers.equalTo(clienteInput.getCelular()))
                .body("tipoDocumento", Matchers.equalTo(clienteInput.getTipoDocumento().toUpperCase()))
                .body("dataCadastro", Matchers.notNullValue());
    }

    @Test
    void deveRetornarStatus201_QuandoCadastrarClienteComCnpjSemFormatacao() throws JsonProcessingException {
        String jsonCorretoClienteCnpjSemFormatacao = ResourceUtils.getContentFromResource(
                "/json/correto/cliente/cliente-cnpj-sem-formatação.json");
        ClienteInput clienteInput = objectMapper.readValue(jsonCorretoClienteCnpjSemFormatacao, ClienteInput.class);
        given()
                .body(jsonCorretoClienteCnpjSemFormatacao)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", Matchers.isA(Number.class))
                .body("nome", Matchers.equalTo(clienteInput.getNome()))
                .body("celular", Matchers.equalTo(clienteInput.getCelular()))
                .body("tipoDocumento", Matchers.equalTo(clienteInput.getTipoDocumento().toUpperCase()))
                .body("dataCadastro", Matchers.notNullValue());
    }

    @Test
    void deveRetornarStatus201_QuandoCadastrarClienteComCpfComFormatacao() throws JsonProcessingException {
        String jsonCorretoClienteCpfComFormatacao = ResourceUtils.getContentFromResource(
                "/json/correto/cliente/cliente-cpf-com-formatação.json");
        ClienteInput clienteInput = objectMapper.readValue(jsonCorretoClienteCpfComFormatacao, ClienteInput.class);
        given()
                .body(jsonCorretoClienteCpfComFormatacao)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", Matchers.isA(Number.class))
                .body("nome", Matchers.equalTo(clienteInput.getNome()))
                .body("celular", Matchers.equalTo(clienteInput.getCelular()))
                .body("tipoDocumento", Matchers.equalTo(clienteInput.getTipoDocumento().toUpperCase()))
                .body("dataCadastro", Matchers.notNullValue());
    }

    @Test
    void deveRetornarStatus201_QuandoCadastrarClienteComCpfSemFormatacao() throws JsonProcessingException {
        String jsonCorretoClienteCpfSemFormatacao = ResourceUtils.getContentFromResource(
                "/json/correto/cliente/cliente-cpf-sem-formatação.json");
        ClienteInput clienteInput = objectMapper.readValue(jsonCorretoClienteCpfSemFormatacao, ClienteInput.class);
        given()
                .body(jsonCorretoClienteCpfSemFormatacao)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", Matchers.isA(Number.class))
                .body("nome", Matchers.equalTo(clienteInput.getNome()))
                .body("celular", Matchers.equalTo(clienteInput.getCelular()))
                .body("tipoDocumento", Matchers.equalTo(clienteInput.getTipoDocumento().toUpperCase()))
                .body("dataCadastro", Matchers.notNullValue());
    }
}
