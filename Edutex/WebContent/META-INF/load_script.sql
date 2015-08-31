-- VALORES DOS REGIMES TRIBUTARIOS
INSERT INTO REGIMETRIBUTARIOEMPRESA 
VALUES (nextval('regimetributario_sequence'),'Lucro Real');


INSERT INTO REGIMETRIBUTARIOEMPRESA 
VALUES (nextval('regimetributario_sequence'),'Lucro Presumido');

INSERT INTO REGIMETRIBUTARIOEMPRESA 
VALUES (nextval('regimetributario_sequence'),'Simples Nacional');

-- VALORES TIPO STATUS
INSERT INTO TIPOSTATUS (CDSTATUS, NMSTATUS)
	VALUES (1, 'Ativo');

	
INSERT INTO TIPOSTATUS (CDSTATUS, NMSTATUS)
	VALUES (2, 'Desativado');
	
-- VALORES TIPO USUARIO
INSERT INTO TIPOUSUARIO (cdtipousuario,nmtipousuario)
	VALUES (1, 'Administrador');

INSERT INTO TIPOUSUARIO (cdtipousuario,nmtipousuario)
	VALUES (2, 'Comum');
	
insert into finalidadenfe (cdfinalidadenfe, nmfinalidade)
	values (1, 'Industrializa��o');


insert into finalidadenfe (cdfinalidadenfe, nmfinalidade)
	values (2, 'Consumo Final');


insert into finalidadenfe (cdfinalidadenfe, nmfinalidade)
	values (3, 'Revenda');		
	
insert into finalidadenfe (cdfinalidadenfe,nmfinalidade)
	 values (4,'Nota Corretiva');	

Insert into TipoImposto (cdtipoimposto, nmtipoimposto)
values (1, 'ICMS');

Insert into TipoImposto (cdtipoimposto, nmtipoimposto)
values (2, 'PIS');

Insert into TipoImposto (cdtipoimposto, nmtipoimposto)
values (3, 'COFINS');

Insert into TipoImposto (cdtipoimposto, nmtipoimposto)
values (4, 'IPI');

Insert into TipoImposto (cdtipoimposto, nmtipoimposto)
values (5, 'ICMS Interestaduals');

	
-- Carga dos estados(UF) de acordo com a tabela IBGE	
Insert into estado (cdestado, nomeestado) values (11, 'Rond�nia');
Insert into estado (cdestado, nomeestado) values (12, 'Acre');
Insert into estado (cdestado, nomeestado) values (13, 'Amazonas');
Insert into estado (cdestado, nomeestado) values (14, 'Roraima');
Insert into estado (cdestado, nomeestado) values (15, 'Par�');
Insert into estado (cdestado, nomeestado) values (16, 'Amap�');
Insert into estado (cdestado, nomeestado) values (17, 'Tocantins');
Insert into estado (cdestado, nomeestado) values (21, 'Maranh�o');
Insert into estado (cdestado, nomeestado) values (22, 'Piau�');
Insert into estado (cdestado, nomeestado) values (23, 'Cear�');
Insert into estado (cdestado, nomeestado) values (24, 'Rio Grande do Norte');
Insert into estado (cdestado, nomeestado) values (25, 'Para�ba');
Insert into estado (cdestado, nomeestado) values (26, 'Pernambuco');
Insert into estado (cdestado, nomeestado) values (27, 'Alagoas');
Insert into estado (cdestado, nomeestado) values (28, 'Sergipe');
Insert into estado (cdestado, nomeestado) values (29, 'Bahia');
Insert into estado (cdestado, nomeestado) values (31, 'Minas Gerais');
Insert into estado (cdestado, nomeestado) values (32, 'Esp�rito Santo');
Insert into estado (cdestado, nomeestado) values (33, 'Rio de Janeiro');
Insert into estado (cdestado, nomeestado) values (35, 'S�o Paulo');
Insert into estado (cdestado, nomeestado) values (41, 'Paran�');
Insert into estado (cdestado, nomeestado) values (42, 'Santa Catarina');
Insert into estado (cdestado, nomeestado) values (43, 'Rio Grande do Sul');
Insert into estado (cdestado, nomeestado) values (50, 'Mato Grosso do Sul');
Insert into estado (cdestado, nomeestado) values (51, 'Mato Grosso');
Insert into estado (cdestado, nomeestado) values (52, 'Goi�s');
Insert into estado (cdestado, nomeestado) values (53, 'Distrito Federal');

--USU�RIO E EMPRESA PADR�O

insert into Empresa
values(1,20517672000186,'Empresa Teste',2,1);

insert into Usuario
values(1,'password',null,current_timestamp,'admin@admin.com','Administrador',1,1);

insert into usuarioempresa
values(1,1);