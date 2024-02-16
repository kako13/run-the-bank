create table estorno (
    id bigint not null auto_increment,
    valor numeric(38,2) not null,
    data_estorno datetime not null,
    codigo_estorno varchar(36) not null,
    conta_remetente_id bigint not null,
    conta_destinatario_id bigint not null,
    pagamento_id bigint not null,

    primary key (id),
    unique key uk_codigo_estorno (codigo_estorno),
    constraint fk_conta_remetente_estorno foreign key (conta_remetente_id) references conta (id),
    constraint fk_conta_destinatario_estorno foreign key (conta_destinatario_id) references conta (id),
    constraint fk_pagamento_estorno foreign key (pagamento_id) references pagamento (id)
);