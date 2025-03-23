package Controlador;

import Conexiones.ConsejeroDAO;
import Modelos.Consejero;
import java.util.List;


public class Consejero_Controlador {
    private ConsejeroDAO consejeroDAO;

    public Consejero_Controlador() {
        this.consejeroDAO = new ConsejeroDAO();
    }

    public void agregarConsejero(String nombreCompleto, String email, String carrera, String rutaImagen) {
        Consejero consejero = new Consejero(0, nombreCompleto, email, carrera, rutaImagen);
        consejeroDAO.agregarConsejero(consejero);
    }

    public List<Consejero> listarConsejeros() {
        return consejeroDAO.listarConsejeros();
    }

    public Consejero buscarConsejero(int id) {
        return consejeroDAO.buscarConsejero(id);
    }

    public void actualizarConsejero(int id, String nuevoNombre, String nuevoEmail, String nuevaCarrera, String nuevaRutaImagen) {
        Consejero consejero = consejeroDAO.buscarConsejero(id);
        if (consejero != null) {
            consejero.setNombreCompleto(nuevoNombre);
            consejero.setEmail(nuevoEmail);
            consejero.setCarrera(nuevaCarrera);
            consejero.setRutaImagen(nuevaRutaImagen);
            consejeroDAO.actualizarConsejero(consejero);
        }
    }

    public void eliminarConsejero(int id) {
        consejeroDAO.eliminarConsejero(id);
    }
}
