package com.kaue.runthebank.adapters.inboud.controller.request.cliente;

import com.kaue.runthebank.adapters.inboud.controller.validation.CPFouCNPJ;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ClienteInput {
    private String nome;
    private String endereco;
    private String senha;
    private String tipoDocumento;
    @CPFouCNPJ
    private String documento;
}
