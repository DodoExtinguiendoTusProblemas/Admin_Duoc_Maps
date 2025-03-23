package Conexiones;

import Modelos.Usuario;
import java.sql.*;
import db.conexion_db;

public class UsuarioDAO {

    // Método para iniciar sesión
    public Usuario login(String email, String password) {
        Usuario usuario = null;
        String query = "SELECT * FROM usuario WHERE email = ? AND password = ?";

        try (Connection conn = conexion_db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String nombreCompleto = rs.getString("nombre_completo");
                    usuario = new Usuario(id, email, password, nombreCompleto);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
        }

        return usuario;
    }

    // Método para obtener un usuario por ID
    public Usuario obtenerUsuarioPorId(int id) {
        Usuario usuario = null;
        String query = "SELECT * FROM usuario WHERE id = ?";

        try (Connection conn = conexion_db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String nombreCompleto = rs.getString("nombre_completo");
                    usuario = new Usuario(id, email, password, nombreCompleto);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
        }

        return usuario;
    }
    
}
