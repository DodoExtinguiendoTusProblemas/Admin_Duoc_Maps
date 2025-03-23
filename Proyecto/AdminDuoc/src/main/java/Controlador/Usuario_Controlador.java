package Controlador;

import Conexiones.UsuarioDAO;
import Modelos.Usuario;

public class Usuario_Controlador {
    private UsuarioDAO usuarioDAO;

    public Usuario_Controlador() {
        this.usuarioDAO = new UsuarioDAO();
    }

    // Método para validar el inicio de sesión
    public Usuario login(String email, String password) {
        return usuarioDAO.login(email, password);
    }

    // Método para obtener información del usuario por su ID
    public Usuario obtenerUsuarioPorId(int id) {
        return usuarioDAO.obtenerUsuarioPorId(id);
    }
}


