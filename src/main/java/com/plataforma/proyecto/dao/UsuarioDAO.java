package com.plataforma.proyecto.dao;

import com.plataforma.proyecto.dto.UsuarioDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private final String URL = "jdbc:sqlserver://localhost:1433;databaseName=PlataformaApoyo;encrypt=true;trustServerCertificate=true";
    private final String USER = "sa";
    private final String PASSWORD = "RafikyData09072304*";

    // INSERTAR usuario
    public void insertarUsuario(UsuarioDTO usuario) {
        String sql = "INSERT INTO usuarios (nombre, tipo, email, contraseña) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getTipo());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getContraseña());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // LISTAR usuarios
    public List<UsuarioDTO> obtenerUsuarios() {
        List<UsuarioDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                UsuarioDTO u = new UsuarioDTO();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setTipo(rs.getString("tipo"));
                u.setEmail(rs.getString("email"));
                u.setContraseña(rs.getString("contraseña"));
                lista.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // ACTUALIZAR tipo por ID
    public void actualizarTipoUsuario(int id, String nuevoTipo) {
        String sql = "UPDATE usuarios SET tipo = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nuevoTipo);
            stmt.setInt(2, id);

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Tipo de usuario actualizado.");
            } else {
                System.out.println("No se encontró usuario con ese ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ELIMINAR por ID
    public void eliminarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
                
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Usuario eliminado.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
