package Conexiones;

import Modelos.Consejero;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import db.conexion_db;
        
public class ConsejeroDAO {

    // Usaremos la conexión proporcionada por la clase ConexionDB
    private Connection connection;

    public ConsejeroDAO() {
        try {
            this.connection = conexion_db.getConnection(); // Obtiene la conexión desde la clase ConexionDB
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos en ConsejeroDAO: " + e.getMessage());
        }
    }

    // Agregar un consejero a la base de datos
    public void agregarConsejero(Consejero consejero) {
        String sql = "INSERT INTO consejeros (nombre_completo, email, carrera, ruta_imagen) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, consejero.getNombreCompleto());
            stmt.setString(2, consejero.getEmail());
            stmt.setString(3, consejero.getCarrera());
            stmt.setString(4, consejero.getRutaImagen());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al agregar el consejero: " + e.getMessage());
        }
    }

    // Listar todos los consejeros de la base de datos
    public List<Consejero> listarConsejeros() {
        List<Consejero> consejeros = new ArrayList<>();
        String sql = "SELECT * FROM consejeros";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Consejero consejero = new Consejero(
                    rs.getInt("id"),
                    rs.getString("nombre_completo"),
                    rs.getString("email"),
                    rs.getString("carrera"),
                    rs.getString("ruta_imagen")
                );
                consejeros.add(consejero);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los consejeros: " + e.getMessage());
        }
        return consejeros;
    }

    // Buscar un consejero por ID
    public Consejero buscarConsejero(int id) {
        Consejero consejero = null;
        String sql = "SELECT * FROM consejeros WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    consejero = new Consejero(
                        rs.getInt("id"),
                        rs.getString("nombre_completo"),
                        rs.getString("email"),
                        rs.getString("carrera"),
                        rs.getString("ruta_imagen")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el consejero: " + e.getMessage());
        }
        return consejero;
    }

    // Actualizar un consejero existente
    public void actualizarConsejero(Consejero consejero) {
        String sql = "UPDATE consejeros SET nombre_completo = ?, email = ?, carrera = ?, ruta_imagen = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, consejero.getNombreCompleto());
            stmt.setString(2, consejero.getEmail());
            stmt.setString(3, consejero.getCarrera());
            stmt.setString(4, consejero.getRutaImagen());
            stmt.setInt(5, consejero.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el consejero: " + e.getMessage());
        }
    }

    // Eliminar un consejero por ID
    public void eliminarConsejero(int id) {
        String sql = "DELETE FROM consejeros WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el consejero: " + e.getMessage());
        }
    }
}
