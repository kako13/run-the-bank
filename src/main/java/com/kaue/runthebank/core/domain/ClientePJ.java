package com.kaue.runthebank.core.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Getter
public class ClientePJ extends Cliente {
    private final String cnpj;

    protected ClientePJ(Long id, String nome, String endereco, String senha, List<Conta> contas, String cnpj) {
        super(id, nome, endereco, senha, cnpj, TipoCliente.PESSOA_JURIDICA, contas);
        this.cnpj = cnpj;
    }

    public static ClientePJ.ClientePJBuilder builder() {
        return new ClientePJ.ClientePJBuilder();
    }

    public static class ClientePJBuilder {
        private Long id;
        private String nome;
        private String endereco;
        private String senha;
        private List<Conta> contas;
        private String cnpj;

        ClientePJBuilder() {
        }

        public ClientePJ.ClientePJBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ClientePJ.ClientePJBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public ClientePJ.ClientePJBuilder endereco(String endereco) {
            this.endereco = endereco;
            return this;
        }

        public ClientePJ.ClientePJBuilder senha(String senha) {
            this.senha = senha;
            return this;
        }

        public ClientePJ.ClientePJBuilder contas(List<Conta> contas) {
            this.contas = contas;
            return this;
        }

        public ClientePJ.ClientePJBuilder cpf(String cpf) {
            this.cnpj = cpf;
            return this;
        }

        public ClientePJ build() {
            return new ClientePJ(id, nome, endereco, senha, contas, cnpj);
        }
    }
}