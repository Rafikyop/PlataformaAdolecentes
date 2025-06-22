package com.plataforma.proyecto;

import com.plataforma.proyecto.dao.UsuarioDAO;
import com.plataforma.proyecto.dto.UsuarioDTO;

public class App {
    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();

        // Insertar usuario
        UsuarioDTO nuevo = new UsuarioDTO("Luis", "Moderador", "luis@mail.com", "123456");
        dao.insertarUsuario(nuevo);

        // Listar usuarios
        for (UsuarioDTO u : dao.obtenerUsuarios()) {
            System.out.println("ID: " + u.getId() + " | Nombre: " + u.getNombre());
        }

        // Actualizar tipo
        dao.actualizarTipoUsuario(1, "Psicólogo");

        // Eliminar
        // dao.eliminarUsuario(2);
    }
}
