package com.kaue.runthebank.adapters.inboud.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kaue.runthebank.adapters.inboud.assembler.conta.ContaMapper;
import com.kaue.runthebank.adapters.inboud.controller.request.conta.ContaInput;
import com.kaue.runthebank.adapters.inboud.controller.request.conta.ContaModel;
import com.kaue.runthebank.adapters.inboud.controller.request.view.ContaView;
import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.ports.in.conta.AberturaContaClienteServicePort;
import com.kaue.runthebank.application.ports.in.conta.ConsultaContaClienteServicePort;
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

import java.util.List;

import static com.kaue.runthebank.config.openapi.ClientePayloadsExemplos.*;
import static com.kaue.runthebank.config.openapi.ContaPayloadsExemplos.*;
import static com.kaue.runthebank.config.openapi.ContaPayloadsExemplos.EXEMPLO_ERRO_DE_SISTEMA;

@Tag(name = "2. Contas")
@Slf4j
@RestController
@RequestMapping(value = "/clientes/{clienteId}/contas", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteContaController {
    @Autowired
    private ConsultaContaClienteServicePort consultaContaClienteServicePort;
    @Autowired
    private AberturaContaClienteServicePort aberturaContaClienteServicePort;
    @Autowired
    private ContaMapper contaMapper;

    @Operation(summary = "Realiza um cadastro de conta para um cliente")
    @ApiResponse(responseCode = "201", description = "Conta cadastrada com sucesso",
            content = @Content(
                    schema = @Schema(implementation = ContaModel.class),
                    examples = @ExampleObject(EXEMPLO_CONTA_CADASTRADA)
            ))
    @ApiResponse(responseCode = "400", description = "Dados inválidos/Violação de regra de negócio/Mensagem incompreensível",
            content = @Content(
                    schema = @Schema(implementation = ContaModel.class),
                    examples = @ExampleObject(EXEMPLO_CONTA_DADOS_INVALIDOS)
            ))
    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
            content = @Content(
                    schema = @Schema(implementation = ContaModel.class),
                    examples = @ExampleObject(EXEMPLO_CLIENTE_NAO_ENCONTRADO)
            ))
    @ApiResponse(responseCode = "500", description = "Erro de Sistema",
            content = @Content(
                    schema = @Schema(implementation = ContaModel.class),
                    examples = @ExampleObject(EXEMPLO_ERRO_DE_SISTEMA)
            ))
    @PostMapping
    @JsonView(ContaView.Cadastro.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ContaModel adicionar(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = EXEMPLO_CONTA_INPUT)}))
                                    @RequestBody @Valid ContaInput contaInput, @PathVariable Long clienteId) {
        log.info("Criando conta");
        Conta conta = contaMapper.toDomainObjectWithoutCliente(contaInput);
        Conta novaConta = aberturaContaClienteServicePort.abrirConta(clienteId, conta);
        return contaMapper.toModel(novaConta);
    }

    @Operation(summary = "Lista a relação de contas de um cliente")
    @ApiResponse(responseCode = "200", description = "Listagem de contas realizada com sucesso",
            content = @Content(
                    schema = @Schema(implementation = ContaModel.class),
                    examples = @ExampleObject(EXEMPLO_CONTA_LISTA)
            ))
    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
            content = @Content(
                    schema = @Schema(implementation = ContaModel.class),
                    examples = @ExampleObject(EXEMPLO_CLIENTE_NAO_ENCONTRADO)
            ))
    @ApiResponse(responseCode = "500", description = "Erro de Sistema",
            content = @Content(
                    schema = @Schema(implementation = ContaModel.class),
                    examples = @ExampleObject(EXEMPLO_ERRO_DE_SISTEMA)
            ))
    @GetMapping
    @JsonView(ContaView.Resumo.class)
    public List<ContaModel> listar(@PathVariable Long clienteId) {
        log.info("Listando contas");
        List<Conta> conta = consultaContaClienteServicePort.listar(clienteId);
        return contaMapper.toCollectionModel(conta);
    }

    @Operation(summary = "Consulta a conta de um cliente")
    @ApiResponse(responseCode = "200", description = "Consulta de conta realizada com sucesso",
            content = @Content(
                    schema = @Schema(implementation = ContaModel.class),
                    examples = @ExampleObject(EXEMPLO_CONTA_CONSULTA)
            ))
    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
            content = @Content(
                    schema = @Schema(implementation = ContaModel.class),
                    examples = @ExampleObject(EXEMPLO_CONTA_NAO_ENCONTRADA)
            ))
    @ApiResponse(responseCode = "500", description = "Erro de Sistema",
            content = @Content(
                    schema = @Schema(implementation = ContaModel.class),
                    examples = @ExampleObject(EXEMPLO_ERRO_DE_SISTEMA)
            ))
    @GetMapping("/{contaId}")
    public ContaModel buscar(@PathVariable Long clienteId, @PathVariable Long contaId) {
        log.info("Buscando conta");
        Conta conta = consultaContaClienteServicePort.buscar(clienteId, contaId);
        return contaMapper.toModel(conta);
    }
}
