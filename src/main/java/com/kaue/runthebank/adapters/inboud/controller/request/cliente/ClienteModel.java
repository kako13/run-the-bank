package com.kaue.runthebank.adapters.inboud.controller.request.cliente;

import com.kaue.runthebank.adapters.inboud.controller.request.conta.ContaModel;
import com.kaue.runthebank.application.core.domain.TipoDocumento;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class ClienteModel {
    private Long id;
    private String nome;
    private String endereco;
    private String senha;
    private TipoDocumento tipoDocumento;
    private String documento;
    private OffsetDateTime dataCadastro;
    private Set<ContaModel> contas = new HashSet<>();
}
