package com.kaue.runthebank.application.integration.service.db;

import com.kaue.runthebank.adapters.inboud.assembler.cliente.ClienteMapper;
import com.kaue.runthebank.adapters.inboud.assembler.conta.ContaMapper;
import com.kaue.runthebank.adapters.inboud.entity.ClienteEntity;
import com.kaue.runthebank.adapters.inboud.entity.ContaEntity;
import com.kaue.runthebank.adapters.outbound.repository.ClienteRepository;
import com.kaue.runthebank.adapters.outbound.repository.ContaRepository;
import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.core.service.ConsultaContaClienteService;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaContaClienteServiceIT {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private ContaMapper contaMapper;
    @Autowired
    private ConsultaContaClienteService consultaContaClienteService;
    private ClienteEntity cliente;
    private Conta contaDomain;
    private ContaEntity conta;

    @BeforeEach
    void setUp() {
        prepararDados();
    }

    @Test
    void consultarConta() {
        Conta conta = consultaContaClienteService.buscar(cliente.getId(), this.conta.getId());
        Assertions.assertThat(conta)
                .isNotNull()
                .hasNoNullFieldsOrPropertiesExcept("movimentos", "cliente");
    }

    private void prepararDados() {
        Cliente clienteDomain = ClienteTestData.umClienteNovo().build();
        ClienteEntity clienteToSave = clienteMapper.toEntity(clienteDomain);
        cliente = clienteRepository.save(clienteToSave);
        contaDomain = ContaTestData.umaContaInativaNova().build();
        ContaEntity contaEntity = contaMapper.toEntity(contaDomain);
        contaEntity.setCliente(cliente);
        conta = contaRepository.save(contaEntity);
    }
}