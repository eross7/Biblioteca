package servizi;

import dominio.Utente;

/**
 * Gestisce la sessione utente corrente (Singleton)
 *
 * @author Eros Tettamanti
 */
public class GestoreSessione {

    private static GestoreSessione istanza;
    private Utente utenteCorrente;

    private GestoreSessione() {
        // Costruttore privato per Singleton
    }

    /**
     * Ottiene l'istanza singleton
     */
    public static GestoreSessione getInstance() {
        if (istanza == null) {
            istanza = new GestoreSessione();
        }
        return istanza;
    }

    /**
     * Effettua il login dell'utente
     */
    public void login(Utente utente) {
        this.utenteCorrente = utente;
    }

    /**
     * Effettua il logout
     */
    public void logout() {
        this.utenteCorrente = null;
    }

    /**
     * Verifica se c'è un utente loggato
     */
    public boolean isLogged() {
        return utenteCorrente != null;
    }

    /**
     * Ottiene l'utente corrente
     */
    public Utente getUtenteCorrente() {
        return utenteCorrente;
    }

    /**
     * Verifica se l'utente corrente è admin
     */
    public boolean isAdmin() {
        return utenteCorrente != null && utenteCorrente.isAdmin();
    }

    /**
     * Ottiene il nome dell'utente corrente
     */
    public String getNomeUtenteCorrente() {
        return utenteCorrente != null ? utenteCorrente.getNomeCompleto() : "Guest";
    }
}
