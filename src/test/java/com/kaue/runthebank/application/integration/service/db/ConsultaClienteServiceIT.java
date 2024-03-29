package com.kaue.runthebank.application.integration.service.db;

import com.kaue.runthebank.adapters.inboud.assembler.cliente.ClienteMapper;
import com.kaue.runthebank.adapters.inboud.assembler.conta.ContaMapper;
import com.kaue.runthebank.adapters.inboud.entity.ClienteEntity;
import com.kaue.runthebank.adapters.inboud.entity.ContaEntity;
import com.kaue.runthebank.adapters.outbound.repository.ClienteRepository;
import com.kaue.runthebank.adapters.outbound.repository.ContaRepository;
import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.core.service.ConsultaClienteService;
import com.kaue.runthebank.application.core.utils.data.ClienteTestData;
import com.kaue.runthebank.application.core.utils.data.ContaTestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.util.Set;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaClienteServiceIT {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private ContaMapper contaMapper;
    @Autowired
    private ConsultaClienteService consultaClienteService;
    private ClienteEntity cliente;
    private Conta contaDomain;
    private ContaEntity conta;

    @BeforeEach
    void setUp() {
        prepararDados();
    }

    @Test
    void consultarCliente() {
        Cliente clienteDomain = consultaClienteService.buscar(cliente.getId());
        Set<Conta> contas = clienteDomain.getContas();
        Assertions.assertThat(contas)
                        .isNotEmpty();
        Assertions.assertThat(clienteDomain)
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }

    private void prepararDados() {
        Cliente clienteDomain = ClienteTestData.umClienteNovo().documento("459.375.770-35").build();
        ClienteEntity clienteToSave = clienteMapper.toEntity(clienteDomain);
        cliente = clienteRepository.save(clienteToSave);
        contaDomain = ContaTestData.umaContaInativaNova().build();
        ContaEntity contaEntity = contaMapper.toEntity(contaDomain);
        contaEntity.setCliente(cliente);
        conta = contaRepository.save(contaEntity);
    }
}