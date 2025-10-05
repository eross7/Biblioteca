/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import bibliotecav2.Bibliotecav2;
import dominio.Esemplare;
import dominio.Libro;
import dominio.LibroDiLettura;
import dominio.LibroScolastico;
import dominio.Prestito;
import java.util.List;

/**
 *
 * @author Eros Tettamanti
 */
public class TestBibliotecaPersistenza {

    public static void main(String[] args) {
        System.out.println("=== TEST SISTEMA BIBLIOTECA CON PERSISTENZA ===\n");

        // Crea la biblioteca
        Bibliotecav2 biblioteca = new Bibliotecav2();

        // Crea libri
        LibroScolastico libroMat = new LibroScolastico(
                "Matematica", 1, "L001", "Algebra Lineare", "Mario Rossi", 50, "SCOLASTICO"
        );

        LibroScolastico libroFis = new LibroScolastico(
                "Fisica", 2, "L002", "Fisica Quantistica", "Laura Bianchi", 60, "SCOLASTICO"
        );

        LibroDiLettura libroFantasy = new LibroDiLettura(
                "Fantasy", "adulto", "L003", "Il Signore degli Anelli",
                "J.R.R. Tolkien", 25, "LETTURA"
        );

        LibroDiLettura libroGiallo = new LibroDiLettura(
                "Giallo", "adulto", "L004", "Dieci Piccoli Indiani",
                "Agatha Christie", 20, "LETTURA"
        );

        // Crea esemplari
        Esemplare es1 = new Esemplare();
        es1.setIdEsemplare(101);
        es1.setLibro(libroMat);
        es1.setStato("DISPONIBILE");

        Esemplare es2 = new Esemplare();
        es2.setIdEsemplare(102);
        es2.setLibro(libroFis);
        es2.setStato("DISPONIBILE");

        Esemplare es3 = new Esemplare();
        es3.setIdEsemplare(103);
        es3.setLibro(libroFantasy);
        es3.setStato("DISPONIBILE");

        Esemplare es4 = new Esemplare();
        es4.setIdEsemplare(104);
        es4.setLibro(libroGiallo);
        es4.setStato("DISPONIBILE");

        // Crea prestiti
        Prestito p1 = new Prestito();
        p1.setEsemplare(es1);
        p1.setNomeCliente("Giovanni Verdi");
        p1.setDataInizio(5);
        p1.setDataFine(19);
        p1.setDataConsegna(0); // Ancora in prestito

        Prestito p2 = new Prestito();
        p2.setEsemplare(es2);
        p2.setNomeCliente("Anna Neri");
        p2.setDataInizio(10);
        p2.setDataFine(24);
        p2.setDataConsegna(22); // Restituito in tempo

        Prestito p3 = new Prestito();
        p3.setEsemplare(es3);
        p3.setNomeCliente("Marco Blu");
        p3.setDataInizio(8);
        p3.setDataFine(22);
        p3.setDataConsegna(0); // Ancora in prestito

        Prestito p4 = new Prestito();
        p4.setEsemplare(es4);
        p4.setNomeCliente("Sofia Gialli");
        p4.setDataInizio(15);
        p4.setDataFine(20);
        p4.setDataConsegna(25); // Restituito in ritardo

        // Inserisci i prestiti nella biblioteca
        System.out.println("--- INSERIMENTO PRESTITI ---");
        try {
            biblioteca.create(p1);
            System.out.println("✓ Prestito 1 inserito (Esemplare " + es1.getIdEsemplare() + ")");

            biblioteca.create(p2);
            System.out.println("✓ Prestito 2 inserito (Esemplare " + es2.getIdEsemplare() + ")");

            biblioteca.create(p3);
            System.out.println("✓ Prestito 3 inserito (Esemplare " + es3.getIdEsemplare() + ")");

            biblioteca.create(p4);
            System.out.println("✓ Prestito 4 inserito (Esemplare " + es4.getIdEsemplare() + ")");
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Errore: " + e.getMessage());
        }

        // Mostra tutti i prestiti
        System.out.println("\n--- ELENCO COMPLETO PRESTITI ---");
        for (Prestito p : biblioteca.listAll()) {
            System.out.println(p);
        }

        // Test READ
        System.out.println("\n--- TEST READ ---");
        try {
            Prestito prestitoLetto = biblioteca.read(101);
            System.out.println("Prestito letto: " + prestitoLetto.getNomeCliente()
                    + " - " + prestitoLetto.getLibroInPrestito().getTitolo());
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }

        // Test calcolo giorni in prestito
        System.out.println("\n--- TEST CALCOLO GIORNI PRESTITO ---");
        int giornoCorrente = 23;
        System.out.println("Giorno corrente: " + giornoCorrente);
        try {
            int giorni = p1.calcolaGiorniPrestito(giornoCorrente);
            System.out.println("Prestito 1 è in corso da " + giorni + " giorni");

            int giorni3 = p3.calcolaGiorniPrestito(giornoCorrente);
            System.out.println("Prestito 3 è in corso da " + giorni3 + " giorni");
        } catch (IllegalArgumentException e) {
            System.out.println("Errore: " + e.getMessage());
        }

        // Test filtraggio per tipo
        System.out.println("\n--- LIBRI SCOLASTICI IN PRESTITO ---");
        List<Libro> libriScolastici = biblioteca.listPerType("SCOLASTICO");
        for (Libro libro : libriScolastici) {
            System.out.println(libro.getTitolo() + " - Valore reale: "
                    + String.format("%.2f", libro.calcolaValoreReale()) + " €");
        }

        System.out.println("\n--- LIBRI DI LETTURA IN PRESTITO ---");
        List<Libro> libriLettura = biblioteca.listPerType("LETTURA");
        for (Libro libro : libriLettura) {
            System.out.println(libro.getTitolo() + " - Valore reale: "
                    + String.format("%.2f", libro.calcolaValoreReale()) + " €");
        }

        // Test calcolo valore totale
        System.out.println("\n--- VALORE TOTALE BIBLIOTECA ---");
        double valoreTotale = biblioteca.calcolaValore();
        System.out.println("Valore totale: " + String.format("%.2f", valoreTotale) + " €");

        // Verifica calcolo manuale
        System.out.println("\nVerifica calcolo:");
        System.out.println("- Algebra Lineare (50 * 0.80) = 40.00 €");
        System.out.println("- Fisica Quantistica (60 * 0.80) = 48.00 €");
        System.out.println("- Il Signore degli Anelli (25 * 0.85) = 21.25 €");
        System.out.println("- Dieci Piccoli Indiani (20 * 0.85) = 17.00 €");
        System.out.println("TOTALE ATTESO: 126.25 €");

        // Test prestiti in ritardo
        System.out.println("\n--- PRESTITI IN RITARDO (giorno " + giornoCorrente + ") ---");
        List<Prestito> prestitiInRitardo = biblioteca.getPrestitiInRitardo(giornoCorrente);
        for (Prestito p : prestitiInRitardo) {
            System.out.println("Cliente: " + p.getNomeCliente()
                    + " - Libro: " + p.getLibroInPrestito().getTitolo()
                    + " - Data fine: " + p.getDataFine());
        }

        // Statistiche finali
        System.out.println("\n" + biblioteca.getStatistiche());

        // Test UPDATE
        System.out.println("\n--- TEST UPDATE ---");
        Prestito p1Aggiornato = new Prestito();
        p1Aggiornato.setEsemplare(es1);
        p1Aggiornato.setNomeCliente("Giovanni Verdi");
        p1Aggiornato.setDataInizio(5);
        p1Aggiornato.setDataFine(19);
        p1Aggiornato.setDataConsegna(18); // Restituito

        try {
            Prestito vecchioPrestito = biblioteca.update(101, p1Aggiornato);
            System.out.println("✓ Prestito aggiornato. Vecchia data consegna: "
                    + vecchioPrestito.getDataConsegna()
                    + ", Nuova data consegna: " + p1Aggiornato.getDataConsegna());
        } catch (Exception e) {
            System.out.println("✗ Errore: " + e.getMessage());
        }

        // Test DELETE
        System.out.println("\n--- TEST DELETE ---");
        try {
            Prestito prestitoEliminato = biblioteca.delete(104);
            System.out.println("✓ Prestito eliminato: " + prestitoEliminato.getNomeCliente());
            System.out.println("Prestiti rimanenti: " + biblioteca.listAll().size());
        } catch (Exception e) {
            System.out.println("✗ Errore: " + e.getMessage());
        }
    }
}
