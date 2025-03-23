package Conexiones;

import Modelos.Sala;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import db.conexion_db;

public class SalaDAO {

    private Connection connection;

    public SalaDAO() {
        try {
            this.connection = conexion_db.getConnection(); // Usando la conexión de ConexionDB
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos en SalaDAO: " + e.getMessage());
        }
    }

    // Agregar una nueva sala
    public void agregarSala(Sala sala) {
        String sql = "INSERT INTO sala (titulo, descripcion) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sala.getTitulo());
            stmt.setString(2, sala.getDescripcion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar la sala: " + e.getMessage(), e);
        }
    }

    // Listar todas las salas
    public List<Sala> listarSalas() {
        List<Sala> salas = new ArrayList<>();
        String sql = "SELECT * FROM sala";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Sala sala = new Sala(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("descripcion")
                );
                salas.add(sala);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las salas: " + e.getMessage(), e);
        }
        return salas;
    }

    // Buscar una sala por ID
    public Sala buscarSala(int id) {
        Sala sala = null;
        String sql = "SELECT * FROM sala WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    sala = new Sala(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descripcion")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la sala: " + e.getMessage(), e);
        }
        return sala;
    }

    // Actualizar una sala
    public void actualizarSala(Sala sala) {
        String sql = "UPDATE sala SET titulo = ?, descripcion = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sala.getTitulo());
            stmt.setString(2, sala.getDescripcion());
            stmt.setInt(3, sala.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la sala: " + e.getMessage(), e);
        }
    }

    // Eliminar una sala por ID
    public void eliminarSala(int id) {
        String sql = "DELETE FROM sala WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la sala: " + e.getMessage(), e);
        }
    }
}
