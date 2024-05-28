    CREATE TABLE ENDERECOS (
            ID BIGINT PRIMARY KEY AUTO_INCREMENT,
            LOGRADOURO VARCHAR(100),
            CEP VARCHAR(8),
            BAIRRO VARCHAR(50),
            CIDADE VARCHAR(30),
            ESTADO VARCHAR(30),
            NUMERO INT,
            COMPLEMENTO VARCHAR(100)
    );

    CREATE TABLE EVENTOS (
            ID BIGINT PRIMARY KEY AUTO_INCREMENT,
            NOME VARCHAR(100),
            DESCRICAO TEXT,
            DATA DATE,
            HORARIO TIME,
            TAXA DECIMAL,
            LOCAL VARCHAR(100),
            LOTE_ATUAL INT,
            VALOR_ATUAL DECIMAL(4,2),
            QUANTIDADE_MAX INT,
            QUANTIDADE_ATUAL INT,
            IMAGEM_ID BIGINT
    );

    CREATE TABLE EVENTO_IMAGENS (
            ID BIGINT PRIMARY KEY AUTO_INCREMENT,
            EVENTO_ID BIGINT,
            IMAGEM LONGBLOB,
            CONTENT_TYPE VARCHAR(10),
            EVENTO_ID BIGINT
    );

    CREATE TABLE PESSOAS (
            ID INTEGER PRIMARY KEY AUTO_INCREMENT,
            CPF VARCHAR(14),
            NOME VARCHAR(60),
            DATA_DE_NASCIMENTO DATE,
            ENDERECO_ID BIGINT
    );

    CREATE TABLE TRANSACOES_INGRESSO (
            ID BINARY(16) PRIMARY KEY,
            LOTE_COMPRADO INT,
            VALOR_PAGO DECIMAL(10, 2),
            URL VARCHAR(255),
            PESSOA_ID INTEGER UNIQUE,
            EVENTO_ID INTEGER UNIQUE

    );

    ALTER TABLE EVENTOS
    ADD FOREIGN KEY(IMAGEM_ID) REFERENCES EVENTO_IMAGENS(ID);

    ALTER TABLE PESSOAS
    ADD FOREIGN KEY(ENDERECO_ID) REFERENCES ENDERECOS(ID);

    ALTER TABLE TRANSACOES_INGRESSO
    ADD FOREIGN KEY(PESSOA_ID) REFERENCES PESSOAS(ID);

    ALTER TABLE TRANSACOES_INGRESSO
    ADD FOREIGN KEY(EVENTO_ID) REFERENCES EVENTOS(ID);

    ALTER TABLE EVENTO_IMAGENS
    ADD FOREIGN KEY(EVENTO_ID) REFERENCES EVENTOS(ID);
