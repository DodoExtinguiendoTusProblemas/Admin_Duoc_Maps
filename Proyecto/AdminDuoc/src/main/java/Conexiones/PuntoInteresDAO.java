package Conexiones;

import Modelos.PuntoInteres;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import db.conexion_db;

public class PuntoInteresDAO {

    private Connection connection;

    public PuntoInteresDAO() {
        try {
            this.connection = conexion_db.getConnection(); // Usando la conexión de ConexionDB
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos en PuntoInteresDAO: " + e.getMessage());
        }
    }

    // Agregar un nuevo punto de interés
    public void agregarPuntoInteres(PuntoInteres puntoInteres) {
        String sql = "INSERT INTO punto_interes (titulo, descripcion, ruta_imagen) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, puntoInteres.getTitulo());
            stmt.setString(2, puntoInteres.getDescripcion());
            stmt.setString(3, puntoInteres.getRutaImagen());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al agregar el punto de interés: " + e.getMessage());
        }
    }

    // Listar todos los puntos de interés
    public List<PuntoInteres> listarPuntosInteres() {
        List<PuntoInteres> puntos = new ArrayList<>();
        String sql = "SELECT * FROM punto_interes";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                PuntoInteres punto = new PuntoInteres(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("descripcion"),
                    rs.getString("ruta_imagen")
                );
                puntos.add(punto);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los puntos de interés: " + e.getMessage());
        }
        return puntos;
    }

    // Buscar un punto de interés por ID
    public PuntoInteres buscarPuntoInteres(int id) {
        PuntoInteres punto = null;
        String sql = "SELECT * FROM punto_interes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    punto = new PuntoInteres(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("ruta_imagen")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el punto de interés: " + e.getMessage());
        }
        return punto;
    }

    // Actualizar un punto de interés
    public void actualizarPuntoInteres(PuntoInteres puntoInteres) {
        String sql = "UPDATE punto_interes SET titulo = ?, descripcion = ?, ruta_imagen = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, puntoInteres.getTitulo());
            stmt.setString(2, puntoInteres.getDescripcion());
            stmt.setString(3, puntoInteres.getRutaImagen());
            stmt.setInt(4, puntoInteres.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el punto de interés: " + e.getMessage());
        }
    }

    // Eliminar un punto de interés por ID
    public void eliminarPuntoInteres(int id) {
        String sql = "DELETE FROM punto_interes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el punto de interés: " + e.getMessage());
        }
    }
}
