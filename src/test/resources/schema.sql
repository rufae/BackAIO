CREATE SCHEMA IF NOT EXISTS aio;

CREATE TABLE IF NOT EXISTS aio.usuario (
   id BIGINT PRIMARY KEY,
   nombre VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS aio.grupo (
    id BIGINT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    fecha_creacion DATE NOT NULL,
    usuarios_id BIGINT,
    CONSTRAINT fk_grupo_usuario FOREIGN KEY (usuarios_id) REFERENCES aio.usuario (id)
);
