package util;

import java.util.regex.Pattern;

/**
 * Classe di utilità per validazione dati
 *
 * @author Eros Tettamanti
 */
public class Validatore {

    private static final Pattern EMAIL_PATTERN
            = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private static final Pattern CODICE_FISCALE_PATTERN
            = Pattern.compile("^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$");

    private static final Pattern TELEFONO_PATTERN
            = Pattern.compile("^[0-9\\-\\s\\+]{7,20}$");

    /**
     * Valida un indirizzo email
     */
    public static boolean validaEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    /**
     * Valida un codice fiscale italiano
     */
    public static boolean validaCodiceFiscale(String codiceFiscale) {
        if (codiceFiscale == null || codiceFiscale.trim().isEmpty()) {
            return false;
        }
        return CODICE_FISCALE_PATTERN.matcher(codiceFiscale.trim().toUpperCase()).matches();
    }

    /**
     * Valida un numero di telefono
     */
    public static boolean validaTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            return true; // Campo opzionale
        }
        return TELEFONO_PATTERN.matcher(telefono.trim()).matches();
    }

    /**
     * Valida username (solo lettere, numeri, punto e underscore)
     */
    public static boolean validaUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        String user = username.trim();
        return user.length() >= 3 && user.length() <= 20
                && user.matches("^[a-zA-Z0-9._]+$");
    }

    /**
     * Valida password (minimo 6 caratteri)
     */
    public static boolean validaPassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        return password.length() >= 6;
    }
}
