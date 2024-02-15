package com.kaue.runthebank.application.integration.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaue.runthebank.adapters.inboud.assembler.cliente.ClienteMapper;
import com.kaue.runthebank.adapters.inboud.assembler.conta.ContaMapper;
import com.kaue.runthebank.adapters.inboud.controller.request.estorno.EstornoInput;
import com.kaue.runthebank.adapters.inboud.entity.ClienteEntity;
import com.kaue.runthebank.adapters.inboud.entity.ContaEntity;
import com.kaue.runthebank.adapters.outbound.repository.ClienteRepository;
import com.kaue.runthebank.adapters.outbound.repository.ContaRepository;
import com.kaue.runthebank.application.core.domain.Pagamento;
import com.kaue.runthebank.application.core.service.PagamentoContaService;
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
class EstornoPagamentoIT {
    @LocalServerPort
    private int port;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private PagamentoContaService pagamentoContaService;
    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private ContaMapper contaMapper;
    @Autowired
    private ObjectMapper objectMapper;
    private ClienteEntity clienteEntity;
    private ContaEntity contaRemetenteEntity;
    private ContaEntity contaDestinatarioEntity;
    private Pagamento pagamento;

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
                "/json/correto/estorno/estorno.json");
        EstornoInput estornoInput = objectMapper.readValue(jsonCorretoPagamento, EstornoInput.class);
        estornoInput.setCodigoPagamento(pagamento.getCodigoPagamento());
        jsonCorretoPagamento = objectMapper.writeValueAsString(estornoInput);

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(jsonCorretoPagamento)
                .pathParam("contaId", contaRemetenteEntity.getId())
        .when()
                .post("/{contaId}/estornos")
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("valorEstornado", Matchers.equalTo(pagamento.getValor().floatValue()))
                .body("pagamento.valor", Matchers.equalTo(pagamento.getValor().floatValue()))
                .body("pagamento.contaRemetente.id", Matchers.equalTo(Integer.valueOf(contaRemetenteEntity.getId().toString())))
                .body("pagamento.contaRemetente.agencia", Matchers.equalTo(contaRemetenteEntity.getAgencia()))
                .body("pagamento.contaDestinatario.id", Matchers.equalTo(Integer.valueOf(contaDestinatarioEntity.getId().toString())))
                .body("pagamento.contaDestinatario.agencia", Matchers.equalTo(contaDestinatarioEntity.getAgencia()))
                .body("pagamento.codigoPagamento", Matchers.notNullValue())
                .body("pagamento.dataPagamento", Matchers.notNullValue())
                .body("codigoEstorno", Matchers.notNullValue())
                .body("dataEstorno", Matchers.notNullValue())
        ;
    }

    private void prepararDados() {
        clienteEntity = clienteRepository.save(
                clienteMapper.toEntity(ClienteTestData.umClienteNovo().build()));

        contaRemetenteEntity = contaMapper.toEntity(ContaTestData.umaContaAtivaNova()
                .saldo(BigDecimal.valueOf(150.50))
                .agencia("004")
                .build());
        contaRemetenteEntity.setCliente(clienteEntity);
        contaRemetenteEntity = contaRepository.save(contaRemetenteEntity);

        contaDestinatarioEntity = contaMapper.toEntity(ContaTestData.umaContaAtivaNova()
                .saldo(BigDecimal.valueOf(0.50))
                .agencia("016")
                .build());
        contaDestinatarioEntity.setCliente(clienteEntity);
        contaDestinatarioEntity = contaRepository.save(contaDestinatarioEntity);

        pagamento = pagamentoContaService.transferirValor(
                Pagamento.builder()
                .contaRemetente(contaMapper.toDomainObject(contaRemetenteEntity))
                .contaDestinatario(contaMapper.toDomainObject(contaDestinatarioEntity))
                .valor(BigDecimal.valueOf(100))
                .estornado(false)
                .build());
    }
}
