# Plataforma de Apoyo a Adolescentes 👥💬

Proyecto académico en Java (Maven + Swing) conectado a SQL Server, diseñado para gestionar usuarios, incidentes e intervenciones en una plataforma de apoyo.

## 🛠️ Tecnologías utilizadas

- Java 17
- Maven
- Swing
- SQL Server
- Cursor
- JDBC

## ⚙️ Requisitos

- SQL Server instalado y corriendo (puerto 1433)
- Usuario `sa` habilitado
- Base de datos creada: `PlataformaApoyo`
- Conexión configurada en `UsuarioDAO.java`

## 🧩 Cómo importar la base de datos

1. Abre SQL Server o DBeaver.
2. Ejecuta el script `base_datos.sql` incluido en este repositorio.
3. Esto creará todas las tablas, datos de prueba y el procedimiento almacenado necesario.
4. Verifica que tu conexión use:
   - Base de datos: `PlataformaAdolecentes`
   - Usuario: `sa`
   - Contraseña: `TuContraseña`

## ▶️ Cómo ejecutar

1. Clona este repositorio:
   ```bash
   git clone https://github.com/tu_usuario/PlataformaApoyo.git
   cd PlataformaApoyo