package com.covidexpress;
/**test */
import javax.swing.JPanel;
import java.awt.*;

/**
 * Une classe pour représenter la zone d'affichage de notre Train
 *
 * JPanel est une classe d'éléments graphiques, pouvant comme JFrame contenir
 * d'autres éléments graphiques.
 *
 * Cette vue va être un observateur du modèle et sera mise à jour à chaque
 * nouvelle génération
 */
public class VueTrain extends JPanel implements Observer {
    /** On maintient une référence vers le modèle. */
    private TModele modele;

    /** @Constructeur. */
    public  VueTrain(TModele modele) {
        this.modele = modele;
        /** On enregistre la vue [this] en tant qu'observateur de [modele]. */
        modele.addObserver(this);
        /** définition de la taille de la zone */
        Dimension dim = new Dimension((this.modele.NB_WAGONS+1)*(this.modele.LARGEUR+22),
                this.modele.HAUTEUR*2);
        this.setPreferredSize(dim);
    }

    /**
     * L'interface [Observer] demande de fournir une méthode [update], qui
     * sera appelée lorsque la vue sera notifiée d'un changement dans le
     * modèle. Ici on se contente de réafficher toute la grille avec la méthode
     * prédéfinie [repaint].
     */
    public void update() {
        repaint();
    }

    /**
     * Fonction auxiliaire de dessin d'un Train
     */
    public void paintComponent(Graphics g) {
        g.clearRect(0,0,(this.modele.NB_WAGONS+1)*(this.modele.LARGEUR+22),this.modele.HAUTEUR*2);
        /** affichage des wagons */
        for(int i = 0; i<modele.NB_WAGONS+1;i=i+1){
            int x = i*(modele.LARGEUR+25)+5;
            paint(g, modele.getWagons()[i],x,modele.HAUTEUR);
            if(i<modele.NB_WAGONS){
                /** Relie les wagons */
                g.setColor(Color.BLACK);
                //g.drawLine(x+modele.LARGEUR,modele.HAUTEUR,x+modele.LARGEUR+25,modele.HAUTEUR);
                g.fillRoundRect(x+modele.LARGEUR,modele.HAUTEUR,25,4,1,1);
            }
        }
    }

    public void paint(Graphics g, Wagons w, int x, int y) {

        /** Coloration des wagons */
        if (w.getId() == modele.NB_WAGONS) {
            g.setColor(Color.PINK);
            g.fillRect(x, y / 2, modele.LARGEUR, modele.HAUTEUR / 2);
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(x, y, modele.LARGEUR, modele.HAUTEUR / 2);
        } else {
            g.setColor(Color.GRAY);
            g.fillRect(x, y / 2, modele.LARGEUR, modele.HAUTEUR / 2);
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(x, y, modele.LARGEUR, modele.HAUTEUR / 2);
        }

        /** Coloration des wagons */
        /** Locomotive */
        if (w.getId() == modele.NB_WAGONS) {
            g.setColor(Color.PINK);
            g.fillRect(x, y / 2, modele.LARGEUR, modele.HAUTEUR / 2);
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(x, y, modele.LARGEUR, modele.HAUTEUR / 2);
            g.setColor(Color.BLACK);
            g.fillRect(x + (modele.LARGEUR / 2) + 50, (y / 2) - 30, 20, 30);
        } else {
            /** wagons */
            g.setColor(Color.GRAY);
            g.fillRect(x, y / 2, modele.LARGEUR, modele.HAUTEUR / 2);
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(x, y, modele.LARGEUR, modele.HAUTEUR / 2);
        }
        /** Affichage des roues */
        g.setColor(Color.BLACK);
        g.fillOval(x + 13, y + (modele.HAUTEUR / 2) - 5, 30, 30);
        g.fillOval(x + 58, y + (modele.HAUTEUR / 2) - 5, 30, 30);
        g.fillOval(x + 102, y + (modele.HAUTEUR / 2) - 5, 30, 30);
        g.fillOval(x + 145, y + (modele.HAUTEUR / 2) - 5, 30, 30);

        /** affichage des bandits */
        int haut = y / 2 + 12;
        int bas = y + 15;
        for (int i = 0; i < w.getBandits().size(); i++) {
            String nom = new String(w.getBandits().get(i).getNom());
            if (w.getBandits().get(i).getToit()) {
                g.setColor(Color.BLACK);
                int Fontsize = 12;
                g.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, Fontsize));
                g.drawString(nom, x, haut);
                haut = haut + 10;
            } else {
                g.setColor(Color.BLACK);
                int Fontsize = 12;
                g.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, Fontsize));
                g.drawString(nom, x, bas);
                bas = bas + 10;
            }
        }
        if (modele.getMarshall().getIdWagon() == w.getId()) {
            g.setColor(Color.RED);
            int Fontsize = 15;
            g.setFont(new Font("TimesRoman", Font.BOLD, Fontsize));
            g.drawString("Marshall", x, bas);
        }
        /** Affichage des butins. */
        int basbutin = y + 15;
        int xbutin = x + (modele.LARGEUR / 2) + 30;
        String butin;
        g.setColor(Color.BLUE);
        int Fontsize = 13;
        g.setFont(new Font("TimesRoman", Font.ITALIC, Fontsize));
        for (int j = 0; j < w.getButins().size(); j++) {
            if (w.getButins().get(j).getNature() == Butins.Nature.Bijou) {
                butin = new String(w.getButins().get(j).getId() + " : Bijou");
            } else if (w.getButins().get(j).getNature() == Butins.Nature.Bourse) {
                butin = new String(w.getButins().get(j).getId() + " : Bourse");
            } else {
                butin = new String(w.getButins().get(j).getId() + " : Magot");
            }
            g.drawString(butin, xbutin, basbutin);
            basbutin = basbutin + 13;
        }
    }
}

