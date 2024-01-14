package com.kaue.runthebank.core.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Getter
public class ClientePF extends Cliente {
    private final String cpf;

    protected ClientePF(Long id, String nome, String endereco, String senha, List<Conta> contas, String cpf) {
        super(id, nome, endereco, senha, cpf, TipoCliente.PESSOA_FISICA, contas);
        this.cpf = cpf;
    }

    public static ClientePFBuilder builder() {
        return new ClientePFBuilder();
    }

    public static class ClientePFBuilder {
        private Long id;
        private String nome;
        private String endereco;
        private String senha;
        private List<Conta> contas;
        private String cpf;

        ClientePFBuilder() {
        }

        public ClientePFBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ClientePFBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public ClientePFBuilder endereco(String endereco) {
            this.endereco = endereco;
            return this;
        }

        public ClientePFBuilder senha(String senha) {
            this.senha = senha;
            return this;
        }

        public ClientePFBuilder contas(List<Conta> contas) {
            this.contas = contas;
            return this;
        }

        public ClientePFBuilder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public ClientePF build() {
            return new ClientePF(id, nome, endereco, senha, contas, cpf);
        }
    }
}