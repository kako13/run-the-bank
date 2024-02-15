create table conta (
	id bigint not null auto_increment,
    agencia varchar(8) not null,
    saldo numeric(38,2) not null,
    cliente_id bigint not null,
    status varchar(7) check (status in ('ATIVA','INATIVA')) not null,
    data_cadastro datetime not null,

    primary key (id),
    unique key uk_id_agencia (id, agencia),
    constraint fk_cliente_conta foreign key (cliente_id) references cliente (id)
);