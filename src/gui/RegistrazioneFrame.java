package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import servizi.ServizioAutenticazione;

/**
 *
 * @author Eros Tettamanti
 */
public class RegistrazioneFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfermaPassword;
    private JTextField txtNome;
    private JTextField txtCognome;
    private JTextField txtEmail;
    private JTextField txtCodiceFiscale;
    private JTextField txtTelefono;
    private JTextField txtIndirizzo;

    private JButton btnRegistrati;
    private JButton btnAnnulla;
    private JLabel lblMessaggio;

    private ServizioAutenticazione servizioAutenticazione;
    private LoginFrame loginFrame;

    public RegistrazioneFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
        this.servizioAutenticazione = new ServizioAutenticazione();
        inizializzaComponenti();
    }

    private void inizializzaComponenti() {
        setTitle("Biblioteca - Registrazione Nuovo Utente");
        setSize(500, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principale con scroll
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 245));

        // === HEADER ===
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(41, 128, 185));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        JLabel lblTitolo = new JLabel("Registrazione Nuovo Utente");
        lblTitolo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitolo.setForeground(Color.WHITE);
        headerPanel.add(lblTitolo);

        // === FORM ===
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Username
        aggiungiCampo(formPanel, gbc, "Username: *", txtUsername = new JTextField(20));

        // Password
        aggiungiCampo(formPanel, gbc, "Password: *", txtPassword = new JPasswordField(20));

        // Conferma Password
        aggiungiCampo(formPanel, gbc, "Conferma Password: *", txtConfermaPassword = new JPasswordField(20));

        // Separatore
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JSeparator sep1 = new JSeparator();
        formPanel.add(sep1, gbc);
        gbc.gridy++;
        gbc.gridwidth = 1;

        // Nome
        aggiungiCampo(formPanel, gbc, "Nome: *", txtNome = new JTextField(20));

        // Cognome
        aggiungiCampo(formPanel, gbc, "Cognome: *", txtCognome = new JTextField(20));

        // Email
        aggiungiCampo(formPanel, gbc, "Email: *", txtEmail = new JTextField(20));

        // Codice Fiscale
        aggiungiCampo(formPanel, gbc, "Codice Fiscale: *", txtCodiceFiscale = new JTextField(20));

        // Separatore
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JSeparator sep2 = new JSeparator();
        formPanel.add(sep2, gbc);
        gbc.gridy++;
        gbc.gridwidth = 1;

        // Telefono
        aggiungiCampo(formPanel, gbc, "Telefono:", txtTelefono = new JTextField(20));

        // Indirizzo
        aggiungiCampo(formPanel, gbc, "Indirizzo:", txtIndirizzo = new JTextField(20));

        // Nota campi obbligatori
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 5, 5, 5);
        JLabel lblObbligatori = new JLabel("* Campi obbligatori");
        lblObbligatori.setFont(new Font("Arial", Font.ITALIC, 11));
        lblObbligatori.setForeground(Color.GRAY);
        formPanel.add(lblObbligatori, gbc);

        // Messaggio
        gbc.gridy++;
        lblMessaggio = new JLabel(" ");
        lblMessaggio.setFont(new Font("Arial", Font.ITALIC, 12));
        lblMessaggio.setForeground(Color.RED);
        lblMessaggio.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(lblMessaggio, gbc);

        // Scroll pane per il form
        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // === FOOTER - Pulsanti ===
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        footerPanel.setBackground(new Color(245, 245, 245));

        btnRegistrati = new JButton("Registrati");
        btnRegistrati.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrati.setBackground(new Color(39, 174, 96));
        btnRegistrati.setForeground(Color.WHITE);
        btnRegistrati.setFocusPainted(false);
        btnRegistrati.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        btnRegistrati.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnAnnulla = new JButton("Annulla");
        btnAnnulla.setFont(new Font("Arial", Font.BOLD, 14));
        btnAnnulla.setBackground(new Color(231, 76, 60));
        btnAnnulla.setForeground(Color.WHITE);
        btnAnnulla.setFocusPainted(false);
        btnAnnulla.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        btnAnnulla.setCursor(new Cursor(Cursor.HAND_CURSOR));

        footerPanel.add(btnRegistrati);
        footerPanel.add(btnAnnulla);

        // Aggiunta componenti
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // === EVENTI ===
        btnRegistrati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                effettuaRegistrazione();
            }
        });

        btnAnnulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tornaAlLogin();
            }
        });
    }

    /**
     * Metodo helper per aggiungere campi al form
     */
    private void aggiungiCampo(JPanel panel, GridBagConstraints gbc, String label, JTextField field) {
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(lbl, gbc);

        gbc.gridy++;
        field.setFont(new Font("Arial", Font.PLAIN, 13));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(field, gbc);

        gbc.gridy++;
    }

    /**
     * Effettua la registrazione
     */
    private void effettuaRegistrazione() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        String confermaPassword = new String(txtConfermaPassword.getPassword());
        String nome = txtNome.getText();
        String cognome = txtCognome.getText();
        String email = txtEmail.getText();
        String codiceFiscale = txtCodiceFiscale.getText();
        String telefono = txtTelefono.getText();
        String indirizzo = txtIndirizzo.getText();

        try {
            boolean successo = servizioAutenticazione.registraUtente(
                    username, password, confermaPassword,
                    nome, cognome, email, codiceFiscale,
                    telefono, indirizzo
            );

            if (successo) {
                mostraSuccesso("Registrazione completata!");

                JOptionPane.showMessageDialog(this,
                        "Registrazione completata con successo!\n\nPuoi ora effettuare il login.",
                        "Successo",
                        JOptionPane.INFORMATION_MESSAGE);

                tornaAlLogin();
            }

        } catch (IllegalArgumentException e) {
            mostraErrore(e.getMessage());
        }
    }

    /**
     * Torna alla schermata di login
     */
    private void tornaAlLogin() {
        loginFrame.setVisible(true);
        dispose();
    }

    /**
     * Mostra messaggio di errore
     */
    private void mostraErrore(String messaggio) {
        lblMessaggio.setText(messaggio);
        lblMessaggio.setForeground(new Color(231, 76, 60));
    }

    /**
     * Mostra messaggio di successo
     */
    private void mostraSuccesso(String messaggio) {
        lblMessaggio.setText(messaggio);
        lblMessaggio.setForeground(new Color(39, 174, 96));
    }
}
