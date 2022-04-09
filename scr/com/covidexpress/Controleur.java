package com.covidexpress;
/**test */
import java.awt.event.*;

/**
 * Classe pour notre contrôleur rudimentaire.
 *
 * Le contrôleur implémente l'interface [ActionListener] qui demande
 * uniquement de fournir une méthode [actionPerformed] indiquant la
 * réponse du contrôleur à la réception d'un événement.
 */
public class Controleur implements ActionListener {
    /**
     * On garde un pointeur vers le modèle, car le contrôleur doit
     * provoquer un appel de méthode du modèle.
     * Remarque : comme cette classe est interne, cette inscription
     * explicite du modèle est initule. On pourrait se contenter de
     * faire directement référence au modèle enregistré pour la classe
     * englobante [VueCommandes].
     */
    TModele modele;
    CVue vue;


    /**
     * Constructeur.
     */
    public Controleur(TModele modele, CVue vue) { //VueCommandes vueCommandes
       this.modele = modele;
       this.vue = vue;
    }

    /**
     * Action effectuée à la réception d'un évènement.
    */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vue.commandes.boutonAction) {
            this.modele.action();
        } else if (e.getSource() == vue.commandes.boutonBraquage) {
            this.modele.braquage();
        } else if (e.getSource() == vue.commandes.boutonTirHaut) {
            this.modele.tirEnHaut();
        } else if (e.getSource() == vue.commandes.boutonTirBas) {
            this.modele.tirEnBas();
        } else if (e.getSource() == vue.commandes.boutonTirDroite) {
            this.modele.tirADroite();
        } else if (e.getSource() == vue.commandes.boutonTirGauche) {
            this.modele.tirAGauche();
        } else if (e.getSource() == vue.commandes.boutonDeplHaut) {
            this.modele.deplEnHaut();
        } else if (e.getSource() == vue.commandes.boutonDeplBas) {
            this.modele.deplEnBas();
        } else if (e.getSource() == vue.commandes.boutonDeplDroite) {
            this.modele.deplADroite();
        } else if (e.getSource() == vue.commandes.boutonDeplGauche) {
            this.modele.deplAGauche();
        }
        this.vue.commandes.jlabel.setText("Joueur " + " " + this.modele.getIdJoueur());
    }
}
