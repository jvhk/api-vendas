CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
-- Usa uuid_generate_v4() para gerar os UUIDs (garanta que a extensão uuid-ossp esteja ativa).

CREATE TABLE cliente (
    uuid UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome VARCHAR(255),
    cpf VARCHAR(20),
    email VARCHAR(255),
    telefone VARCHAR(20)
);

CREATE TABLE produto (
    uuid UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome VARCHAR(255),
    descricao TEXT,
    preco NUMERIC(15, 2)
);

CREATE TABLE venda (
    uuid UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    id_cliente UUID,
    data_venda TIMESTAMP,
    valor_venda NUMERIC(15, 2),
    CONSTRAINT fk_venda_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(uuid)
);

CREATE TABLE produtos_vendidos (
    uuid UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    produto_id UUID,
    cliente_id UUID,
    venda_id UUID,
    CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES produto(uuid),
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(uuid),
    CONSTRAINT fk_venda FOREIGN KEY (venda_id) REFERENCES venda(uuid)
);