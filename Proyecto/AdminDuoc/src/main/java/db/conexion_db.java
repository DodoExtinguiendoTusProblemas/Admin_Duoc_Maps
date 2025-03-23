package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class conexion_db {
    private static final String URL = "jdbc:mysql://localhost:3307/duocmaps"; // Cambia al nombre de tu BD local
    private static final String USUARIO = "root"; // Usuario de tu base de datos local
    private static final String CONTRASENA = "-"; // Contraseña de tu base de datos local

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            throw e; // Re-lanzar la excepción para que se maneje en otro lugar
        }
    }

    public static PreparedStatement prepareStatement(String query) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}


