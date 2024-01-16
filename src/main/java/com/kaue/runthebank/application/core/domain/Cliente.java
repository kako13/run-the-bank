package com.kaue.runthebank.application.core.domain;

import lombok.*;

import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Cliente {
    @EqualsAndHashCode.Include
    Long id;
    String nome;
    String endereco;
    String senha;
    String documento;
    TipoDocumento tipoDocumento;

    Set<Conta> contas;

    public boolean possuiConta(Long idConta) {
        return contas.stream().anyMatch(conta -> conta.getId().equals(idConta));
    }

    public boolean naoPossuiConta(Long idConta) {
        return !possuiConta(idConta);
    }
}