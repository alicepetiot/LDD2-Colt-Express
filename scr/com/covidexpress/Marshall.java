package com.covidexpress;
/**test */
/**
 * Définition d'une classe pour le Marshall.
 * Cette classe fait encore partie du modèle.
 */
public class Marshall {
    private final static double NERVOSITE_MARSHALL = 0.3;
    private final static String nom = "Samuel Ford";
    private final static boolean toit = false;
    private final static int id = -1;
    private int idWagon;

    /** @Constructeur. */
    public Marshall() {
        this.idWagon = TModele.NB_WAGONS;
    }

    /**
     * @Parametres aucuns.
     * @Output int.
     * @Return l'id du wagon où il est.
     */
    public int getIdWagon() {
        return this.idWagon;
    }

    /**
     * @Parametres aucuns.
     * @Output double.
     * @Return la nervosité du marshall
     */
    public double getNERVOSITE_MARSHALL() {
        return NERVOSITE_MARSHALL;
    }

    /**
     * @Parametres aucuns.
     * @Output boolean.
     * @Return si le marshall est sur le toit ou non
     * (normalement impossible).
     */
    public boolean getToit() {
        return toit;
    }

    /**
     * @Parametres aucun.
     * @Output String.
     * @Return le nom du marshall.
     */
    public String getNom() {
        return nom;
    }

    /**
     * @Parametres aucun.
     * @Output int.
     * @Return l'identifiant du marshall.
     */
    public int getId() {
        return id;
    }


    /**
     * @Parametres aucuns.
     * @Output void.
     * On décide que le Marshall à 30% de chance de se déplacer et auquel
     * cas il aura 50% de chance d'aller à droite ou 50% de chance d'aller
     * à gauche.
     */
    public void deplMarshall() {
        double resultDepl = Math.random();
        double resultDirection = Math.random();
        if (resultDepl < NERVOSITE_MARSHALL) {
            if (resultDirection < 0.5) {
                this.deplGauche();
            } else {
                this.deplDroite();
            }
        }
    }

    /**
     * @Parametres aucuns.
     * @Output void.
     * @Enleve -1 au paramètre id_wagon qui correspond à la
     * position x du Marshall dans le train si il ne se trouve
     * pas dans le dernier wagon (wagon queue).
     */
    public void deplGauche() {
        if (this.idWagon != 0) {
            this.idWagon--;
        } else {
            System.out.println(this.nom + " est déjà sur le dernier wagon de gauche");
        }
    }

    /**
     * @Parametres aucuns.
     * @Output void.
     * @Ajoute +1 au paramètre id_wagon qui correspond à la
     * position x du Marshall dans le train si il ne se trouve
     * pas dans le premier wagon, c'est-à-dire la locomotive
     * (wagon tête).
     */
    public void deplDroite() {
        if (this.idWagon != TModele.NB_WAGONS) {
            this.idWagon++;
        } else {
            System.out.println(this.nom + " est déjà sur le dernier wagon de droite");
        }
    }
}
