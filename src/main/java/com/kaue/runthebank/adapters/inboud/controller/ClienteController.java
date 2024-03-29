package com.kaue.runthebank.adapters.inboud.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kaue.runthebank.adapters.inboud.assembler.cliente.ClienteMapper;
import com.kaue.runthebank.adapters.inboud.controller.request.cliente.ClienteInput;
import com.kaue.runthebank.adapters.inboud.controller.request.cliente.ClienteModel;
import com.kaue.runthebank.adapters.inboud.controller.request.view.ClienteView;
import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.ports.in.cliente.CadastroClienteServicePort;
import com.kaue.runthebank.application.ports.in.cliente.ConsultaClienteServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.kaue.runthebank.config.openapi.ClientePayloadsExemplos.*;
import static com.kaue.runthebank.config.openapi.ContaPayloadsExemplos.EXEMPLO_ERRO_DE_SISTEMA;

@Tag(name = "1. Clientes")
@Slf4j
@RestController
@RequestMapping(value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {
    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private ConsultaClienteServicePort consultaClienteServicePort;
    @Autowired
    private CadastroClienteServicePort cadastroClienteServicePort;

    @Operation(summary = "Realiza um cadastro de cliente")
    @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso",
            content = @Content(
                    schema = @Schema(implementation = ClienteModel.class),
                    examples = @ExampleObject(EXEMPLO_CLIENTE_CADASTRADO)
            ))
    @ApiResponse(responseCode = "400", description = "Dados inválidos/Violação de regra de negócio/Mensagem incompreensível",
            content = @Content(
                    schema = @Schema(implementation = ClienteModel.class),
                    examples = @ExampleObject(EXEMPLO_CLIENTE_DADOS_INVALIDOS)
            ))
    @ApiResponse(responseCode = "500", description = "Erro de Sistema",
            content = @Content(
                    schema = @Schema(implementation = ClienteModel.class),
                    examples = @ExampleObject(EXEMPLO_ERRO_DE_SISTEMA)
            ))
    @PostMapping
    @JsonView(ClienteView.Cadastro.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteModel adicionar(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = EXEMPLO_CLIENTE_INPUT)}))
                                      @RequestBody @Valid ClienteInput clienteInput) {
        log.info("Criando cliente");
        Cliente cliente = clienteMapper.toDomainObject(clienteInput);
        Cliente novoCliente = cadastroClienteServicePort.cadastrarCliente(cliente);
        return clienteMapper.toModel(novoCliente);
    }

    @Operation(summary = "Consulta um cliente")
    @ApiResponse(responseCode = "200", description = "Consulta de cliente realizada com sucesso",
            content = @Content(
                    schema = @Schema(implementation = ClienteModel.class),
                    examples = @ExampleObject(EXEMPLO_CLIENTE_CONSULTA)
            ))
    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
            content = @Content(
                    schema = @Schema(implementation = ClienteModel.class),
                    examples = @ExampleObject(EXEMPLO_CLIENTE_NAO_ENCONTRADO)
            ))
    @ApiResponse(responseCode = "500", description = "Erro de Sistema",
            content = @Content(
                    schema = @Schema(implementation = ClienteModel.class),
                    examples = @ExampleObject(EXEMPLO_ERRO_DE_SISTEMA)
            ))
    @GetMapping("/{clienteId}")
    @JsonView(ClienteView.Detalhe.class)
    public ClienteModel buscar(@PathVariable Long clienteId) {
        log.info("Buscando cliente");
        Cliente cliente = consultaClienteServicePort.buscar(clienteId);
        return clienteMapper.toModel(cliente);
    }
}
