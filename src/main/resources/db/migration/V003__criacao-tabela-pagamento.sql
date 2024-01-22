create table pagamento (
    id bigint not null auto_increment,
    valor numeric(38,2) not null,
    data_pagamento datetime not null,
    codigo_pagamento varchar(36) not null,
    conta_remetente_id bigint not null,
    conta_destinataria_id bigint not null,

    primary key (id),
    unique key uk_codigo_pagamento (codigo_pagamento),
    constraint fk_conta_remetente_pagamento foreign key (conta_remetente_id) references conta (id),
    constraint fk_conta_destinataria_pagamento foreign key (conta_destinataria_id) references conta (id)
);