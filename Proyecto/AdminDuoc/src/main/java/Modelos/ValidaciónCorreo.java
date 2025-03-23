package Modelos;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class ValidaciónCorreo {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    public static boolean esCorreoValido(String email) {
        if (email == null || email.isEmpty()) {
            return false; // El correo no debe ser nulo o vacío
        }
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
