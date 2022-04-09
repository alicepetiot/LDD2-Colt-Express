

package com.covidexpress;
/**test */
import java.util.ArrayList;
import java.util.Random;

/**
 * Définition d'une classe pour les wagons.
 * Cette classe fait encore partie du modèle.
 */
public class Wagons {
    private int id;
    public final static int nbButinsMIN = 1;
    public final static int nbButinsMAX = 4;
    private ArrayList<Bandits> bandits;
    private ArrayList<Butins> butins;
    private Marshall marshall;


    /** @Constructeur.
     * Pour chaque wagon on créé une liste de bandits et de butins initialement vide.
     * Pour les wagons on ajoute entre 1 et 4 butins choisit aléatoirement qu'on
     * place à l'intérieur. Si il s'agit du wagon queue on ajoute
     * à sa liste des bandits le nombre de joueur prévu dans le modèle.
     * Ils sont initialisés sur le toit du dernier wagon. Puis, on finit
     * par ajouter le marshall et le magot dans la locomotive qui correspond
     * au premier wagon.
     */
    public Wagons(int id) {
        this.id = id;
        //this.bandits = new HashMap<>();
        this.bandits = new ArrayList<>();
        this.butins = new ArrayList<>();

        //Initialise aléatoirement entre 1 et 4 butins de valeur
        //et nature aléatoire pour chaque wagon.
        if (this.id != TModele.NB_WAGONS) {
            this.randomButins();
        }

        //Initialise l'id du wagon dans lequel se trouve le butin
        //pour avoir sa position dans le train.
        for (Butins b: this.butins) {
            b.setIdWagon(this.id);
        }
    }

    /** Prolongement du constructeur.
     * On choisit aléatoirement un chiffre entre le nombre minimal et le
     * nombre maximal de butins que peut contenir un Wagon. C'est-à-dire
     * dans notre cas entre 1 et 4. Puis on ajoute à la liste des butins
     * que contient notre Wagon le nombre (compris entre 1 et 4) de butins
     * que l'on vient générer aléatoirement.
     */
    public void randomButins() {
        Random random = new Random();
        int result = nbButinsMIN +random.nextInt(nbButinsMAX-nbButinsMIN);
        for (int i = 0; i < result; i++) {
            this.butins.add(new Butins(i,this.id));
        }
    }

    /**
     * @Parametres aucuns.
     * @Output int.
     * @Return l'identifiant du wagon [this].
     */
    public int getId() {
        return this.id;
    }

    /**
     * @Parametres aucuns.
     * @Output ArrayList<Bandits>.
     * @Return la liste des bandits dans le wagon.
     */

    public ArrayList<Bandits> getBandits() {
        return this.bandits;
    }

    /**
     * @Parametres aucuns.
     * @Output ArrayList<Butins>.
     * @Return la liste des butins dans le wagon.
     */
    public ArrayList<Butins> getButins() {
        return this.butins;
    }

    /**
     * @Parametres aucuns.
     * @Output int.
     * @Return le nombre minimum de butins dans le wagon à l'initialisation.
     */
    public int getNbButinsMIN() {
        return nbButinsMIN;
    }

    /**
     * @Parametres aucuns.
     * @Output int.
     * @Return le nombre maximum de butins dans le wagon à l'initialisation.
     */
    public int getNbButinsMAX() {
        return nbButinsMAX;
    }

    /**
     * @Parametres aucuns.
     * @Output Marshall.
     * @Return le marshall qui se trouve dans le wagon [this].
     */
    public Marshall getMarshall() {
        return this.marshall;
    }

    /**
     * @Parametres aucuns.
     * @Output void.
     * @Modifie l'identifiant du wagon [this].
     */
    public void setId(int id) {
        if (id >= 0 && id <= TModele.NB_WAGONS) {
            this.id = id;
        } else {
            System.out.println("l'identifiant " + id + " n'appartient pas au train");
        }
    }

    /**
     * @Parametres aucuns.
     * @Output void.
     * @Modifie la liste des butins que contient le wagon [this].
     */
    public void setButins(ArrayList<Butins> butins) {
        this.butins = butins;
    }

    /**
     * @Parametres aucuns.
     * @Output void.
     * @Modifie la liste des bandits que contient le wagon [this].
     */
    public void setBandits(ArrayList<Bandits> bandits) {
        this.bandits = bandits;
    }

    /**
     * @Parametres aucuns.
     * @Output void.
     * @Modifie le marshall que contient le wagon [this].
     */
    public void setMarshall(Marshall marshall) {
        this.marshall = marshall;
    }


    /**
     * @Parametres aucuns.
     * @Output void.
     * @Affiche le wagon [this].
     */
    public void afficheWagon(){
        System.out.println("wagon " + this.id + ": ");

        //Affiche les butins
        System.out.print("butin : {");
        for (int i = 0; i < this.butins.size(); i++) {
            this.butins.get(i).afficheButin2();
            if (i != this.butins.size()-1) {
                System.out.print(",");
            }
        }
        System.out.println(" }");

        //Affiche les bandits
        System.out.print("bandits : {");
        for (Bandits b : this.bandits) {
            b.afficheBandit();
        }
        System.out.println(" }");
    }

    /**
     * @Parametres bandits.
     * @Output void.
     * @Ajoute un bandit à la liste des bandits du wagon [this].
     */

    public void addBandits(Bandits bandit) {
        if (!this.bandits.contains(bandit)) {
            this.bandits.add(bandit);
        } else {
            System.out.println(bandit.getNom() + "est déjà dans le wagon");
        }
    }

    /**
     * @Parametres butins.
     * @Output void.
     * @Ajoute un butin à la liste des butins du wagon [this].
     */
    public void addButins(Butins butin) {
        if (!this.butins.contains(butin)) {
            this.butins.add(butin);
        } else {
            System.out.println("le butin d'id " + butin.getId() +
                    " est déjà dans le wagon d'id " + butin.getIdWagon());
        }
    }

    /**
     * @Parametres bandits.
     * @Output void.
     * @Supprime un bandit de la liste des bandits du wagon [this].
     */

    public void removeBandits(Bandits bandit) {
        if (this.bandits.contains(bandit)) {
            this.bandits.remove(bandit);
        } else {
            System.out.println(bandit.getNom() + "n'est pas dans le wagon d'id "
                    + bandit.getIdWagon());
        }
    }

    /**
     * @Parametres butins.
     * @Output void.
     * @Supprime un butin de la liste des butins du wagon [this].
     */
    public void removeButins(Butins butin) {
        if (this.butins.contains(butin)) {
            this.butins.remove(butin);
        } else {
            System.out.println("le butin d'id " + butin.getId() + " n'est pas dans le wagon" +
                    "d'id " + butin.getIdWagon());
        }
    }
}

