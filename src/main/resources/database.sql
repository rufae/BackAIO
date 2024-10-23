-- Tabla Clientes
CREATE TABLE Clientes (
                          cliente_id SERIAL PRIMARY KEY,
                          nombre VARCHAR(50) NOT NULL,
                          apellidos VARCHAR(50) NOT NULL,
                          email VARCHAR(100) UNIQUE NOT NULL,
                          telefono VARCHAR(20),
                          direccion TEXT,
                          fecha_nacimiento DATE
);

-- Tabla Usuarios
CREATE TABLE Usuario (
                         usuario_id SERIAL PRIMARY KEY,
                         cliente_id INTEGER REFERENCES Clientes(cliente_id) ON DELETE CASCADE,
                         username VARCHAR(100) UNIQUE NOT NULL,
                         contraseña VARCHAR(100) NOT NULL,
                         rol VARCHAR(50),
                         fecha_registro DATE NOT NULL DEFAULT CURRENT_DATE
);

-- Tabla Grupos
CREATE TABLE Grupos (
                        grupo_id SERIAL PRIMARY KEY,
                        nombre VARCHAR(100) NOT NULL,
                        descripcion TEXT,
                        fecha_creacion DATE NOT NULL DEFAULT CURRENT_DATE
);

-- Tabla Sitios
CREATE TABLE Sitios (
                        sitio_id SERIAL PRIMARY KEY,
                        nombre VARCHAR(100) NOT NULL,
                        tipo VARCHAR(50) NOT NULL,
                        tipo_especifico VARCHAR(50), -- Nueva columna para tipo específico
                        descripcion TEXT,
                        pais VARCHAR(50),
                        region VARCHAR(50),
                        distancia FLOAT,
                        calidad INTEGER CHECK (calidad BETWEEN 1 AND 5),
                        reseñas_publicas VARCHAR(100)
);

-- Tabla Alojamientos
CREATE TABLE Alojamientos (
                              alojamiento_id SERIAL PRIMARY KEY,
                              nombre VARCHAR(100) NOT NULL,
                              descripcion TEXT
);

-- Tabla Excursiones
CREATE TABLE Excursiones (
                             excursion_id SERIAL PRIMARY KEY,
                             nombre VARCHAR(100) NOT NULL,
                             descripcion TEXT
);

-- Tabla Ocio
CREATE TABLE Ocio (
                      ocio_id SERIAL PRIMARY KEY,
                      nombre VARCHAR(100) NOT NULL,
                      descripcion TEXT
);

-- Tabla Restaurantes
CREATE TABLE Restaurantes (
                              restaurante_id SERIAL PRIMARY KEY,
                              nombre VARCHAR(100) NOT NULL,
                              descripcion TEXT
);

-- Tabla Viajes
CREATE TABLE Viajes (
                        viaje_id SERIAL PRIMARY KEY,
                        usuario_id INTEGER REFERENCES Usuario(usuario_id) ON DELETE CASCADE,
                        lugar VARCHAR(100) NOT NULL,
                        fecha_inicio DATE NOT NULL,
                        fecha_vuelta DATE NOT NULL,
                        numero_adultos INTEGER NOT NULL,
                        numero_ninos INTEGER NOT NULL,
                        numero_habitaciones INTEGER
);

-- Tabla Itinerario
CREATE TABLE Itinerario (
                            itinerario_id SERIAL PRIMARY KEY,
                            fecha_ida DATE NOT NULL,
                            fecha_vuelta DATE NOT NULL,
                            viaje_id INTEGER REFERENCES Viajes(viaje_id) ON DELETE CASCADE -- Relación directa con Viajes
);

-- Tabla intermedia Itinerario_Sitios (relación muchos a muchos)
CREATE TABLE Itinerario_Sitios (
                                   itinerario_id INTEGER REFERENCES Itinerario(itinerario_id) ON DELETE CASCADE,
                                   sitio_id INTEGER REFERENCES Sitios(sitio_id) ON DELETE CASCADE,
                                   PRIMARY KEY (itinerario_id, sitio_id)
);

-- Tabla Chat
CREATE TABLE Chat (
                      chat_id SERIAL PRIMARY KEY,
                      grupo_id INTEGER REFERENCES Grupos(grupo_id) ON DELETE CASCADE,
                      usuario_id INTEGER REFERENCES Usuario(usuario_id) ON DELETE CASCADE -- Relación directa con Usuario
);

-- Tabla Mensajes (relación con Chat)
CREATE TABLE Mensajes (
                          mensaje_id SERIAL PRIMARY KEY,
                          chat_id INTEGER REFERENCES Chat(chat_id) ON DELETE CASCADE,
                          usuario_id INTEGER REFERENCES Usuario(usuario_id) ON DELETE CASCADE,
                          contenido TEXT NOT NULL,
                          fecha_envio TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          imagen BYTEA
);

-- Tabla Usuarios_Grupos (relación muchos a muchos)
CREATE TABLE Usuarios_Grupos (
                                 usuario_id INTEGER REFERENCES Usuario(usuario_id) ON DELETE CASCADE,
                                 grupo_id INTEGER REFERENCES Grupos(grupo_id) ON DELETE CASCADE,
                                 PRIMARY KEY (usuario_id, grupo_id)
);

CREATE TABLE Sitios_Tipos (
                              sitio_id INTEGER REFERENCES Sitios(sitio_id) ON DELETE CASCADE,
                              tipo VARCHAR(50) NOT NULL, -- Tipo general como 'Alojamiento', 'Excursion', etc.
                              alojamiento_id INTEGER REFERENCES Alojamientos(alojamiento_id) ON DELETE CASCADE,
                              excursion_id INTEGER REFERENCES Excursiones(excursion_id) ON DELETE CASCADE,
                              ocio_id INTEGER REFERENCES Ocio(ocio_id) ON DELETE CASCADE,
                              restaurante_id INTEGER REFERENCES Restaurantes(restaurante_id) ON DELETE CASCADE,

                              PRIMARY KEY (sitio_id, tipo)
);
