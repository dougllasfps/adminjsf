CREATE SCHEMA controle_acesso;

CREATE TABLE controle_acesso.permissao (
  id        BIGSERIAL NOT NULL PRIMARY KEY,
  descricao VARCHAR(60),
  label     VARCHAR(50)
);