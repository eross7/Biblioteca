package dominio;

/**
 * Classe astratta Libro - rappresenta l'opera bibliografica
 *
 * @author Eros Tettamanti
 */
public abstract class Libro {

    private String isbn;
    private String titolo;
    private String autore;
    private double prezzo;
    private String tipo;

    public Libro(String isbn, String titolo, String autore, double prezzo, String tipo) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.autore = autore;
        this.prezzo = prezzo;
        this.tipo = tipo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Libro{" + "isbn=" + isbn + ", titolo=" + titolo + ", autore=" + autore + ", prezzo=" + prezzo + ", tipo=" + tipo + '}';
    }

    /**
     * Calcola il valore reale pagato dalla biblioteca per il libro
     *
     * @return valore reale del libro
     */
    public abstract double calcolaValoreReale();

}
