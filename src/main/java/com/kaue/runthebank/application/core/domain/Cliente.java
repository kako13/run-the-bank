package com.kaue.runthebank.application.core.domain;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Cliente {
    @EqualsAndHashCode.Include
    private Long id;
    private String nome;
    private String endereco;
    private String senha;
    private String celular;
    private String documento;
    private TipoDocumento tipoDocumento;
    private OffsetDateTime dataCadastro;

    private Set<Conta> contas = new HashSet<>();

    public boolean possuiConta(Long idConta) {
        return contas.stream().anyMatch(conta -> conta.getId().equals(idConta));
    }

    public boolean naoPossuiConta(Long idConta) {
        return !possuiConta(idConta);
    }
}