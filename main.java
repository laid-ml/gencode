import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

public class GenerateurMotDePasseGUI {

    private static final String MAJUSCULES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUSCULES = MAJUSCULES.toLowerCase();
    private static final String CHIFFRES = "0123456789";
    private static final String CARACTERES_SPECIAUX = "!@#$%^&*()_-+=<>?/{}[]|";

    private static SecureRandom aleatoire = new SecureRandom();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> creerEtAfficherGUI());
    }

    private static void creerEtAfficherGUI() {
        JFrame fenetre = new JFrame("Générateur de mots de passe");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(400, 300);

        Container panneau = fenetre.getContentPane();
        panneau.setLayout(new GridLayout(6, 1));

        JLabel labelMotDePasse = new JLabel("Mot de passe généré :", SwingConstants.CENTER);
        panneau.add(labelMotDePasse);

        JTextField champMotDePasse = new JTextField();
        champMotDePasse.setEditable(false);
        champMotDePasse.setHorizontalAlignment(JTextField.CENTER);
        panneau.add(champMotDePasse);

        JLabel labelLongueur = new JLabel("Longueur du mot de passe :", SwingConstants.CENTER);
        panneau.add(labelLongueur);

        JSpinner selecteurLongueur = new JSpinner(new SpinnerNumberModel(12, 1, 100, 1));
        panneau.add(selecteurLongueur);

        JPanel panneauOptions = new JPanel(new GridLayout(1, 4));
        JCheckBox caseMajuscules = new JCheckBox("Majuscules", true);
        JCheckBox caseMinuscules = new JCheckBox("Minuscules", true);
        JCheckBox caseChiffres = new JCheckBox("Chiffres", true);
        JCheckBox caseCaracteresSpeciaux = new JCheckBox("Caractères spéciaux", true);
        panneauOptions.add(caseMajuscules);
        panneauOptions.add(caseMinuscules);
        panneauOptions.add(caseChiffres);
        panneauOptions.add(caseCaracteresSpeciaux);
        panneau.add(panneauOptions);

        JButton boutonGenerer = new JButton("Générer");
        boutonGenerer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int longueur = (int) selecteurLongueur.getValue();
                boolean inclureMajuscules = caseMajuscules.isSelected();
                boolean inclureMinuscules = caseMinuscules.isSelected();
                boolean inclureChiffres = caseChiffres.isSelected();
                boolean inclureCaracteresSpeciaux = caseCaracteresSpeciaux.isSelected();

                String motDePasse = genererMotDePasseAleatoire(longueur, inclureMajuscules, inclureMinuscules, inclureChiffres, inclureCaracteresSpeciaux);
                champMotDePasse.setText(motDePasse);
            }
        });
        panneau.add(boutonGenerer);

        fenetre.setVisible(true);
    }

    private static String genererMotDePasseAleatoire(int longueur, boolean inclureMajuscules, boolean inclureMinuscules, boolean inclureChiffres, boolean inclureCaracteresSpeciaux) {
        
        if (longueur < 1) {
            throw new IllegalArgumentException("La longueur doit être supérieure à 0");
        }

        StringBuilder caracteresAutorises = new StringBuilder();
        if (inclureMajuscules) {
            caracteresAutorises.append(MAJUSCULES);
        }
        if (inclureMinuscules) {
            caracteresAutorises.append(MINUSCULES);
        }
        if (inclureChiffres) {
            caracteresAutorises.append(CHIFFRES);
        }
        if (inclureCaracteresSpeciaux) {
            caracteresAutorises.append(CARACTERES_SPECIAUX);
        }

        if (caracteresAutorises.length() == 0) {
            throw new IllegalArgumentException("Au moins un type de caractère doit être sélectionné");
        }

        StringBuilder motDePasse = new StringBuilder(longueur);
        for (int i = 0; i < longueur; i++) {
            motDePasse.append(caracteresAutorises.charAt(aleatoire.nextInt(caracteresAutorises.length())));
        }

        return motDePasse.toString();
    }
}


      
