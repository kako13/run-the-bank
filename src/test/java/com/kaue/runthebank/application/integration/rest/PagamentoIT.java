package com.kaue.runthebank.application.integration.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaue.runthebank.adapters.inboud.assembler.cliente.ClienteMapper;
import com.kaue.runthebank.adapters.inboud.assembler.conta.ContaMapper;
import com.kaue.runthebank.adapters.inboud.controller.request.pagamento.PagamentoInput;
import com.kaue.runthebank.adapters.inboud.entity.ClienteEntity;
import com.kaue.runthebank.adapters.inboud.entity.ContaEntity;
import com.kaue.runthebank.adapters.outbound.repository.ClienteRepository;
import com.kaue.runthebank.adapters.outbound.repository.ContaRepository;
import com.kaue.runthebank.application.core.utils.data.ClienteTestData;
import com.kaue.runthebank.application.core.utils.data.ContaTestData;
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

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PagamentoIT {
    @LocalServerPort
    private int port;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private ContaMapper contaMapper;
    @Autowired
    private ObjectMapper objectMapper;
    private ClienteEntity clienteEntity;
    private ContaEntity contaRemetenteEntity;
    private ContaEntity contaDestinatarioEntity;

    @BeforeEach
    void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/banking/contas";
        prepararDados();
    }

    @Test
    void deveRetornarStatus201_QuandoCadastrarClienteComCnpjComFormatacao() throws JsonProcessingException {
        String jsonCorretoPagamento = ResourceUtils.getContentFromResource(
                "/json/correto/pagamento/pagamento.json");
        PagamentoInput pagamentoInput = objectMapper.readValue(jsonCorretoPagamento, PagamentoInput.class);
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(jsonCorretoPagamento)
                .pathParam("contaId", clienteEntity.getId())
        .when()
                .post("/{contaId}/pagamentos")
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("valor", Matchers.equalTo(pagamentoInput.getValor().floatValue()))
                .body("contaRemetente.id", Matchers.equalTo(Integer.valueOf(pagamentoInput.getContaRemetenteId().toString())))
                .body("contaRemetente.agencia", Matchers.equalTo(contaRemetenteEntity.getAgencia()))
                .body("contaDestinatario.id", Matchers.equalTo(Integer.valueOf(pagamentoInput.getContaDestinatarioId().toString())))
                .body("contaDestinatario.agencia", Matchers.equalTo(contaDestinatarioEntity.getAgencia()))
                .body("codigoPagamento", Matchers.notNullValue())
                .body("dataPagamento", Matchers.notNullValue());
    }

    private void prepararDados() {
        clienteEntity = clienteRepository.save(
                clienteMapper.toEntity(ClienteTestData.umClienteNovo().build()));

        contaRemetenteEntity = contaMapper.toEntity(ContaTestData.umaContaAtivaNova()
                .saldo(BigDecimal.valueOf(150.50))
                .agencia("08698-5")
                .build());
        contaRemetenteEntity.setCliente(clienteEntity);
        contaRemetenteEntity = contaRepository.save(contaRemetenteEntity);

        contaDestinatarioEntity = contaMapper.toEntity(ContaTestData.umaContaAtivaNova()
                .saldo(BigDecimal.valueOf(0.50))
                .agencia("08698-5")
                .build());
        contaDestinatarioEntity.setCliente(clienteEntity);
        contaDestinatarioEntity = contaRepository.save(contaDestinatarioEntity);
    }
}
