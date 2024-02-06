package com.kaue.runthebank.application.integration.service.db;

import com.kaue.runthebank.adapters.inboud.assembler.cliente.ClienteMapper;
import com.kaue.runthebank.adapters.inboud.entity.ClienteEntity;
import com.kaue.runthebank.adapters.outbound.repository.ClienteRepository;
import com.kaue.runthebank.adapters.outbound.repository.ContaRepository;
import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.core.service.AberturaContaClienteService;
import com.kaue.runthebank.application.core.utils.data.ClienteTestData;
import com.kaue.runthebank.application.core.utils.data.ContaTestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AberturaContaClienteServiceIT {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private AberturaContaClienteService aberturaContaClienteService;
    private ClienteEntity cliente;
    private Conta contaDomain;

    @BeforeEach
    void setUp() {
        contaRepository.deleteAll();
        clienteRepository.deleteAll();
        prepararDados();
    }

    @Test
    void abrirConta() {
        Conta conta = aberturaContaClienteService.abrirConta(cliente.getId(), contaDomain);
        Assertions.assertThat(conta)
                .isNotNull()
                .hasNoNullFieldsOrPropertiesExcept("cliente", "movimentos");
    }

    private void prepararDados() {
        Cliente clienteDomain = ClienteTestData.umClienteNovo().build();
        ClienteEntity clienteToSave = clienteMapper.toEntity(clienteDomain);
        cliente = clienteRepository.save(clienteToSave);
        contaDomain = ContaTestData.umaContaInativaNova().build();
    }
}