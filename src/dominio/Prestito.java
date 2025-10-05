package dominio;

import persistenza.Persistibile;

/**
 * Prestito - rappresenta un prestito di un esemplare
 *
 * @author Eros Tettamanti
 */
public class Prestito implements Persistibile<Integer> {

    private Esemplare esemplare;

    private String nomeCliente;
    private int dataInizio;  // Giorno del mese (1-31)
    private int dataFine;    // Giorno previsto di restituzione
    private int dataConsegna; // Giorno effettivo di restituzione (0 = non ancora restituito)

    public Prestito() {
        this.dataConsegna = 0;
    }

    public Prestito(int idPrestito, Esemplare esemplare, String nomeCliente, int dataInizio, int dataFine) {
        this.esemplare = esemplare;
        this.nomeCliente = nomeCliente;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.dataConsegna = 0;
    }

    public Esemplare getEsemplare() {
        return esemplare;
    }

    public void setEsemplare(Esemplare esemplare) {
        this.esemplare = esemplare;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(int dataInizio) {
        this.dataInizio = dataInizio;
    }

    public int getDataFine() {
        return dataFine;
    }

    public void setDataFine(int dataFine) {
        this.dataFine = dataFine;
    }

    public int getDataConsegna() {
        return dataConsegna;
    }

    public void setDataConsegna(int dataConsegna) {
        this.dataConsegna = dataConsegna;
    }

    /**
     * Metodo di utilità per ottenere direttamente il libro dal prestito
     *
     * @return libro in prestito
     */
    public Libro getLibroInPrestito() {
        return esemplare != null ? esemplare.getLibro() : null;
    }

    /**
     * Calcola da quanti giorni il libro è in prestito
     *
     * @param giornoCorrente giorno corrente del mese (1-31)
     * @return numero di giorni in prestito
     * @throws IllegalArgumentException se il giorno non è valido
     */
    public int calcolaGiorniPrestito(int giornoCorrente) {
        if (giornoCorrente < 1 || giornoCorrente > 31) {
            throw new IllegalArgumentException(
                    "Giorno non valido: deve essere compreso tra 1 e 31");
        }
        if (giornoCorrente < dataInizio) {
            throw new IllegalArgumentException(
                    "Il giorno corrente non può essere precedente alla data di inizio prestito");
        }

        // Se il libro è già stato restituito, calcola i giorni tra inizio e consegna
        if (dataConsegna > 0) {
            return dataConsegna - dataInizio;
        }

        // Altrimenti calcola i giorni fino ad oggi
        return giornoCorrente - dataInizio;
    }

    /**
     * Verifica se il prestito è in ritardo
     *
     * @param giornoCorrente giorno corrente del mese
     * @return true se in ritardo
     */
    public boolean isInRitardo(int giornoCorrente) {
        if (dataConsegna > 0) {
            return dataConsegna > dataFine;
        }
        return giornoCorrente > dataFine;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== PRESTITO #").append(getChiave()).append(" ===\n");
        sb.append("Cliente: ").append(nomeCliente).append("\n");
        sb.append("Data Inizio: giorno ").append(dataInizio).append("\n");
        sb.append("Data Fine Prevista: giorno ").append(dataFine).append("\n");
        sb.append("Data Consegna: ");
        if (dataConsegna > 0) {
            sb.append("giorno ").append(dataConsegna);
        } else {
            sb.append("NON ANCORA RESTITUITO");
        }
        sb.append("\n").append(esemplare.toString());
        return sb.toString();
    }

    @Override
    public Prestito clone() {
        try {
            return (Prestito) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Integer getChiave() {
        return esemplare != null ? esemplare.getIdEsemplare() : null;
    }

}
