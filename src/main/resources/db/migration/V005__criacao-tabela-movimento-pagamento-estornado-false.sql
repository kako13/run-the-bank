alter table pagamento alter column estornado boolean default false;

create table movimento (
    id bigint not null auto_increment,
    conta_id bigint,
    pagamento_id bigint,
    valor numeric(38,2),
    tipo_movimento varchar(255) check (tipo_movimento in ('CREDITO','DEBITO')),

    primary key (id)
);