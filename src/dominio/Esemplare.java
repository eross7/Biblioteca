package dominio;

/**
 * Esemplare - rappresenta la copia fisica di un Libro
 *
 * @author Eros Tettamanti
 */
public class Esemplare {

    private int idEsemplare;

    private Libro libro;

    // Stato: DISPONIBILE, IN_PRESTITO, DANNEGGIATO, PERSO
    private String stato;

    public Esemplare() {
        this.stato = "DISPONIBILE";
    }

    public Esemplare(int idEsemplare, Libro libro, String stato) {
        this.idEsemplare = idEsemplare;
        this.libro = libro;
        this.stato = stato;
    }

    public int getIdEsemplare() {
        return idEsemplare;
    }

    public void setIdEsemplare(int idEsemplare) {
        this.idEsemplare = idEsemplare;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "Esemplare{" + "idEsemplare=" + idEsemplare + ", libro=" + libro + ", stato=" + stato + '}';
    }

}
