package servizi;

import dominio.Utente;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import persistenza.PersistenzaUtente;

/**
 * Servizio per gestire autenticazione e registrazione utenti
 *
 * @author Eros Tettamanti
 */
public class ServizioAutenticazione {

    private PersistenzaUtente persistenzaUtente;

    public ServizioAutenticazione() {
        this.persistenzaUtente = new PersistenzaUtente();
        inizializzaUtentiDefault();
    }

    /**
     * Crea utenti di default per testing
     */
    private void inizializzaUtentiDefault() {
        // Admin di default
        try {
            Utente admin = new Utente();
            admin.setUsername("admin");
            admin.setPassword(Utente.hashPassword("admin123"));
            admin.setNome("Amministratore");
            admin.setCognome("Sistema");
            admin.setEmail("admin@biblioteca.it");
            admin.setCodiceFiscale("ADMIN000000000");
            admin.setRuolo("ADMIN");
            admin.setAttivo(true);
            admin.setDataRegistrazione(getCurrentDate());

            persistenzaUtente.create(admin);

            // Utente test
            Utente utente = new Utente();
            utente.setUsername("mario.rossi");
            utente.setPassword(Utente.hashPassword("password"));
            utente.setNome("Mario");
            utente.setCognome("Rossi");
            utente.setEmail("mario.rossi@email.it");
            utente.setCodiceFiscale("RSSMRA80A01H501Z");
            utente.setTelefono("333-1234567");
            utente.setIndirizzo("Via Roma 1, Milano");
            utente.setRuolo("UTENTE");
            utente.setAttivo(true);
            utente.setDataRegistrazione(getCurrentDate());

            persistenzaUtente.create(utente);

        } catch (IllegalArgumentException e) {
            // Utenti già presenti
        }
    }

    /**
     * Effettua il login
     *
     * @param username
     * @param password
     * @return Utente se login riuscito, null altrimenti
     */
    public Utente login(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            return null;
        }
        if (password == null || password.trim().isEmpty()) {
            return null;
        }

        try {
            Utente utente = persistenzaUtente.read(username.trim());

            // Verifica se l'utente è attivo
            if (!utente.isAttivo()) {
                return null;
            }

            // Verifica password
            if (utente.verificaPassword(password)) {
                return utente;
            }

        } catch (NoSuchElementException e) {
            // Utente non trovato
        }

        return null;
    }

    /**
     * Registra un nuovo utente
     *
     * @param username
     * @param password
     * @param confermaPassword
     * @param nome
     * @param cognome
     * @param email
     * @param codiceFiscale
     * @param telefono
     * @param indirizzo
     * @return true se registrazione riuscita
     * @throws IllegalArgumentException se i dati non sono validi
     */
    public boolean registraUtente(String username, String password, String confermaPassword, String nome, String cognome, String email, String codiceFiscale, String telefono, String indirizzo) {

        // Validazione campi obbligatori
        if (!validaCampiObbligatori(username, password, confermaPassword, nome, cognome, email, codiceFiscale)) {
            throw new IllegalArgumentException("Tutti i campi obbligatori devono essere compilati");
        }

        // Verifica password corrispondenti
        if (!password.equals(confermaPassword)) {
            throw new IllegalArgumentException("Le password non corrispondono");
        }

        // Verifica lunghezza password
        if (password.length() < 6) {
            throw new IllegalArgumentException("La password deve essere di almeno 6 caratteri");
        }
// Valida password
        if (!util.Validatore.validaPassword(password)) {
            throw new IllegalArgumentException("La password deve essere di almeno 6 caratteri");
        }

        // Valida email
        if (!util.Validatore.validaEmail(email.trim())) {
            throw new IllegalArgumentException("Formato email non valido");
        }

        // Valida codice fiscale
        if (!util.Validatore.validaCodiceFiscale(codiceFiscale.trim())) {
            throw new IllegalArgumentException("Codice fiscale non valido. Formato richiesto: 16 caratteri (es. RSSMRA80A01H501Z)");
        }

        // Valida telefono se fornito
        if (telefono != null && !telefono.trim().isEmpty()) {
            if (!util.Validatore.validaTelefono(telefono.trim())) {
                throw new IllegalArgumentException("Numero di telefono non valido");
            }
        }
        // Verifica username già esistente
        try {
            persistenzaUtente.read(username);
            throw new IllegalArgumentException("Username già esistente");
        } catch (NoSuchElementException e) {
            // OK, username disponibile
        }

        // Verifica email già esistente
        if (persistenzaUtente.findByEmail(email) != null) {
            throw new IllegalArgumentException("Email già registrata");
        }

        // Verifica codice fiscale già esistente
        if (persistenzaUtente.findByCodiceFiscale(codiceFiscale) != null) {
            throw new IllegalArgumentException("Codice fiscale già registrato");
        }

        // Crea nuovo utente
        Utente nuovoUtente = new Utente();
        nuovoUtente.setUsername(username.trim());
        nuovoUtente.setPassword(Utente.hashPassword(password));
        nuovoUtente.setNome(nome.trim());
        nuovoUtente.setCognome(cognome.trim());
        nuovoUtente.setEmail(email.trim().toLowerCase());
        nuovoUtente.setCodiceFiscale(codiceFiscale.trim().toUpperCase());
        nuovoUtente.setTelefono(telefono != null ? telefono.trim() : "");
        nuovoUtente.setIndirizzo(indirizzo != null ? indirizzo.trim() : "");
        nuovoUtente.setRuolo("UTENTE");
        nuovoUtente.setAttivo(true);
        nuovoUtente.setDataRegistrazione(getCurrentDate());

        // Salva
        persistenzaUtente.create(nuovoUtente);

        return true;
    }

    /**
     * Valida i campi obbligatori
     */
    private boolean validaCampiObbligatori(String... campi) {
        for (String campo : campi) {
            if (campo == null || campo.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Ottiene la data corrente formattata
     */
    private String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.now().format(formatter);
    }

    /**
     * Verifica se uno username è disponibile
     *
     * @param username
     * @return
     */
    public boolean isUsernameDisponibile(String username) {
        try {
            persistenzaUtente.read(username);
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }
}
