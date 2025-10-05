package persistenza;

import dominio.Utente;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Eros Tettamanti
 */
public class PersistenzaUtente extends PersistenzaGenerica<String, Utente> {

    private static Map<String, Utente> elencoUtenti = new HashMap<>();

    @Override
    protected Map<String, Utente> getMappa() {
        return elencoUtenti;
    }

    /**
     * Ricerca utente per email
     *
     * @param email dell'utente
     * @return
     */
    public Utente findByEmail(String email) {
        for (Utente utente : elencoUtenti.values()) {
            if (utente.getEmail().equalsIgnoreCase(email)) {
                return utente.clone();
            }
        }
        return null;
    }

    /**
     * Ricerca utente per codice fiscale
     *
     * @param codiceFiscale dell'utente
     * @return
     */
    public Utente findByCodiceFiscale(String codiceFiscale) {
        for (Utente utente : elencoUtenti.values()) {
            if (utente.getCodiceFiscale().equalsIgnoreCase(codiceFiscale)) {
                return utente.clone();
            }
        }
        return null;
    }
}
