create table cliente (
	id bigint not null auto_increment,
    nome varchar(255) not null,
    senha varchar(255) not null,
    documento varchar(255) not null UNIQUE,
    tipo_documento varchar(255) check (tipo_documento in ('CPF','CNPJ')),
    endereco varchar(255),
    data_cadastro datetime not null,

    primary key (id)
);