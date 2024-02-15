package com.kaue.runthebank.adapters.inboud.controller.request.cliente;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.kaue.runthebank.adapters.inboud.controller.validation.CPFouCNPJ;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CPFouCNPJ(campoDocumento = "documento", campoTipoDocumento = "tipoDocumento")
@JsonTypeName("cliente")
public class ClienteInput {
    @NotBlank
    @Size(max = 80)
    private String nome;
    @NotBlank
    @Size(max = 15)
    private String celular;
    @NotBlank
    @Size(max = 255)
    private String endereco;
    @NotBlank
    @Size(max = 128)
    private String senha;
    @NotBlank
    private String tipoDocumento;
    @NotBlank
    private String documento;
}

