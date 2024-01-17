package com.kaue.runthebank.adapters.inboud.controller;

import com.kaue.runthebank.adapters.inboud.assembler.cliente.ClienteAssembler;
import com.kaue.runthebank.adapters.inboud.assembler.cliente.ClienteDisassembler;
import com.kaue.runthebank.adapters.inboud.controller.request.cliente.ClienteInput;
import com.kaue.runthebank.adapters.inboud.controller.request.cliente.ClienteModel;
import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.ports.in.cliente.ConsultaClienteServicePort;
import com.kaue.runthebank.application.ports.in.cliente.CadastroClienteServicePort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ConsultaClienteServicePort consultaClienteServicePort;
    @Autowired
    private CadastroClienteServicePort cadastroClienteServicePort;
    @Autowired
    private ClienteDisassembler clienteDisassembler;
    @Autowired
    private ClienteAssembler clienteAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteModel adicionar(@RequestBody @Valid ClienteInput clienteInput) {
        Cliente cliente = clienteDisassembler.toDomainObject(clienteInput);
        Cliente novoCliente = cadastroClienteServicePort.cadastrarCliente(cliente);
        return clienteAssembler.toModel(novoCliente);
    }
    @GetMapping("/{clienteId}")
    public ClienteModel buscar(@PathVariable Long clienteId) {
        Cliente cliente = consultaClienteServicePort.buscar(clienteId);
        return clienteAssembler.toModel(cliente);
    }
}
