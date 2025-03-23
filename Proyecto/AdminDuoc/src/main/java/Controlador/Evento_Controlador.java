package Controlador;

import Conexiones.EventoDAO;
import Modelos.Evento;
import java.util.Date;
import java.util.List;


public class Evento_Controlador {
    private EventoDAO eventoDAO;

    public Evento_Controlador() {
        this.eventoDAO = new EventoDAO();
    }

    public void agregarEvento(String titulo, String descripcion, Date fecha, String rutaImagen) {
        Evento evento = new Evento(0, titulo, descripcion, fecha, rutaImagen);
        eventoDAO.agregarEvento(evento);
    }

    public List<Evento> listarEventos() {
        return eventoDAO.listarEventos();
    }

    public Evento buscarEvento(int id) {
        return eventoDAO.buscarEvento(id);
    }

    public void actualizarEvento(int id, String nuevoTitulo, String nuevaDescripcion, Date nuevaFecha, String nuevaRutaImagen) {
        Evento evento = eventoDAO.buscarEvento(id);
        if (evento != null) {
            evento.setTitulo(nuevoTitulo);
            evento.setDescripcion(nuevaDescripcion);
            evento.setFecha(nuevaFecha);
            evento.setRutaImagen(nuevaRutaImagen);
            eventoDAO.actualizarEvento(evento);
        }
    }

    public void eliminarEvento(int id) {
        eventoDAO.eliminarEvento(id);
    }
}
