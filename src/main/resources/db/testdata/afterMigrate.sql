insert into
cliente (nome, senha, celular, documento, tipo_documento, endereco, data_cadastro)
values
('Kayque Lucas', '123456', '11989562374', '123.456.789-09', 'CPF', 'Rua da Formiga, 730', CURRENT_TIMESTAMP() AT TIME ZONE 'UTC');

insert into
cliente (nome, senha, celular, documento, tipo_documento, endereco, data_cadastro)
values
('Helio Otctávio', '234567', '63915948263', '234.567.890-92', 'CPF', 'Rua da Matilha, 351', CURRENT_TIMESTAMP() AT TIME ZONE 'UTC');

insert into
cliente (nome, senha, celular, documento, tipo_documento, endereco, data_cadastro)
values
('Kauê Danilo', '345678', '13988294673', '345.678.901-75', 'CPF', 'Rua do Bando, 832', CURRENT_TIMESTAMP() AT TIME ZONE 'UTC');

insert into
conta (agencia, cliente_id, saldo, status, data_cadastro)
values
('004', 1, 100, 'ATIVA', CURRENT_TIMESTAMP() AT TIME ZONE 'UTC');

insert into
conta (agencia, cliente_id, saldo, status, data_cadastro)
values
('016', 1, 500, 'ATIVA', CURRENT_TIMESTAMP() AT TIME ZONE 'UTC');

insert into
conta (agencia, cliente_id, saldo, status, data_cadastro)
values
('256', 2, 1000, 'ATIVA', CURRENT_TIMESTAMP() AT TIME ZONE 'UTC');