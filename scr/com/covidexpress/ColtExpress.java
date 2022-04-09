package com.covidexpress;
/**test */
import java.awt.*;

/**
 * Nous allons commencer à construire notre application, en voici la classe
 * principale.
 */
public class ColtExpress {
    /** L'amorçage est fait en créant le modèle et la vue, par un simple appel
     * à chaque constructeur.
     * Ici, le modèle est créé indépendamment (il s'agit d'une partie autonome
     * de l'application), et la vue prend le modèle comme paramètre (son
     * objectif est de faire le lien entre le modèle et utilisateur).
     */
    public static Controleur ctrl;
    public static void main(String[] args) {
        /**
         * Pour les besoins du jour on considère la ligne EvenQueue... comme une
         * incantation qu'on pourra expliquer plus tard.
         */
        EventQueue.invokeLater(() -> {
            /** Voici le contenu qui nous intéresse. **/
            TModele modele = new TModele();
            CVue vue = new CVue(modele);
            ctrl = new Controleur(modele,vue);

        });
    }
}
