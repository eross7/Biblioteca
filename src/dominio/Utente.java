package dominio;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import persistenza.Persistibile;

/**
 * Classe Utente - rappresenta un utente del sistema
 *
 * @author Eros Tettamanti
 */
public class Utente implements Persistibile<String> {

    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String email;
    private String codiceFiscale;
    private String telefono;
    private String indirizzo;
    private String ruolo;              // ADMIN o UTENTE
    private boolean attivo;            // Per bloccare utenti
    private String dataRegistrazione;

    public Utente() {
        this.attivo = true;
        this.ruolo = "UTENTE";
    }

    public Utente(String username, String password, String nome, String cognome,
            String email, String codiceFiscale, String ruolo) {
        this.username = username;
        this.password = hashPassword(password);
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.codiceFiscale = codiceFiscale;
        this.ruolo = ruolo;
        this.attivo = true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }

    public String getDataRegistrazione() {
        return dataRegistrazione;
    }

    public void setDataRegistrazione(String dataRegistrazione) {
        this.dataRegistrazione = dataRegistrazione;
    }

    /**
     * Hash della password usando SHA-256
     *
     * @param password inserita dall'utente del sistema
     * @return
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Errore nell'hashing della password", e);
        }
    }

    /**
     * Verifica se la password corrisponde
     *
     * @param passwordDaVerificare inserita durante il login di utente del
     * sistema
     * @return
     */
    public boolean verificaPassword(String passwordDaVerificare) {
        String hashedInput = hashPassword(passwordDaVerificare);
        return this.password.equals(hashedInput);
    }

    /**
     * Verifica se l'utente è admin
     *
     * @return
     */
    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(ruolo);
    }

    public String getNomeCompleto() {
        return nome + " " + cognome;
    }

    @Override
    public String getChiave() {
        return username;
    }

    @Override
    public Utente clone() {
        try {
            return (Utente) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Utente{" + "username=" + username + "nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", ruolo=" + ruolo + ", attivo=" + attivo + '}';
    }

}
