package com.plataforma.proyecto.dao;

import com.plataforma.proyecto.dto.IntervencionDTO;
import java.sql.*;

public class IntervencionDAO {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=PlataformaApoyo;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "RafikyData09072304*";

    public void registrarIntervencion(IntervencionDTO intervencion) {
        String sql = "{call RegistrarIntervencionCompleta(?, ?, ?, ?)}";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, intervencion.getReporteId());
            stmt.setInt(2, intervencion.getModeradorId());
            stmt.setInt(3, intervencion.getPsicologoId());
            stmt.setString(4, intervencion.getDescripcionAccion());

            stmt.execute();
            System.out.println("Intervención registrada exitosamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}