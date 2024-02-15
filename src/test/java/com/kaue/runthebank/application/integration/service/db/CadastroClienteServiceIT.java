package com.kaue.runthebank.application.integration.service.db;

import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.core.service.CadastroClienteService;
import com.kaue.runthebank.application.core.utils.data.ClienteTestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CadastroClienteServiceIT {
    @Autowired
    private CadastroClienteService cadastroClienteService;
    private Cliente clienteDomain;

    @BeforeEach
    void setUp() {
        prepararDados();
    }

    @Test
    void cadastrarCliente() {
        Cliente cliente = cadastroClienteService.cadastrarCliente(clienteDomain);
        Assertions.assertThat(cliente)
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }

    private void prepararDados() {
        clienteDomain = ClienteTestData.umClienteNovo().documento("996.826.940-94").build();
    }
}