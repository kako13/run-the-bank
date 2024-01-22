create table cliente (
	id bigint not null auto_increment,
    nome varchar(80) not null,
    endereco varchar(255) not null,
    senha varchar(128) not null,
    celular varchar(15) not null,
    documento varchar(18) not null UNIQUE,
    tipo_documento varchar(4) check (tipo_documento in ('CPF','CNPJ')) not null,
    data_cadastro datetime not null,

    primary key (id)
);