package com.covidexpress;
/**test */
import com.covidexpress.Butins.Nature;
import javax.swing.*;
import java.awt.*;

/**
 * Une classe pour représenter la zone contenant les informations sur les bandits
 *
 *
 */
public class VueCompteRendu extends JPanel implements Observer {
    /**
     * Pour que le bouton puisse transmettre ses ordres, on garde une
     * référence au modèle.
     */
    private TModele modele;
    /** UTILISER JPANEL POUR DU TEXTE. */


    /** @Constructeur. */
    public VueCompteRendu(TModele modele) {
        this.modele = modele;
        /** On enregistre la vue [this] en tant qu'observateur de [modele]. */
        modele.addObserver(this);
        /** Dimension de la fenêtre */
        Dimension dim = new Dimension(modele.NB_JOUEURS*240,200);
        this.setPreferredSize(dim);
    }

    /**
     * L'interface [Observer] demande de fournir une méthode [update], qui
     * sera appelée lorsque la vue sera notifiée d'une changement dans le
     * modèle. Ici on se contente de réafficher toute la grille avec la méthode
     * prédéfinie [repaint].
     */
    public void update() {
        repaint();
    }

    /**
     * Fonction auxiliaire de dessin d'un bandit
     */
    public void paintComponent(Graphics g) {
        g.clearRect(0,0,(this.modele.NB_WAGONS+1)*(this.modele.LARGEUR+22),this.modele.HAUTEUR*2);
        /** affichages des bandits */
        for (int i = 0; i<modele.getBandits().size();i = i+1){
            int x = i*230 +5;
            paint(g,modele.getBandits().get(i),x,170);
        }
    }

    public void paint(Graphics g, Bandits b, int x, int y) {
        g.setColor(Color.BLACK);
        int Fontsize = 12;
        g.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, Fontsize));
        int hauteur = y;
        /** Affiche le nombre de balles */
        String balles = new String("Nombre de balles restantes : "+b.getNB_BALLES());
        g.drawString(balles,x,hauteur);
        hauteur = hauteur-20;
        /** affiche la somme des butins */
        int somme = 0;
        for(int i =0; i<b.getButin().size();i=i+1){
            somme = somme + b.getButin().get(i).getValeur();
        }
        String valeur = new String("Montant total : "+somme);
        g.drawString(valeur,x,hauteur);
        hauteur = hauteur-20;
        /** affiche les butins*/
        int magots = 0;
        int bijoux = 0;
        int bourses = 0;
        for(int i =0; i<b.getButin().size();i=i+1) {
            if (b.getButin().get(i).getNature() == Nature.Bijou) {
                bijoux++;
            } else if (b.getButin().get(i).getNature() == Nature.Bourse){
                bourses++;
            } else if(b.getButin().get(i).getNature() == Nature.Magot){
                magots++;
            }
        }
        String nbbijou = new String("Nombre de bijoux : "+String.valueOf(bijoux));
        String nbmagots = new String("Nombre de magots : "+String.valueOf(magots));
        String nbbourse = new String("Nombre de bourses : "+String.valueOf(bourses));
        g.drawString(nbbourse,x,hauteur);
        hauteur = hauteur-20;
        g.drawString(nbbijou,x,hauteur);
        hauteur = hauteur-20;
        g.drawString(nbmagots,x,hauteur);
        hauteur = hauteur-20;
        /** Affiche le wagon ainsi que sa position */
        String etage;
        if (b.getToit()) {
            etage = new String(" sur le toit");
        } else {
            etage = new String(" à l'intérieur");
        }
        String place = new String("Wagons numéro : " + b.getIdWagon());
        String position = place.concat(etage);
        g.drawString(position, x, hauteur);
        hauteur = hauteur-20;
        /** affiche le nom du bandit */
        String nom = new String("Bandit " + b.getId() + " : " + b.getNom());
        g.drawString(nom, x, hauteur);
    }
}

