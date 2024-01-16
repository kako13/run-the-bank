package com.kaue.runthebank.adapters.inboud.controller;

import com.kaue.runthebank.adapters.inboud.assembler.ClienteAssembler;
import com.kaue.runthebank.adapters.inboud.controller.request.ClienteInput;
import com.kaue.runthebank.adapters.inboud.controller.request.ClienteModel;
import com.kaue.runthebank.adapters.inboud.assembler.ClienteDisassembler;
import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.ports.in.CadastrarClienteServicePort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private CadastrarClienteServicePort cadastrarClienteServicePort;
    @Autowired
    private ClienteDisassembler clienteDisassembler;
    @Autowired
    private ClienteAssembler clienteAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteModel add(@RequestBody @Valid ClienteInput clienteInput) {
        Cliente cliente = clienteDisassembler.toDomainObject(clienteInput);
        Cliente novoCliente = cadastrarClienteServicePort.cadastrarCliente(cliente);
        return clienteAssembler.toModel(novoCliente);
    }
}
