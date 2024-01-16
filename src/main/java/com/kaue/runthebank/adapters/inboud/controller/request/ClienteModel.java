package com.kaue.runthebank.adapters.inboud.controller.request;

import com.kaue.runthebank.application.core.domain.TipoDocumento;
import lombok.Data;

@Data
public class ClienteModel {
    String nome;
    String endereco;
    String senha;
    TipoDocumento tipoDocumento;
    String documento;
}
