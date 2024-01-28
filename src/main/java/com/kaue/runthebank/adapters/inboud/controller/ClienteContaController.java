package com.kaue.runthebank.adapters.inboud.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kaue.runthebank.adapters.inboud.assembler.conta.ContaMapper;
import com.kaue.runthebank.adapters.inboud.controller.request.conta.ContaInput;
import com.kaue.runthebank.adapters.inboud.controller.request.conta.ContaModel;
import com.kaue.runthebank.adapters.inboud.controller.request.view.ContaView;
import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.ports.in.conta.AberturaContaClienteServicePort;
import com.kaue.runthebank.application.ports.in.conta.ConsultaContaClienteServicePort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes/{clienteId}/contas")
public class ClienteContaController {
    @Autowired
    private ConsultaContaClienteServicePort consultaContaClienteServicePort;
    @Autowired
    private AberturaContaClienteServicePort aberturaContaClienteServicePort;
    @Autowired
    private ContaMapper contaMapper;

    @PostMapping
    @JsonView(ContaView.Cadastro.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ContaModel adicionar(@PathVariable Long clienteId, @RequestBody @Valid ContaInput contaInput) {
        Conta conta = contaMapper.toDomainObject(contaInput);
        Conta novaConta = aberturaContaClienteServicePort.abrirConta(clienteId, conta);
        return contaMapper.toModel(novaConta);
    }

    @GetMapping
    @JsonView(ContaView.Resumo.class)
    public List<ContaModel> listar(@PathVariable Long clienteId) {
        List<Conta> conta = consultaContaClienteServicePort.listar(clienteId);
        return contaMapper.toCollectionModel(conta);
    }
    @GetMapping("/{contaId}")
    public ContaModel buscar(@PathVariable Long clienteId, @PathVariable Long contaId) {
        Conta conta = consultaContaClienteServicePort.buscar(clienteId, contaId);
        return contaMapper.toModel(conta);
    }
}
