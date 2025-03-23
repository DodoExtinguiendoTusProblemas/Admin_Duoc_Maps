package Conexiones;

import Modelos.Preguntas_Frecuentes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import db.conexion_db;

public class Pregunta_FrecuenteDAO {

    private Connection connection;

    public Pregunta_FrecuenteDAO() {
        try {
            this.connection = conexion_db.getConnection();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos en Pregunta_FrecuenteDAO: " + e.getMessage());
        }
    }

    public void agregarPreguntaFrecuente(Preguntas_Frecuentes preguntaFrecuente) {
        String sql = "INSERT INTO preguntas_frecuentes (pregunta, respuesta) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, preguntaFrecuente.getPregunta());
            stmt.setString(2, preguntaFrecuente.getRespuesta());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al agregar la pregunta frecuente: " + e.getMessage());
        }
    }

    public List<Preguntas_Frecuentes> listarPreguntasFrecuentes() {
        List<Preguntas_Frecuentes> preguntas = new ArrayList<>();
        String sql = "SELECT * FROM preguntas_frecuentes";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Preguntas_Frecuentes pregunta = new Preguntas_Frecuentes(
                    rs.getInt("id"),
                    rs.getString("pregunta"),
                    rs.getString("respuesta")
                );
                preguntas.add(pregunta);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar las preguntas frecuentes: " + e.getMessage());
        }
        return preguntas;
    }

    public Preguntas_Frecuentes buscarPreguntaFrecuente(int id) {
        Preguntas_Frecuentes pregunta = null;
        String sql = "SELECT * FROM preguntas_frecuentes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pregunta = new Preguntas_Frecuentes(
                        rs.getInt("id"),
                        rs.getString("pregunta"),
                        rs.getString("respuesta")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar la pregunta frecuente: " + e.getMessage());
        }
        return pregunta;
    }

    public void actualizarPreguntaFrecuente(Preguntas_Frecuentes preguntaFrecuente) {
        String sql = "UPDATE preguntas_frecuentes SET pregunta = ?, respuesta = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, preguntaFrecuente.getPregunta());
            stmt.setString(2, preguntaFrecuente.getRespuesta());
            stmt.setInt(3, preguntaFrecuente.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar la pregunta frecuente: " + e.getMessage());
        }
    }

    public void eliminarPreguntaFrecuente(int id) {
        String sql = "DELETE FROM preguntas_frecuentes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar la pregunta frecuente: " + e.getMessage());
        }
    }
}
