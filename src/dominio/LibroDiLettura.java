/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 * LibroDiLettura - sottoclasse di Libro.
 *
 * La biblioteca paga il 15% in meno (85% del prezzo)
 *
 * @author Eros Tettamanti
 */
public class LibroDiLettura extends Libro {

    private String genere;
    private String categoria;

    public LibroDiLettura(String genere, String categoria, String isbn, String titolo, String autore, double prezzo, String tipo) {
        super(isbn, titolo, autore, prezzo, tipo);
        this.genere = genere;
        this.categoria = categoria;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "LibroDiLettura{" + "genere=" + genere + ", categoria=" + categoria + '}';
    }

    @Override
    public double calcolaValoreReale() {
        return getPrezzo() * 0.85;

    }

}
