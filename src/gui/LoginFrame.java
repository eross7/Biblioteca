package gui;

import dominio.Utente;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import servizi.GestoreSessione;
import servizi.ServizioAutenticazione;

/**
 * Finestra di Login
 *
 * @author Eros Tettamanti
 */
public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegistrati;
    private JLabel lblMessaggio;

    private ServizioAutenticazione servizioAutenticazione;

    public LoginFrame() {
        servizioAutenticazione = new ServizioAutenticazione();
        inizializzaComponenti();
    }

    private void inizializzaComponenti() {
        // Configurazione finestra
        setTitle("Biblioteca - Login");
        setSize(450, 450); // Aumentata l'altezza per accommodare il pulsante
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principale con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 245));

        // === HEADER ===
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(41, 128, 185));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JLabel lblTitolo = new JLabel("Sistema Gestione Biblioteca");
        lblTitolo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitolo.setForeground(Color.WHITE);
        lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(lblTitolo);

        // === CENTRO - Form Login ===
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(25, 30, 25, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // === USERNAME ===
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(lblUsername, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtUsername = new JTextField(15);
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsername.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        formPanel.add(txtUsername, gbc);

        // === PASSWORD ===
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(lblPassword, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtPassword = new JPasswordField(15);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        formPanel.add(txtPassword, gbc);

        // === SEPARATORE ===
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 0, 15, 0);
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(189, 195, 199));
        formPanel.add(separator, gbc);

        // === PULSANTE LOGIN ===
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 8, 8, 8);
        btnLogin = new JButton("Accedi");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setBackground(new Color(39, 174, 96));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        formPanel.add(btnLogin, gbc);

        // === MESSAGGIO DI ERRORE/SUCCESSO ===
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 8, 5, 8);
        lblMessaggio = new JLabel(" ");
        lblMessaggio.setFont(new Font("Arial", Font.ITALIC, 12));
        lblMessaggio.setForeground(Color.RED);
        lblMessaggio.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(lblMessaggio, gbc);

        // === FOOTER - Pulsante Registrati ===
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(245, 245, 245));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblNoAccount = new JLabel("Non hai un account? ");
        lblNoAccount.setFont(new Font("Arial", Font.PLAIN, 12));

        btnRegistrati = new JButton("Registrati");
        btnRegistrati.setFont(new Font("Arial", Font.BOLD, 12));
        btnRegistrati.setForeground(new Color(41, 128, 185));
        btnRegistrati.setBackground(new Color(245, 245, 245));
        btnRegistrati.setBorderPainted(false);
        btnRegistrati.setFocusPainted(false);
        btnRegistrati.setCursor(new Cursor(Cursor.HAND_CURSOR));

        footerPanel.add(lblNoAccount);
        footerPanel.add(btnRegistrati);

        // === INFO UTENTI TEST ===
        JLabel lblInfo = new JLabel("<html><center>Test: admin/admin123 oppure mario.rossi/password</center></html>");
        lblInfo.setFont(new Font("Arial", Font.ITALIC, 10));
        lblInfo.setForeground(Color.GRAY);
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(new Color(245, 245, 245));
        infoPanel.add(footerPanel, BorderLayout.CENTER);
        infoPanel.add(lblInfo, BorderLayout.SOUTH);

        // === AGGIUNTA AL MAIN PANEL ===
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(infoPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // === EVENTI ===
        // Enter per login
        txtPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                effettuaLogin();
            }
        });
        // Pulsante Login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                effettuaLogin();
            }
        });
        // Pulsante Registrati
        btnRegistrati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apriRegistrazione();
            }
        });
    }

    /**
     * Effettua il login
     */
    private void effettuaLogin() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (username.trim().isEmpty() || password.isEmpty()) {
            mostraErrore("Inserisci username e password");
            return;
        }

        Utente utente = servizioAutenticazione.login(username, password);

        if (utente != null) {
            GestoreSessione.getInstance().login(utente);
            mostraSuccesso("Login effettuato!");

            Timer timer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    apriDashboard(utente);
                }
            });
            timer.setRepeats(false);
            timer.start();
        } else {
            mostraErrore("Username o password errati");
            txtPassword.setText("");
        }
    }

    private void apriDashboard(Utente utente) {
        if (utente.isAdmin()) {
//            JOptionPane.showMessageDialog(this,
//                    "Benvenuto Admin: " + utente.getNomeCompleto() + "\n\nDashboard Admin in sviluppo...",
//                    "Login Riuscito",
//                    JOptionPane.INFORMATION_MESSAGE);
            AdminDashboard adminDashboard = new AdminDashboard();
            adminDashboard.setVisible(true);
            dispose(); // Chiude il LoginFrame
        } else {
            JOptionPane.showMessageDialog(this,
                    "Benvenuto: " + utente.getNomeCompleto() + "\n\nDashboard Utente in sviluppo...",
                    "Login Riuscito",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        // dispose(); // chiudi il frame se vuoi
    }

    private void apriRegistrazione() {
        RegistrazioneFrame registrazioneFrame = new RegistrazioneFrame(this);
        registrazioneFrame.setVisible(true);
        this.setVisible(false);
    }

    private void mostraErrore(String messaggio) {
        lblMessaggio.setText(messaggio);
        lblMessaggio.setForeground(new Color(231, 76, 60));
    }

    private void mostraSuccesso(String messaggio) {
        lblMessaggio.setText(messaggio);
        lblMessaggio.setForeground(new Color(39, 174, 96));
    }

    // MAIN DI TEST
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            try {
//                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            new LoginFrame().setVisible(true);
//        });
//    }
}
