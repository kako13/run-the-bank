package com.kaue.runthebank.core.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@ToString
@Getter
public abstract class Cliente {
    @EqualsAndHashCode.Include
    Long id;
    String nome;
    String endereco;
    String senha;
    String documento;
    TipoCliente tipoCliente;
    List<Conta> contas;

    public boolean possuiConta(Long idConta) {
        return contas.stream().anyMatch(conta -> conta.getId().equals(idConta));
    }

    public boolean naoPossuiConta(Long idConta) {
        return !possuiConta(idConta);
    }
}