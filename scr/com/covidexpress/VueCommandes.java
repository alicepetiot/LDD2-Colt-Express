package com.covidexpress;
/**test */
import javax.swing.*;
import java.awt.*;


/**
 * Une classe pour représenter la zone contenant les boutons.
 *
 * Cette zone n'aura pas à être mise à jour et ne sera donc par un observateur.
 * En revanche, comme la zone précedente, celle-ci est un panneau [JPanel].
 */
public class VueCommandes extends JPanel {
    /**
     * Pour que le bouton puisse transmettre ses ordres, on garde une
     * référence au modèle.
     */
    private TModele modele;
    /**
     * On crée un nouveau bouton, de la classe [JButton], en précisant le
     * texte qui doit l'étiqueter.
     */
    /** Boutons de tirs. */
    public JButton boutonTirHaut;
    public JButton boutonTirBas;
    public JButton boutonTirDroite;
    public JButton boutonTirGauche;
    public JButton boutonTirCentre;
    /** Boutons de déplacements. */
    public JButton boutonDeplHaut;
    public JButton boutonDeplBas;
    public JButton boutonDeplDroite;
    public JButton boutonDeplGauche;
    public JButton boutonDeplCentre;
    /** Bouton braquage. */
    public JButton boutonBraquage;
    /** Bouton action. */
    public JButton boutonAction;
    public JLabel jlabel;
    public JLabel phase;
    public JLabel espace;
    public JPanel boutonsTir;
    public JPanel boutonsDepl;

    /** Constructeur. */
    public VueCommandes(TModele modele) {
        this.modele = modele;
        /**
         * Création des boutons de tirs.
         */
        boutonTirHaut = new JButton("Tir ↑"); //Tir en haut.
        boutonTirBas = new JButton("Tir ↓"); //Tir en bas.
        boutonTirDroite = new JButton("Tir →"); //Tir à gauche.
        boutonTirGauche = new JButton("Tir ←"); //Tir à droite.
        boutonTirCentre = new JButton("       ");
        /**
         * Création des boutons de déplacements.
         */
        boutonDeplHaut = new JButton("Depl ↑"); //Se déplace sur le toit.
        boutonDeplBas = new JButton("Depl ↓"); //Se déplace à l'intérieur.
        boutonDeplDroite = new JButton("Depl →"); //Se déplace à droite.
        boutonDeplGauche = new JButton("Depl ←"); //Se déplace à gauche.
        boutonDeplCentre = new JButton("       ");
        /**
         * Création du bouton de braquage.
         */
        boutonBraquage = new JButton("Braquage");
        /**
         * Création du bouton d'action.
         */
        boutonAction = new JButton("Action !");

        /**
         * Création du label qui indique le joueur en cours.
         */
        jlabel = new JLabel("Joueur 0");
        espace = new JLabel("      ");
        phase = new JLabel("Planification");

        /**
         * On ajoute les boutons de déplacements au panneau boutonsDepl.
         */
        this.boutonsDepl = new JPanel();
        boutonsDepl.setLayout(new BorderLayout());
        /** Au nord */
        boutonsDepl.add(boutonDeplHaut,BorderLayout.NORTH);
        /** Au sud */
        boutonsDepl.add(boutonDeplBas,BorderLayout.SOUTH);
        /** A l'est */
        boutonsDepl.add(boutonDeplDroite,BorderLayout.EAST);
        /** A l'ouest */
        boutonsDepl.add(boutonDeplGauche,BorderLayout.WEST);
        /** Au centre */
        boutonsDepl.add(boutonDeplCentre,BorderLayout.CENTER);

        /**
         * On ajoute les boutons de déplcaments au panneau boutonsTir.
         */
        this.boutonsTir = new JPanel();
        boutonsTir.setLayout(new BorderLayout());
        /** Au nord */
        boutonsTir.add(this.boutonTirHaut,BorderLayout.NORTH);
        /** Au sud */
        boutonsTir.add(boutonTirBas,BorderLayout.SOUTH);
        /** A l'est */
        boutonsTir.add(boutonTirDroite,BorderLayout.EAST);
        /** A l'ouest */
        boutonsTir.add(boutonTirGauche,BorderLayout.WEST);
        /** Au centre */
        boutonsTir.add(boutonTirCentre,BorderLayout.CENTER);

        /**
         * On ajoute le label sur une ligne.
         */
        JPanel b1 = new JPanel();
        b1.setLayout(new BoxLayout(b1,BoxLayout.LINE_AXIS));
        b1.add(jlabel);
        b1.add(espace);
        b1.add(phase);

        /**
         * On ajoute le bouton de braquage, les boutons de tirs, de
         * déplacements et d'action sur une ligne.
         */
        JPanel b2 = new JPanel();
        b2.setLayout(new BoxLayout(b2,BoxLayout.LINE_AXIS));
        b2.add(boutonBraquage);
        b2.add(boutonsTir);
        b2.add(boutonsDepl);
        b2.add(boutonAction);

        /**
         * On mets les deux lignes précedentes sur la même
         * colonne.
         */
        JPanel b4 = new JPanel();
        b4.setLayout(new BoxLayout(b4,BoxLayout.PAGE_AXIS));
        b4.add(b1);
        b4.add(b2);

        /**
         * Puis on ajoute tous les boutons au panneau [this].
         */
        this.add(b4);

        this.boutonAction.setEnabled(false);
        this.boutonTirHaut.setEnabled(true);
        this.boutonTirBas.setEnabled(true);
        this.boutonTirDroite.setEnabled(true);
        this.boutonTirGauche.setEnabled(true);
        this.boutonTirCentre.setEnabled(false);
        this.boutonDeplGauche.setEnabled(true);
        this.boutonDeplDroite.setEnabled(true);
        this.boutonDeplHaut.setEnabled(true);
        this.boutonDeplBas.setEnabled(true);
        this.boutonDeplCentre.setEnabled(false);
        this.boutonBraquage.setEnabled(true);

        /**
         * Le bouton, lorsqu'il est cliqué par l'utilisateur, produit un
         * événement, de classe [ActionEvent].
         *
         * On a ici une variante du schéma observateur/observé : un objet
         * implémentant une interface [ActionListener] va s'inscrire pour
         * "écouter les événements produits par le bouton, et recevoir
         * automatiquements des notifications.
         * D'autres variantes d'auditeurs pour des événements particuliers :
         * [MouseListener], [KeyboardListener], [WindowListener].
         *
         * Cet observateur va enrichier notre schéma Modèle-Vue d'une couche
         * intermédiaire Contrôleur, dont l'objectif est de récupérer les
         * événements produits par la vue et de les traduire en instructions
         * pour le modèle.
         * Cette strate intermédiaire est potentiellement riche, et peut
         * notamment traduire les mêmes événements de différentes façons en
         * fonction d'un état de l'application.
         */

        /** Enregistrement du contrôleur comme auditeur du bouton. **/
        boutonAction.addActionListener(e-> {
            activationAction();
            this.modele.action();
            this.jlabel.setText("Joueur" + this.modele.getIdJoueur());

        });

        boutonBraquage.addActionListener(e-> {
            activationAction();
            this.modele.add_action(this.modele.getIdJoueur(),"braquage");
            this.jlabel.setText("Joueur" + this.modele.getIdJoueur());

        });

        /**
         * Boutons de déplacement.
         */

        /** A l'interieur. */
        boutonDeplBas.addActionListener(e-> {
            activationAction();
            this.modele.add_action( this.modele.getIdJoueur(),"deplEnBas");
            this.jlabel.setText("Joueur" + this.modele.getIdJoueur());
        });

        /** Sur le toit. */
        boutonDeplHaut.addActionListener(e-> {
            activationAction();
            this.modele.add_action( this.modele.getIdJoueur(),"deplEnHaut");
            this.jlabel.setText("Joueur" + this.modele.getIdJoueur());

        });

        /** A droite. */
        boutonDeplDroite.addActionListener(e-> {
            activationAction();
            this.modele.add_action( this.modele.getIdJoueur(),"deplADroite");
            this.jlabel.setText("Joueur" + this.modele.getIdJoueur());

        });

        /** A gauche. */
        boutonDeplGauche.addActionListener(e-> {
            activationAction();
            this.modele.add_action( this.modele.getIdJoueur(),"deplAGauche");
            this.jlabel.setText("Joueur" + this.modele.getIdJoueur());

        });

        /**
         * Boutons de tir.
         */
        /** Vers le bas. */
        boutonTirBas.addActionListener(e-> {
            activationAction();
            this.modele.add_action( this.modele.getIdJoueur(),"tireEnBas");
            this.jlabel.setText("Joueur" + this.modele.getIdJoueur());

        });

        /** Vers le haut. */
        boutonTirHaut.addActionListener(e -> {
            activationAction();
            this.modele.add_action( this.modele.getIdJoueur(),"tireEnHaut");
            this.jlabel.setText("Joueur" + this.modele.getIdJoueur());

            });

        /** Vers la droite. */
        boutonTirDroite.addActionListener(e-> {
            activationAction();
            this.modele.add_action( this.modele.getIdJoueur(),"tireADroite");
            this.jlabel.setText("Joueur" + this.modele.getIdJoueur());

        });

        /** Vers la gauche. */
        boutonTirGauche.addActionListener(e-> {
            activationAction();
            this.modele.add_action( this.modele.getIdJoueur(),"tireAGauche");
            this.jlabel.setText("Joueur" + this.modele.getIdJoueur());
        });

        /**
         * Variante : une lambda-expression qui évite de créer une classe
         * spécifique pour un contrôleur simplissime.
         *
         * JButton boutonAvance = new JButton(">");
         * this.add(boutonAvance);
         * boutonAvance.addActionListener(e -> { modele.avance(); });
         *
         */
    }

    public void activationAction() {
        if (modele.getCoupTour() == TModele.NB_COUPS_TOUR -1) {
            this.phase.setText("Action");
            this.boutonAction.setEnabled(true);
            this.boutonTirHaut.setEnabled(false);
            this.boutonTirBas.setEnabled(false);
            this.boutonTirDroite.setEnabled(false);
            this.boutonTirGauche.setEnabled(false);
            this.boutonDeplGauche.setEnabled(false);
            this.boutonDeplDroite.setEnabled(false);
            this.boutonDeplHaut.setEnabled(false);
            this.boutonDeplBas.setEnabled(false);
            this.boutonBraquage.setEnabled(false);
        }
        else if (modele.getCoupAction() == TModele.NB_COUPS_TOUR -1) {
            this.phase.setText("Planification");
            this.boutonAction.setEnabled(false);
            this.boutonTirHaut.setEnabled(true);
            this.boutonTirBas.setEnabled(true);
            this.boutonTirDroite.setEnabled(true);
            this.boutonTirGauche.setEnabled(true);
            this.boutonDeplGauche.setEnabled(true);
            this.boutonDeplDroite.setEnabled(true);
            this.boutonDeplHaut.setEnabled(true);
            this.boutonDeplBas.setEnabled(true);
            this.boutonBraquage.setEnabled(true);
        }
    }
}
