package bibliotecav2;

import dominio.Libro;
import dominio.LibroDiLettura;
import dominio.LibroScolastico;
import dominio.Prestito;
import java.util.ArrayList;
import java.util.List;
import persistenza.PersistenzaPrestito;

/**
 * Classe Biblioteca - gestisce i prestiti estendendo la persistenza
 *
 * @author Eros Tettamanti
 */
public class Bibliotecav2 extends PersistenzaPrestito {

    /**
     * Crea (inserisce) un nuovo prestito
     *
     * @param prestito il prestito da inserire
     */
    public void create(Prestito prestito) {
        super.create(prestito);
    }

    /**
     * Legge un prestito dato l'ID dell'esemplare (chiave)
     *
     * @param chiave l'ID dell'esemplare
     * @return il prestito trovato
     */
    public Prestito read(Integer chiave) {
        return super.read(chiave);
    }

    /**
     * Aggiorna un prestito esistente
     *
     * @param chiave l'ID dell'esemplare
     * @param prestito il nuovo oggetto prestito
     * @return il prestito precedente
     */
    public Prestito update(Integer chiave, Prestito prestito) {
        return super.update(chiave, prestito);
    }

    /**
     * Elimina un prestito
     *
     * @param chiave l'ID dell'esemplare
     * @return il prestito eliminato
     */
    public Prestito delete(Integer chiave) {
        return super.delete(chiave);
    }

    /**
     * Restituisce l'elenco completo di tutti i prestiti
     *
     * @return lista di tutti i prestiti
     */
    public List<Prestito> listAll() {
        return super.elencoCompleto();
    }

    /**
     * Restituisce l'elenco dei libri in prestito filtrati per tipo
     *
     * @param tipo stringa che rappresenta il tipo ("SCOLASTICO" o "LETTURA")
     * @return lista di libri del tipo specificato
     */
    public List<Libro> listPerType(String tipo) {
        List<Libro> listaLibri = new ArrayList<>();

        if (tipo == null) {
            return listaLibri;
        }

        String tipoNormalizzato = tipo.toUpperCase();

        for (Prestito prestito : super.elencoCompleto()) {
            if (prestito.getLibroInPrestito() != null) {
                Libro libro = prestito.getLibroInPrestito();

                // Verifica il tipo di libro
                if (tipoNormalizzato.equals("SCOLASTICO") && libro instanceof LibroScolastico) {
                    listaLibri.add(libro);
                } else if (tipoNormalizzato.equals("LETTURA") && libro instanceof LibroDiLettura) {
                    listaLibri.add(libro);
                }
            }
        }

        return listaLibri;
    }

    /**
     * Calcola il valore totale di tutti i libri attualmente in prestito
     * Utilizza il metodo calcolaValoreReale() che restituisce il valore reale
     * pagato dalla biblioteca
     *
     * @return valore totale della biblioteca
     */
    public double calcolaValore() {
        double somma = 0;

        for (Prestito prestito : super.elencoCompleto()) {
            if (prestito.getLibroInPrestito() != null) {
                somma += prestito.getLibroInPrestito().calcolaValoreReale();
            }
        }

        return somma;
    }

    /**
     * Restituisce tutti i prestiti attivi (non ancora restituiti)
     *
     * @return lista di prestiti attivi
     */
    public List<Prestito> getPrestitiAttivi() {
        List<Prestito> prestitiAttivi = new ArrayList<>();

        for (Prestito prestito : super.elencoCompleto()) {
            if (prestito.getDataConsegna() == 0) {
                prestitiAttivi.add(prestito);
            }
        }

        return prestitiAttivi;
    }

    /**
     * Restituisce tutti i prestiti completati (già restituiti)
     *
     * @return lista di prestiti completati
     */
    public List<Prestito> getPrestitiCompletati() {
        List<Prestito> prestitiCompletati = new ArrayList<>();

        for (Prestito prestito : super.elencoCompleto()) {
            if (prestito.getDataConsegna() > 0) {
                prestitiCompletati.add(prestito);
            }
        }

        return prestitiCompletati;
    }

    /**
     * Restituisce tutti i prestiti in ritardo rispetto al giorno corrente
     *
     * @param giornoCorrente il giorno corrente del mese (1-31)
     * @return lista di prestiti in ritardo
     */
    public List<Prestito> getPrestitiInRitardo(int giornoCorrente) {
        List<Prestito> prestitiInRitardo = new ArrayList<>();

        for (Prestito prestito : super.elencoCompleto()) {
            // Se non ancora restituito e oltre la data fine
            if (prestito.getDataConsegna() == 0 && giornoCorrente > prestito.getDataFine()) {
                prestitiInRitardo.add(prestito);
            } // Se già restituito ma in ritardo
            else if (prestito.getDataConsegna() > 0 && prestito.getDataConsegna() > prestito.getDataFine()) {
                prestitiInRitardo.add(prestito);
            }
        }

        return prestitiInRitardo;
    }

    /**
     * Restituisce statistiche sulla biblioteca
     *
     * @return stringa con le statistiche
     */
    public String getStatistiche() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== STATISTICHE BIBLIOTECA ===\n");
        sb.append("Totale prestiti: ").append(listAll().size()).append("\n");
        sb.append("Prestiti attivi: ").append(getPrestitiAttivi().size()).append("\n");
        sb.append("Prestiti completati: ").append(getPrestitiCompletati().size()).append("\n");
        sb.append("Libri scolastici in prestito: ").append(listPerType("SCOLASTICO").size()).append("\n");
        sb.append("Libri di lettura in prestito: ").append(listPerType("LETTURA").size()).append("\n");
        sb.append("Valore totale libri: ").append(String.format("%.2f", calcolaValore())).append(" €\n");
        return sb.toString();
    }
}

