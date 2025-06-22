-- Crear base de datos
CREATE DATABASE PlataformaAdolecentes;
GO
USE PlataformaAdolecentes;
GO

-- Tabla usuarios
CREATE TABLE usuarios (
    id INT PRIMARY KEY IDENTITY,
    nombre VARCHAR(100),
    tipo VARCHAR(50),
    email VARCHAR(100),
    contraseña VARCHAR(100)
);

-- Tabla plataformas
CREATE TABLE plataformas (
    id INT PRIMARY KEY IDENTITY,
    nombre VARCHAR(100),
    api_url VARCHAR(255)
);

-- Tabla mensajes
CREATE TABLE mensajes (
    id INT PRIMARY KEY IDENTITY,
    contenido TEXT,
    fecha DATETIME DEFAULT GETDATE(),
    usuario_id INT,
    plataforma_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (plataforma_id) REFERENCES plataformas(id)
);

-- Tabla incidentes
CREATE TABLE incidentes (
    id INT PRIMARY KEY IDENTITY,
    descripcion TEXT,
    frecuencia VARCHAR(50),
    usuario_id INT,
    plataforma_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (plataforma_id) REFERENCES plataformas(id)
);

-- Tabla reportes
CREATE TABLE reportes (
    id INT PRIMARY KEY IDENTITY,
    incidente_id INT,
    nivel_riesgo VARCHAR(50),
    fecha DATE,
    FOREIGN KEY (incidente_id) REFERENCES incidentes(id)
);

-- Tabla intervenciones
CREATE TABLE intervenciones (
    id INT PRIMARY KEY IDENTITY,
    reporte_id INT,
    moderador_id INT,
    psicologo_id INT,
    FOREIGN KEY (reporte_id) REFERENCES reportes(id),
    FOREIGN KEY (moderador_id) REFERENCES usuarios(id),
    FOREIGN KEY (psicologo_id) REFERENCES usuarios(id)
);

-- Tabla acciones
CREATE TABLE acciones (
    id INT PRIMARY KEY IDENTITY,
    descripcion TEXT,
    intervencion_id INT,
    FOREIGN KEY (intervencion_id) REFERENCES intervenciones(id)
);

-- Tabla modulos educativos
CREATE TABLE modulos_educativos (
    id INT PRIMARY KEY IDENTITY,
    titulo VARCHAR(100)
);

-- Tabla estrategias preventivas
CREATE TABLE estrategias_preventivas (
    id INT PRIMARY KEY IDENTITY,
    descripcion TEXT,
    modulo_educativo_id INT,
    FOREIGN KEY (modulo_educativo_id) REFERENCES modulos_educativos(id)
);

-- Tabla recursos del módulo
CREATE TABLE recursos_modulo (
    id INT PRIMARY KEY IDENTITY,
    tipo VARCHAR(50),
    contenido TEXT,
    modulo_educativo_id INT,
    FOREIGN KEY (modulo_educativo_id) REFERENCES modulos_educativos(id)
);

-- Tabla dashboards
CREATE TABLE dashboards (
    id INT PRIMARY KEY IDENTITY,
    usuario_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Tabla métricas
CREATE TABLE metricas (
    id INT PRIMARY KEY IDENTITY,
    nombre VARCHAR(100),
    valor FLOAT,
    dashboard_id INT,
    FOREIGN KEY (dashboard_id) REFERENCES dashboards(id)
);

-- Datos de prueba mínimos
INSERT INTO usuarios (nombre, tipo, email, contraseña) VALUES
('Carlos Mejía', 'psicologo', 'carlos@correo.com', '1234'),
('María Pérez', 'moderador', 'maria@correo.com', 'abcd');

INSERT INTO plataformas (nombre, api_url) VALUES
('Instagram', 'https://api.instagram.com'),
('TikTok', 'https://api.tiktok.com');

INSERT INTO incidentes (descripcion, frecuencia, usuario_id, plataforma_id) VALUES
('Bullying verbal', 'frecuente', 1, 1);

INSERT INTO reportes (incidente_id, nivel_riesgo, fecha) VALUES
(1, 'alto', GETDATE());

-- Procedimiento almacenado
GO
CREATE PROCEDURE RegistrarIntervencionCompleta
    @reporte_id INT,
    @moderador_id INT,
    @psicologo_id INT,
    @descripcion NVARCHAR(255)
AS
BEGIN
    DECLARE @id_intervencion INT;

    INSERT INTO intervenciones (reporte_id, moderador_id, psicologo_id)
    VALUES (@reporte_id, @moderador_id, @psicologo_id);

    SET @id_intervencion = SCOPE_IDENTITY();

    INSERT INTO acciones (descripcion, intervencion_id)
    VALUES (@descripcion, @id_intervencion);
END;
GO