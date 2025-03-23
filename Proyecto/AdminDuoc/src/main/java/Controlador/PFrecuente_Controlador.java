package Controlador;

import Conexiones.Pregunta_FrecuenteDAO;
import Modelos.Preguntas_Frecuentes;
import java.util.List;

public class PFrecuente_Controlador {
    private Pregunta_FrecuenteDAO preguntaFrecuenteDAO;

    public PFrecuente_Controlador() {
        this.preguntaFrecuenteDAO = new Pregunta_FrecuenteDAO();
    }

    public void agregarPreguntaFrecuente(String pregunta, String respuesta) {
        Preguntas_Frecuentes preguntaFrecuente = new Preguntas_Frecuentes(0, pregunta, respuesta);
        preguntaFrecuenteDAO.agregarPreguntaFrecuente(preguntaFrecuente);
    }

    public List<Preguntas_Frecuentes> listarPreguntasFrecuentes() {
        return preguntaFrecuenteDAO.listarPreguntasFrecuentes();
    }

    public Preguntas_Frecuentes buscarPreguntaFrecuente(int id) {
        return preguntaFrecuenteDAO.buscarPreguntaFrecuente(id);
    }

    public void actualizarPreguntaFrecuente(int id, String nuevaPregunta, String nuevaRespuesta) {
        Preguntas_Frecuentes preguntaFrecuente = preguntaFrecuenteDAO.buscarPreguntaFrecuente(id);
        if (preguntaFrecuente != null) {
            preguntaFrecuente.setPregunta(nuevaPregunta);
            preguntaFrecuente.setRespuesta(nuevaRespuesta);
            preguntaFrecuenteDAO.actualizarPreguntaFrecuente(preguntaFrecuente);
        }
    }

    public void eliminarPreguntaFrecuente(int id) {
        preguntaFrecuenteDAO.eliminarPreguntaFrecuente(id);
    }
}
