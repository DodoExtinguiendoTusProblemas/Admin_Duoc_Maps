package Controlador;

import Conexiones.PuntoInteresDAO;
import Modelos.PuntoInteres;
import java.util.List;


public class PuntoInteresControlador {
    private PuntoInteresDAO puntoInteresDAO;

    public PuntoInteresControlador() {
        this.puntoInteresDAO = new PuntoInteresDAO();
    }

    // Método para agregar un nuevo punto de interés, ahora incluye la ruta de la imagen
    public void agregarPuntoInteres(String titulo, String descripcion, String rutaImagen) {
        PuntoInteres puntoInteres = new PuntoInteres(0, titulo, descripcion, rutaImagen);
        puntoInteresDAO.agregarPuntoInteres(puntoInteres);
    }

    // Listar todos los puntos de interés
    public List<PuntoInteres> listarPuntosInteres() {
        return puntoInteresDAO.listarPuntosInteres();
    }

    // Buscar un punto de interés por ID
    public PuntoInteres buscarPuntoInteres(int id) {
        return puntoInteresDAO.buscarPuntoInteres(id);
    }

    // Actualizar un punto de interés, ahora incluye la ruta de la imagen
    public void actualizarPuntoInteres(int id, String nuevoTitulo, String nuevaDescripcion, String nuevaRutaImagen) {
        PuntoInteres puntoInteres = puntoInteresDAO.buscarPuntoInteres(id);
        if (puntoInteres != null) {
            puntoInteres.setTitulo(nuevoTitulo);
            puntoInteres.setDescripcion(nuevaDescripcion);
            puntoInteres.setRutaImagen(nuevaRutaImagen);
            puntoInteresDAO.actualizarPuntoInteres(puntoInteres);
        }
    }

    // Eliminar un punto de interés
    public void eliminarPuntoInteres(int id) {
        puntoInteresDAO.eliminarPuntoInteres(id);
    }
}
