package com.covidexpress;
/**test */
import java.lang.Math;


/**
 * Définition d'une classe pour les butins.
 * Cette classe fait encore partie du modèle.
 */
public class Butins {
    /**
     * Enumération des différentes natures que peut prendre un butin.
     */
    public enum Nature { Bourse, Bijou, Magot }
    /**
     * Un butin a une certaine valeur qui lui est attribuée et est d'une
     * certaine nature définie plus haut par l'enum.
     */
    private int valeur;
    private int id;
    private static final int valeurBijou = 500;
    private Nature nature;
    private boolean toit;
    private int idWagon;

    /** @Constructeur
     * On commence par générer un nombre aléatoire entre [0,1[. Avec le nombre
     * obtenu on peut définir qu'un butin à 75% de chance d'être une bourse
     * et 25% d'être un bijou.
     * Pour choisir la valeur de la bourse on procède de la même manière
     * avec la fonction randomValeur(), tandis que la valeur d'un bijou
     * est fixée à 500$.
     */
    public Butins(int id, int idWagon) {
        this.toit = false;
        this.id = id;
        this.idWagon = idWagon;
        double resultNature = Math.random();
        if (resultNature < 0.75) {
            this.nature = Nature.Bourse;
            this.randomValeur();
        } else if (resultNature >= 0.75 && resultNature < 1.0) {
            this.nature = Nature.Bijou;
            this.valeur = valeurBijou;
        }
    }

    /** Prolongement du constructeur.
     * On commence par générer un nombre aléatoire entre [0,1[.
     * Avec le chiffre obtenu on peut définir la probabilité qu'a une bourse
     * d'avoir telle valeur à son initialisation. Autrement dit, une bourse a
     * 45% de chance de valoir 250$
     * 11% de chance de valoir 300$
     * 11% de chance de valoir 350$
     * 11% de chance de valoir 400$
     * 11% de chance de valoir 450$
     * 11% de chance de valoir 500$.
     */
    public void randomValeur() {
        double resultValeur = Math.random();
        if (resultValeur < 0.45) {
            this.valeur = 250;
        } else if (resultValeur >= 0.45 && resultValeur < 0.56) {
            this.valeur = 300;
        } else if (resultValeur >= 0.56 && resultValeur < 0.67) {
            this.valeur = 350;
        } else if (resultValeur >= 0.67 && resultValeur < 0.78) {
            this.valeur = 400;
        } else if (resultValeur >= 0.78 && resultValeur < 0.89) {
            this.valeur = 450;
        } else if (resultValeur >= 0.89 && resultValeur < 1.0) {
            this.valeur = 500;
        }
    }

    /** @Surcharge_du_constructeur.
     *  Permet d'ajouter le magot seulement dans la
     *  locomotive pour le Marshall.
     */
    public Butins(int id, int idWagon, boolean magot) {
        if (magot) {
            this.id = id;
            this.idWagon = idWagon;
            this.toit = false;
            this.nature = Nature.Magot;
            this.valeur = 1000;
        }
    }

    /**
     * @Parametres aucuns.
     * @Output int.
     * @Return la valeur du montant d'une bourse.
     * */
    public int getValeur() {
        return this.valeur;
    }

    /**
     * @Parametres aucuns.
     * @Output boolean.
     * @Return si le butin est sur le toit ou non.
     */
    public boolean getToit() {
        return this.toit;
    }


    /**
     * @Paramatres aucuns.
     * @Output nature.
     * @Return la nature du butin [this].
     * */
    public Nature getNature() {
        return this.nature;
    }

    /**
     * @Parametres aucuns.
     * @Output int.
     * @Return l'identifiant du butin [this].
     * */
    public int getId() {
        return this.id;
    }

    /**
     * @Parametres aucuns.
     * @Output int.
     * @Return l'identifiant du wagon où se trouve le butin [this].
     */
    public int getIdWagon() {
        return this.idWagon;
    }

    /**
     * @Parametres aucuns.
     * @Output int.
     * @Return la valeur d'un bijou.
     */
    public int getValeurBijou() {
        return valeurBijou;
    }


    /**
     * @Parametres l'identifiant d'un wagon.
     * @Output void.
     * @Modifie l'identifiant du wagon où se trouve le butin [this].
     */
    public void setIdWagon(int idWagon) {
        if (idWagon >= 0 && idWagon <= TModele.NB_WAGONS) {
            this.idWagon = idWagon;
        } else {
            System.out.println(idWagon + " est en dehors du train");
        }
    }

    /**
     * @Parametres int.
     * @Output void.
     * @Modifie la valeur du butin [this].
     */
    public void setValeur(int valeur) {
        if (valeur == 250 ||
            valeur == 300 ||
            valeur == 350 ||
            valeur == 400 ||
            valeur == 450 ||
            valeur == 500 ) {
            this.valeur = valeur;
        } else {
            System.out.println(valeur + " est différent de {250,300,350,400,450,500}");
        }
    }

    /**
     * @Parametres int.
     * @Output void.
     * @Modifie l'identifiant du butin [this].
     */
    public void setId(int id) {
            this.id = id;
    }

    /**
     * @Parametres Nature.
     * @Output void.
     * @Modifie la nature du butin [this].
     */
    public void setNature(Nature nature) {
        if (nature != Nature.Magot && this.nature != Nature.Magot) {
            this.nature = nature;
            if (nature == Nature.Bijou) {
                this.valeur = valeurBijou;
            } else {
                this.randomValeur();
            }
        } else if (nature == Nature.Magot) {
            System.out.println("On ne peut pas modifier un " + this.nature + " en Magot");
        } else {
            System.out.println("On ne peut pas modifier un Magot en " + nature);
        }
    }

    /**
     * @Parametres boolean.
     * @Output void.
     * @Modifie si le butin [this] est sur le toit ou non.
     */
    public void setToit(boolean toit) {
        this.toit = toit;
    }

    /**
     * @Parametres aucuns.
     * @Output void.
     * @Affiche un butin.
     */
    public void afficheButin() {
        System.out.println("Butin " + this.id + ": " + this.nature + " " + this.valeur + "$");
    }

    /**
     * @Paramatres aucuns.
     * @Output void.
     * @Affiche un butin qui se trouve dans la liste des butins d'un bandit.
     */
    public void afficheButin2() {
        System.out.print("Butin " + this.id + ": " + this.nature + " " + this.valeur + "$");
    }
}