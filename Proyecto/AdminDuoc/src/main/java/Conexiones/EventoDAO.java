package Conexiones;

import Modelos.Evento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import db.conexion_db;
        
public class EventoDAO {

    // Usaremos la conexión proporcionada por la clase ConexionDB
    private Connection connection;

    public EventoDAO() {
        try {
            this.connection = conexion_db.getConnection(); // Obtiene la conexión desde la clase ConexionDB
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos en EventoDAO: " + e.getMessage());
        }
    }

    // Agregar un evento a la base de datos
    public void agregarEvento(Evento evento) {
        String sql = "INSERT INTO eventos (titulo, descripcion, fecha, ruta_imagen) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, evento.getTitulo());
            stmt.setString(2, evento.getDescripcion());
            stmt.setDate(3, new java.sql.Date(evento.getFecha().getTime()));
            stmt.setString(4, evento.getRutaImagen());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al agregar el evento: " + e.getMessage());
        }
    }

    // Listar todos los eventos de la base de datos
    public List<Evento> listarEventos() {
        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT * FROM eventos";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Evento evento = new Evento(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("descripcion"),
                    rs.getDate("fecha"),
                    rs.getString("ruta_imagen")
                );
                eventos.add(evento);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los eventos: " + e.getMessage());
        }
        return eventos;
    }

    // Buscar un evento por ID
    public Evento buscarEvento(int id) {
        Evento evento = null;
        String sql = "SELECT * FROM eventos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    evento = new Evento(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha"),
                        rs.getString("ruta_imagen")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el evento: " + e.getMessage());
        }
        return evento;
    }

    // Actualizar un evento existente
    public void actualizarEvento(Evento evento) {
        String sql = "UPDATE eventos SET titulo = ?, descripcion = ?, fecha = ?, ruta_imagen = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, evento.getTitulo());
            stmt.setString(2, evento.getDescripcion());
            stmt.setDate(3, new java.sql.Date(evento.getFecha().getTime()));
            stmt.setString(4, evento.getRutaImagen());
            stmt.setInt(5, evento.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el evento: " + e.getMessage());
        }
    }

    // Eliminar un evento por ID
    public void eliminarEvento(int id) {
        String sql = "DELETE FROM eventos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el evento: " + e.getMessage());
        }
    }
}
