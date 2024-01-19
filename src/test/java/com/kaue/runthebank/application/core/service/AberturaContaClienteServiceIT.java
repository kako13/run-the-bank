package com.kaue.runthebank.application.core.service;

import com.kaue.runthebank.adapters.inboud.assembler.cliente.ClienteAssembler;
import com.kaue.runthebank.adapters.inboud.entity.ClienteEntity;
import com.kaue.runthebank.adapters.outbound.repository.ClienteRepository;
import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.core.domain.Conta;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class AberturaContaClienteServiceIT {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteAssembler clienteAssembler;

    @Autowired
    private AberturaContaClienteService aberturaContaClienteService;
    private ClienteEntity cliente;
    private Conta contaDomain;

    @BeforeEach
    void setUp() {
        prepararDados();
    }

    @Test
    void abrirConta() {
        Conta conta = aberturaContaClienteService.abrirConta(cliente.getId(), contaDomain);
        Assertions.assertThat(conta)
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }

    private void prepararDados() {
        Cliente clienteDomain = ClienteTestData.umClienteNovo().build();
        ClienteEntity clienteToSave = clienteAssembler.toEntity(clienteDomain);
        cliente = clienteRepository.save(clienteToSave);
        contaDomain = ContaTestData.umaContaInativaNova().build();
    }
}