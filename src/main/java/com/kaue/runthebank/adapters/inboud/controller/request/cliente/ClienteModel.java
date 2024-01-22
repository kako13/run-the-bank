package com.kaue.runthebank.adapters.inboud.controller.request.cliente;

import com.fasterxml.jackson.annotation.JsonView;
import com.kaue.runthebank.adapters.inboud.controller.request.conta.ContaModel;
import com.kaue.runthebank.adapters.inboud.controller.request.view.ClienteView;
import com.kaue.runthebank.application.core.domain.TipoDocumento;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ClienteModel {
    @JsonView({ClienteView.Resumo.class, ClienteView.Cadastro.class})
    private Long id;
    @JsonView({ClienteView.Resumo.class, ClienteView.Cadastro.class})
    private String nome;
    @JsonView(ClienteView.Resumo.class)
    private String endereco;
    private String senha;
    @JsonView({ClienteView.Resumo.class, ClienteView.Cadastro.class})
    private String celular;
    @JsonView({ClienteView.Resumo.class, ClienteView.Cadastro.class})
    private TipoDocumento tipoDocumento;
    @JsonView(ClienteView.Resumo.class)
    private String documento;
    @JsonView({ClienteView.Resumo.class, ClienteView.Cadastro.class})
    private OffsetDateTime dataCadastro;
    @JsonView(ClienteView.Resumo.class)
    private Set<ContaModel> contas = new HashSet<>();
}
