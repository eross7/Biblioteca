/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Eros Tettamanti
 */
public class Costanti {

    // Ruoli utente
    public static final String RUOLO_ADMIN = "ADMIN";
    public static final String RUOLO_UTENTE = "UTENTE";

    // Stati esemplare
    public static final String STATO_DISPONIBILE = "DISPONIBILE";
    public static final String STATO_IN_PRESTITO = "IN_PRESTITO";
    public static final String STATO_DANNEGGIATO = "DANNEGGIATO";
    public static final String STATO_PERSO = "PERSO";

    // Tipi libro
    public static final String TIPO_SCOLASTICO = "SCOLASTICO";
    public static final String TIPO_LETTURA = "LETTURA";

    // Durata prestito (giorni)
    public static final int DURATA_PRESTITO_GIORNI = 14;

    // Limiti
    public static final int MAX_PRESTITI_PER_UTENTE = 3;
    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int MAX_USERNAME_LENGTH = 20;

    // Messaggi
    public static final String MSG_LOGIN_FALLITO = "Username o password errati";
    public static final String MSG_CAMPI_OBBLIGATORI = "Tutti i campi obbligatori devono essere compilati";
    public static final String MSG_PASSWORD_NON_CORRISPONDONO = "Le password non corrispondono";
    public static final String MSG_PASSWORD_TROPPO_CORTA = "La password deve essere di almeno " + MIN_PASSWORD_LENGTH + " caratteri";
    public static final String MSG_USERNAME_ESISTENTE = "Username già esistente";
    public static final String MSG_EMAIL_ESISTENTE = "Email già registrata";
    public static final String MSG_CF_ESISTENTE = "Codice fiscale già registrato";
    public static final String MSG_REGISTRAZIONE_SUCCESSO = "Registrazione completata con successo!";

    // Colori (RGB)
    public static final int[] COLOR_PRIMARY = {41, 128, 185};      // Blu
    public static final int[] COLOR_SUCCESS = {39, 174, 96};       // Verde
    public static final int[] COLOR_DANGER = {231, 76, 60};        // Rosso
    public static final int[] COLOR_WARNING = {243, 156, 18};      // Arancione
    public static final int[] COLOR_INFO = {52, 152, 219};         // Azzurro
    public static final int[] COLOR_LIGHT = {245, 245, 245};       // Grigio chiaro
    public static final int[] COLOR_DARK = {52, 73, 94};           // Grigio scuro
}
