package com.covidexpress;
import java.util.ArrayList;
/**test */
//enum Direction {Avant,Arriere,Haut,Bas}
/**
 * Définition d'une classe pour les bandits.
 * Cette classe fait encore partie du modèle.
 */
public class Bandits {
    private String nom;
    private int id;
    /**
     * On définit la position d'un bandit en fonction de l'id du
     * wagon dans lequel il se trouve et d'un booléen qui nous
     * informe s'il se trouve sur le toit ou a l'interieur du
     * wagon.
     */
    private boolean toit;
    private int idWagon;
    private ArrayList<Butins> butin;
    private int NB_BALLES;
    private int NB_BALLES_MAX = 6;
    /**
     * @Constructeur
     */
    public Bandits(int id) {
        this.id = id;
        this.NB_BALLES = NB_BALLES_MAX;
        this.setNom();
        this.toit = true;
        this.butin = new ArrayList<>();
        this.idWagon=0;
    }

    /**
     * @Parametres aucuns.
     * @Output string.
     * @Return le nom du bandit [this].
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * @Parametres aucuns.
     * @Output int
     * @Return l'identifiant du bandit [this].
     */
    public int getId() {
        return this.id;
    }

    /**
     * @Parametres aucuns.
     * @Output int.
     * @Return le nombre de balles du bandit [this].
     */
    public int getNB_BALLES() {
        return this.NB_BALLES;
    }

    /**
     * @Parametres aucuns.
     * @Output boolean.
     * @Return si le bandit se trouve sur le toit ou non.
     */
    public boolean getToit() {
        return this.toit;
    }

    /**
     * @Parametres aucuns.
     * @Output int.
     * @Return l'id du wagon où se trouve le bandit [this].
     */
    public int getIdWagon() {
        return this.idWagon;
    }

    /**
     * @Parametres aucuns.
     * @Output liste de butins.
     * @Return la liste de butins du bandits.
     */
    public ArrayList<Butins> getButin() {
        return this.butin;
    }

    /**
     * Prolongement du constructeur.
     * On considère que le nombre de joueur maximal est de 6 joueurs dont
     * les noms de bandits sont Bella,Cheyenne,Django,Doc,Ghost et Tuco. A
     * chaque instanciation d'un objet Bandits on incrémente l'identifiant
     * et en fonction de sa valeur on lui associe un nom de bandit.
     */
    public void setNom() {
        if (this.id == 0) {
            this.nom = "Bella";
        } else if (this.id == 1) {
            this.nom = "Cheyenne";
        } else if (this.id == 2) {
            this.nom = "Django";
        } else if (this.id == 3) {
            this.nom = "Doc";
        } else if (this.id == 4) {
            this.nom = "Ghost";
        } else if (this.id == 5) {
            this.nom = "Tuco";
        }
    }


    /**
     * @Parametres boolean.
     * @Output void.
     * @Modifie la position Y du bandit c'est à dire le boolean toit.
     */
    public void setToit(boolean toit) {
        this.toit = toit;
    }

    /**
     * @Parametres int.
     * @Output void.
     * @Modifie la position X du bandit, c'est-à-dire l'id du wagon
     * dans lequel il se trouve.
     */
    public void setIdWagon(int idWagon) {
        if (idWagon >= 0 && idWagon <= TModele.NB_WAGONS) {
            this.idWagon = idWagon;
        } else {
            System.out.println("L'identifiant ne correspond à aucun wagon dans le train.");
        }
    }

    /**
     * @Parametres int.
     * @Output void.
     * @Modifie l'identifiant du bandit
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @Parametres ArrayList<Butins>.
     * @Output void.
     * @Modifie la liste des butins du bandit.
     */
    public void setButins(ArrayList<Butins> butins) {
        this.butin = butins;
    }

    /**
     * @Parametres int.
     * @Output void.
     * @Modifie le nombre de balles du bandit.
     */
    public void setNB_BALLES(int NB_BALLES) {
        if (NB_BALLES >= 0 && NB_BALLES <= this.NB_BALLES) {
            this.NB_BALLES = NB_BALLES;
        } else if (NB_BALLES < 0) {
            System.out.println("Nombres de balles négatif");
        } else {
            System.out.println("Nombre de balles supérieur au nombre autorisé");
        }
    }


    /**
     * @Parametres aucuns.
     * @Output void.
     * @Affiche le bandit [this].
     */
    public void afficheBandit() {
        System.out.print("Bandit " + this.id + ": " + nom + ", [");

        //Affiche butin
        for (int i = 0; i < this.butin.size(); i++) {
            this.butin.get(i).afficheButin2();
            if (i != this.butin.size() - 1) {
                System.out.print(",");
            }
        }
        System.out.println("] , toit = " + this.toit );
    }

    /**
     * @Parametres butins.
     * @Output void.
     * @Ajoute un butin au hasard parmi ceux présent à sa position
     * à la liste des butins du bandit [this] .
     */
    public void ajouteButin(Butins butin) {
        if (!this.butin.contains(butin)) {
            this.butin.add(butin);
        } else {
            System.out.print(this.nom + " possède déjà ce butin");
            butin.afficheButin();
            System.out.println();
        }
    }

    /**
     * @Parametres butins.
     * @Output void.
     * @Supprime un butin de la liste des butins du bandit [this].
     */
    public void removeButin(Butins butin) {
        if (this.butin.contains(butin)) {
            this.butin.remove(butin);
        } else {
            System.out.print(this.nom + " ne possède pas ce butin : ");
            butin.afficheButin();
            System.out.println();
        }
    }

    /**
     * @Parametres aucuns.
     * @Output void.
     * @Enleve -1 à la variable id_wagon qui correspond à la position
     * x du bandit [this] dans le train pour le faire reculer d'un
     * wagon.
     */
    public void deplWagonGauche() {
        if (this.idWagon != 0) {
            this.idWagon--;
            System.out.println(this.nom + " est allé dans le wagon " + this.idWagon + " de gauche.");
        } else {
            System.out.println(this.nom + " se trouve déjà dans le dernier wagon " + this.idWagon + " de gauche.");
        }
    }

    /**
     * @Parametres aucuns.
     * @Output void.
     * @Ajoute +1 à la variable id_wagon qui correspond à la position
     * x du bandit [this] dans le train pour le faire avancer d'un
     * wagon.
     */
    public void deplWagonDroite() {
        if (this.idWagon != TModele.NB_WAGONS) {
            this.idWagon++;
            System.out.println(this.nom + " est allé dans le wagon " + this.idWagon + " de droite.");
        } else {
            System.out.println(this.nom + " se trouve déjà dans le dernier wagon " + this.idWagon + " de droite." );
        }
    }

    /**
     * @Parametres aucuns.
     * @Output void.
     * @Modifie le paramètre toit qui correspond à la position y
     * du bandit [this] dans le wagon si et seulement si il se
     * trouve à l'intérieur du wagon, c'est-à-dire si toit
     * est à false.
     */
    public void deplWagonMonter() {
        if (!this.toit) {
            this.toit = true;
            System.out.println(this.nom + " est monté sur le toit du wagon " + this.idWagon);
        } else {
            System.out.println(this.nom + " est déjà sur le toit du wagon " + this.idWagon);
        }
    }

    /**
     * @Parametres aucuns.
     * @Output void.
     * @Modifie le paramètre toit qui correspond à la position y
     * du bandit [this] dans le wagon si et seulement si il se
     * trouve sur le toit du wagon, c'est-à-dire si toit est
     * à true.
     */
    public void deplWagonDescendre() {
        if (this.toit) {
            this.toit = false;
            System.out.println(this.nom + " est descendu à l'intérieur du wagon " + this.idWagon);
        } else {
            System.out.println(this.nom + " est déjà à l'intérieur du wagon " + this.idWagon);
        }
    }

    /**
     * @Parametres aucuns.
     * @Output void.
     * @Enleve une balle au bandit [this] si il décide de tirer
     * sur un autre bandit.
     */
    public void tirerSurBandit() {
        if (this.NB_BALLES != 0) {
            this.NB_BALLES--;
        } else {
            System.out.println(this.nom + " n'a plus de balles");
        }
    }
}
