create table conta (
	id bigint not null auto_increment,
    agencia varchar(255) not null,
    saldo numeric(38,2) not null,
    cliente_id bigint not null,
    status varchar(255) check (status in ('ATIVA','INATIVA')) not null,
    data_cadastro datetime not null,

    primary key (id, agencia)
);

alter table if exists conta add constraint fk_cliente_conta foreign key (cliente_id) references cliente;