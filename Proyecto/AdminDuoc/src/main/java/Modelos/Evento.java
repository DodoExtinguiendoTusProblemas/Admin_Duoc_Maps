package Modelos;

import java.util.Date;


public class Evento {
    private int id;
    private String titulo;
    private String descripcion;
    private Date fecha;
    private String rutaImagen; // Nueva propiedad para la imagen

    // Constructor
    public Evento(int id, String titulo, String descripcion, Date fecha, String rutaImagen) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.rutaImagen = rutaImagen;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public String getRutaImagen() { return rutaImagen; }
    public void setRutaImagen(String rutaImagen) { this.rutaImagen = rutaImagen; }
}
