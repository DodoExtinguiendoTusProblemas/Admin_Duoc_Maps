package Controlador;

import Conexiones.SalaDAO;
import Modelos.Sala;
import java.util.List;

public class Sala_Controlador {
    private SalaDAO salaDAO;

    public Sala_Controlador() {
        this.salaDAO = new SalaDAO();
    }

    public void agregarSala(String titulo, String descripcion) {
        Sala sala = new Sala(0, titulo, descripcion);
        salaDAO.agregarSala(sala);
    }

    public List<Sala> listarSalas() {
        return salaDAO.listarSalas();
    }

    public Sala buscarSala(int id) {
        return salaDAO.buscarSala(id);
    }

    public void actualizarSala(int id, String nuevoTitulo, String nuevaDescripcion) {
        Sala sala = salaDAO.buscarSala(id);
        if (sala != null) {
            sala.setTitulo(nuevoTitulo);
            sala.setDescripcion(nuevaDescripcion);
            salaDAO.actualizarSala(sala);
        }
    }

    public void eliminarSala(int id) {
        salaDAO.eliminarSala(id);
    }
}
