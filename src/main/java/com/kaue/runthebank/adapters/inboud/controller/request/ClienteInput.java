package com.kaue.runthebank.adapters.inboud.controller.request;

import com.kaue.runthebank.adapters.inboud.controller.validation.CPFouCNPJ;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ClienteInput {
    String nome;
    String endereco;
    String senha;
    String tipoDocumento;
    @CPFouCNPJ
    String documento;
}
