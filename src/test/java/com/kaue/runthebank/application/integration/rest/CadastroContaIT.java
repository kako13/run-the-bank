package com.kaue.runthebank.application.integration.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaue.runthebank.adapters.inboud.assembler.cliente.ClienteMapper;
import com.kaue.runthebank.adapters.inboud.controller.request.conta.ContaInput;
import com.kaue.runthebank.adapters.inboud.entity.ClienteEntity;
import com.kaue.runthebank.adapters.outbound.repository.ClienteRepository;
import com.kaue.runthebank.application.core.domain.StatusConta;
import com.kaue.runthebank.application.core.utils.data.ClienteTestData;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CadastroContaIT {
    @LocalServerPort
    private int port;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    ObjectMapper objectMapper;
    private ClienteEntity clienteCadastradoEntity;

    @BeforeEach
    void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/banking/clientes";
        prepararDados();
    }

    @Test
    void deveRetornarStatus201_QuandoCadastrarContaAtiva() throws JsonProcessingException {
        String jsonCorretoContaAtiva = ResourceUtils.getContentFromResource(
                "/json/correto/conta/conta-ativa.json");
        ContaInput contaInput = objectMapper.readValue(jsonCorretoContaAtiva, ContaInput.class);
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(jsonCorretoContaAtiva)
                .pathParam("clienteId", clienteCadastradoEntity.getId())
        .when()
                .post("/{clienteId}/contas")
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", Matchers.isA(Number.class))
                .body("agencia", Matchers.equalTo(contaInput.getAgencia()))
                .body("status", Matchers.equalTo(StatusConta.ATIVA.name()))
                .body("dataCadastro", Matchers.notNullValue());
    }

    @Test
    void deveRetornarStatus201_QuandoCadastrarContaInativa() throws JsonProcessingException {
        String jsonCorretoContaInativa = ResourceUtils.getContentFromResource(
                "/json/correto/conta/conta-inativa.json");
        ContaInput contaInput = objectMapper.readValue(jsonCorretoContaInativa, ContaInput.class);
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(jsonCorretoContaInativa)
                .pathParam("clienteId", clienteCadastradoEntity.getId())
        .when()
                .post("/{clienteId}/contas")
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", Matchers.isA(Number.class))
                .body("agencia", Matchers.equalTo(contaInput.getAgencia()))
                .body("status", Matchers.equalTo(StatusConta.INATIVA.name()))
                .body("dataCadastro", Matchers.notNullValue());
    }

    private void prepararDados() {
        clienteCadastradoEntity = clienteRepository.save(
                clienteMapper.toEntity(ClienteTestData.umClienteNovo().documento("621.479.720-70").build()));
    }
}
