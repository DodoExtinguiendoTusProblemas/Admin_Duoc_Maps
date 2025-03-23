package Modelos;


public class PuntoInteres {
    private int id;
    private String titulo;
    private String descripcion;
    private String RutaImagen;

    public PuntoInteres(int id, String titulo, String descripcion, String RutaImagen) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.RutaImagen = RutaImagen;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public String getRutaImagen(){return RutaImagen; }
    public void setRutaImagen(String RutaImagen) {this.RutaImagen = RutaImagen; }
}
