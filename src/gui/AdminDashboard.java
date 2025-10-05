package gui;

import dominio.Utente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import servizi.GestoreSessione;

/**
 * Dashboard Amministratore - Pannello principale per admin
 *
 * @author Eros Tettamanti
 */
public class AdminDashboard extends JFrame {

    private JPanel contentPanel;
    private JLabel lblBenvenuto;
    private Utente utenteCorrente;

    // Bottoni menu laterale
    private JButton btnHome;
    private JButton btnGestioneLibri;
    private JButton btnGestioneEsemplari;
    private JButton btnGestionePrestiti;
    private JButton btnGestioneUtenti;
    private JButton btnStatistiche;
    private JButton btnImpostazioni;
    private JButton btnLogout;

    public AdminDashboard() {
        this.utenteCorrente = GestoreSessione.getInstance().getUtenteCorrente();
        inizializzaComponenti();
    }

    private void inizializzaComponenti() {
        setTitle("Biblioteca - Pannello Amministratore");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principale
        setLayout(new BorderLayout());

        // === HEADER ===
        JPanel headerPanel = creaHeader();
        add(headerPanel, BorderLayout.NORTH);

        // === MENU LATERALE ===
        JPanel menuPanel = creaMenuLaterale();
        add(menuPanel, BorderLayout.WEST);

        // === CONTENT AREA (Centro) ===
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Mostra home iniziale
        mostraHome();

        add(contentPanel, BorderLayout.CENTER);
    }

    /**
     * Crea il panel header
     */
    private JPanel creaHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(41, 128, 185));
        header.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Titolo
        JLabel lblTitolo = new JLabel("Sistema Gestione Biblioteca");
        lblTitolo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitolo.setForeground(Color.WHITE);

        // Info utente
        lblBenvenuto = new JLabel("Amministratore: " + utenteCorrente.getNomeCompleto());
        lblBenvenuto.setFont(new Font("Arial", Font.PLAIN, 14));
        lblBenvenuto.setForeground(Color.WHITE);
        lblBenvenuto.setHorizontalAlignment(SwingConstants.RIGHT);

        header.add(lblTitolo, BorderLayout.WEST);
        header.add(lblBenvenuto, BorderLayout.EAST);

        return header;
    }

    /**
     * Crea il menu laterale
     */
    private JPanel creaMenuLaterale() {
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(new Color(52, 73, 94));
        menu.setPreferredSize(new Dimension(250, 0));
        menu.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Titolo menu
        JLabel lblMenu = new JLabel("MENU PRINCIPALE");
        lblMenu.setFont(new Font("Arial", Font.BOLD, 14));
        lblMenu.setForeground(Color.WHITE);
        lblMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu.add(lblMenu);
        menu.add(Box.createRigidArea(new Dimension(0, 20)));

        // Bottoni menu
        btnHome = creaBottoneMenu("[ Home ]");
        btnGestioneLibri = creaBottoneMenu("Gestione Libri");
        btnGestioneEsemplari = creaBottoneMenu("Gestione Esemplari");
        btnGestionePrestiti = creaBottoneMenu("Gestione Prestiti");
        btnGestioneUtenti = creaBottoneMenu("Gestione Utenti");
        btnStatistiche = creaBottoneMenu("Statistiche");
        btnImpostazioni = creaBottoneMenu("Impostazioni");

        menu.add(btnHome);
        menu.add(Box.createRigidArea(new Dimension(0, 10)));
        menu.add(btnGestioneLibri);
        menu.add(Box.createRigidArea(new Dimension(0, 10)));
        menu.add(btnGestioneEsemplari);
        menu.add(Box.createRigidArea(new Dimension(0, 10)));
        menu.add(btnGestionePrestiti);
        menu.add(Box.createRigidArea(new Dimension(0, 10)));
        menu.add(btnGestioneUtenti);
        menu.add(Box.createRigidArea(new Dimension(0, 10)));
        menu.add(btnStatistiche);
        menu.add(Box.createRigidArea(new Dimension(0, 10)));
        menu.add(btnImpostazioni);

        // Spazio per spingere logout in basso
        menu.add(Box.createVerticalGlue());

        // Separatore
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        separator.setForeground(Color.GRAY);
        menu.add(separator);
        menu.add(Box.createRigidArea(new Dimension(0, 10)));

        // Logout
        btnLogout = creaBottoneMenu(">> Logout");
        btnLogout.setBackground(new Color(231, 76, 60));
        menu.add(btnLogout);

        // === EVENTI ===
        btnHome.addActionListener(e -> mostraHome());
        btnGestioneLibri.addActionListener(e -> mostraGestioneLibri());
        btnGestioneEsemplari.addActionListener(e -> mostraGestioneEsemplari());
        btnGestionePrestiti.addActionListener(e -> mostraGestionePrestiti());
        btnGestioneUtenti.addActionListener(e -> mostraGestioneUtenti());
        btnStatistiche.addActionListener(e -> mostraStatistiche());
        btnImpostazioni.addActionListener(e -> mostraImpostazioni());
        btnLogout.addActionListener(e -> effettuaLogout());

        return menu;
    }

    /**
     * Crea un bottone per il menu
     */
    private JButton creaBottoneMenu(String testo) {
        JButton btn = new JButton(testo);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(41, 128, 185));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(230, 45));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Effetto hover
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (btn.getBackground().equals(new Color(41, 128, 185))) {
                    btn.setBackground(new Color(52, 152, 219));
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (btn.getBackground().equals(new Color(52, 152, 219))) {
                    btn.setBackground(new Color(41, 128, 185));
                }
            }
        });

        return btn;
    }

    /**
     * Mostra la home
     */
    private void mostraHome() {
        contentPanel.removeAll();

        JPanel homePanel = new JPanel();
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));
        homePanel.setBackground(Color.WHITE);

        // Titolo
        JLabel lblTitolo = new JLabel("Benvenuto nel Pannello Amministratore");
        lblTitolo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitolo.setForeground(new Color(52, 73, 94));
        lblTitolo.setAlignmentX(Component.CENTER_ALIGNMENT);
        homePanel.add(lblTitolo);
        homePanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel lblSottotitolo = new JLabel("Seleziona una voce dal menu per iniziare");
        lblSottotitolo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblSottotitolo.setForeground(Color.GRAY);
        lblSottotitolo.setAlignmentX(Component.CENTER_ALIGNMENT);
        homePanel.add(lblSottotitolo);
        homePanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // Cards con info rapide
        JPanel cardsPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        cardsPanel.setBackground(Color.WHITE);
        cardsPanel.setMaximumSize(new Dimension(800, 400));

        cardsPanel.add(creaCard("LIBRI", "Gestisci il catalogo", new Color(52, 152, 219)));
        cardsPanel.add(creaCard("PRESTITI", "Controlla i prestiti", new Color(46, 204, 113)));
        cardsPanel.add(creaCard("UTENTI", "Gestisci gli utenti", new Color(155, 89, 182)));
        cardsPanel.add(creaCard("STATISTICHE", "Visualizza i report", new Color(241, 196, 15)));

        homePanel.add(cardsPanel);

        contentPanel.add(homePanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Crea una card informativa CLICCABILE
     */
    private JPanel creaCard(String titolo, String descrizione, Color colore) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(colore);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(colore.darker(), 2),
                BorderFactory.createEmptyBorder(30, 20, 30, 20)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursore a manina

        JLabel lblTitolo = new JLabel(titolo);
        lblTitolo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitolo.setForeground(Color.WHITE);
        lblTitolo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblDesc = new JLabel(descrizione);
        lblDesc.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDesc.setForeground(Color.WHITE);
        lblDesc.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(lblTitolo);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(lblDesc);

        // Effetto hover
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBackground(colore.brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBackground(colore);
            }

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Naviga alla sezione corrispondente
                if (titolo.contains("LIBRI")) {
                    mostraGestioneLibri();
                } else if (titolo.contains("PRESTITI")) {
                    mostraGestionePrestiti();
                } else if (titolo.contains("UTENTI")) {
                    mostraGestioneUtenti();
                } else if (titolo.contains("STATISTICHE")) {
                    mostraStatistiche();
                }
            }
        });

        return card;
    }

    /**
     * Mostra la gestione libri
     */
    private void mostraGestioneLibri() {
        contentPanel.removeAll();

        JLabel lbl = new JLabel("GESTIONE LIBRI - In sviluppo");
        lbl.setFont(new Font("Arial", Font.BOLD, 24));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);

        contentPanel.add(lbl, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Mostra la gestione esemplari
     */
    private void mostraGestioneEsemplari() {
        contentPanel.removeAll();

        JLabel lbl = new JLabel("GESTIONE ESEMPLARI - In sviluppo");
        lbl.setFont(new Font("Arial", Font.BOLD, 24));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);

        contentPanel.add(lbl, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Mostra la gestione prestiti
     */
    private void mostraGestionePrestiti() {
        contentPanel.removeAll();

        JLabel lbl = new JLabel("GESTIONE PRESTITI - In sviluppo");
        lbl.setFont(new Font("Arial", Font.BOLD, 24));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);

        contentPanel.add(lbl, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Mostra la gestione utenti
     */
    private void mostraGestioneUtenti() {
        contentPanel.removeAll();

        JLabel lbl = new JLabel("GESTIONE UTENTI - In sviluppo");
        lbl.setFont(new Font("Arial", Font.BOLD, 24));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);

        contentPanel.add(lbl, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Mostra le statistiche
     */
    private void mostraStatistiche() {
        contentPanel.removeAll();

        JLabel lbl = new JLabel("STATISTICHE - In sviluppo");
        lbl.setFont(new Font("Arial", Font.BOLD, 24));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);

        contentPanel.add(lbl, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Mostra le impostazioni
     */
    private void mostraImpostazioni() {
        contentPanel.removeAll();

        JLabel lbl = new JLabel("IMPOSTAZIONI - In sviluppo");
        lbl.setFont(new Font("Arial", Font.BOLD, 24));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);

        contentPanel.add(lbl, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Effettua il logout
     */
    private void effettuaLogout() {
        int scelta = JOptionPane.showConfirmDialog(
                this,
                "Sei sicuro di voler uscire?",
                "Conferma Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (scelta == JOptionPane.YES_OPTION) {
            GestoreSessione.getInstance().logout();

            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
            dispose();
        }
    }
}
