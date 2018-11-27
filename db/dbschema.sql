create table controle_acesso.modulo_permissao
(
	id bigserial not null
		constraint modulo_permissao_pkey
			primary key,
	cd_modulo bigint
		constraint fk_modulo_perm_modulo
			references modulo,
	cd_permissao bigint
		constraint fk_modulo_perm_permissao
			references permissao
)
;

