package com.kaue.runthebank.adapters.inboud.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kaue.runthebank.adapters.inboud.assembler.cliente.ClienteMapper;
import com.kaue.runthebank.adapters.inboud.controller.request.cliente.ClienteInput;
import com.kaue.runthebank.adapters.inboud.controller.request.cliente.ClienteModel;
import com.kaue.runthebank.adapters.inboud.controller.request.view.ClienteView;
import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.ports.in.cliente.CadastroClienteServicePort;
import com.kaue.runthebank.application.ports.in.cliente.ConsultaClienteServicePort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private ConsultaClienteServicePort consultaClienteServicePort;
    @Autowired
    private CadastroClienteServicePort cadastroClienteServicePort;

    @PostMapping
    @JsonView(ClienteView.Cadastro.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteModel adicionar(@RequestBody @Valid ClienteInput clienteInput) {
        Cliente cliente = clienteMapper.toDomainObject(clienteInput);
        Cliente novoCliente = cadastroClienteServicePort.cadastrarCliente(cliente);
        return clienteMapper.toModel(novoCliente);
    }

    @GetMapping("/{clienteId}")
    @JsonView(ClienteView.Resumo.class)
    public ClienteModel buscar(@PathVariable Long clienteId) {
        Cliente cliente = consultaClienteServicePort.buscar(clienteId);
        return clienteMapper.toModel(cliente);
    }
}
