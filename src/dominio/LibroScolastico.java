package dominio;

/**
 * LibroScolastico - sottoclasse di Libro.
 *
 * La biblioteca paga il 20% in meno (80% del prezzo)
 *
 * @author Eros Tettamanti
 */
public class LibroScolastico extends Libro {

    private String materia;
    private int numeroVolume;

    public LibroScolastico(String materia, int numeroVolume, String isbn, String titolo, String autore, double prezzo, String tipo) {
        super(isbn, titolo, autore, prezzo, tipo);
        this.materia = materia;
        this.numeroVolume = numeroVolume;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public int getNumeroVolume() {
        return numeroVolume;
    }

    public void setNumeroVolume(int numeroVolume) {
        this.numeroVolume = numeroVolume;
    }

    @Override
    public String toString() {
        return "LibroScolastico{" + "materia=" + materia + ", numeroVolume=" + numeroVolume + '}';
    }

    @Override
    public double calcolaValoreReale() {
        return getPrezzo() * 0.80;

    }

}
