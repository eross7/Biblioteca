package test;

import gui.LoginFrame;
import javax.swing.*;

/**
 *
 * @author Eros Tettamanti
 */
public class TestLoginSystem {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Imposta Look and Feel di sistema per aspetto nativo
                    // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                    //  UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Avvia l'applicazione con il Login
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);

                // Mostra info utenti di test
                System.out.println("=== SISTEMA AVVIATO ===");
                System.out.println("Utenti di test disponibili:");
                System.out.println("1. Admin -> username: admin, password: admin123");
                System.out.println("2. Utente -> username: mario.rossi, password: password");
                System.out.println("\nPuoi anche registrare un nuovo utente!");
            }
        });
    }
}
