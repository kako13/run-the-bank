package com.kaue.runthebank.adapters.inboud.controller.request.cliente;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.kaue.runthebank.adapters.inboud.controller.validation.CPFouCNPJ;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CPFouCNPJ(campoDocumento = "documento", campoTipoDocumento = "tipoDocumento")
@JsonTypeName("cliente")
public class ClienteInput {
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @NotBlank
    private String senha;
    @NotBlank
    private String tipoDocumento;
    @NotBlank
    private String documento;
}

