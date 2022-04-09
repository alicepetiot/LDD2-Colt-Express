package com.covidexpress;
/**test */
import java.util.*;

/** Le modèle : le coeur de l'application.
 *
 * Le modèle étend la classe [Observable] : il va posséder un certain nombre
 * d'observateurs (ici, un : la partie de la vue responsable de l'affichage)
 * et devra les prévenir avec [notifyObservers] lors des modifications.
 */
public class TModele extends Observable {
    public static final int NB_WAGONS = 4;
    public static final int LARGEUR = 200, HAUTEUR = 130;
    public static final int NB_JOUEURS = 2;
    public static final int NB_COUPS_JOUEUR = 4;
    public static final int NB_COUPS_TOUR = NB_COUPS_JOUEUR*NB_JOUEURS;

    private Wagons[] wagons;
    private HashMap<Integer,Bandits> bandits;
    private Marshall marshall;
    private int id_joueur;

    private HashMap<Integer, ArrayList<String>> actions;
    private int coup_joueur = 0;
    private int coup_tour = 0;
    private int coup_action = 0;
    private int id_joueur_action = 0;

    /** Constructeur.
     * On initialise le tableau de Wagon et donc le train.
     */
    public TModele() {
        //Initialisation des wagons
        this.actions = new HashMap<Integer, ArrayList<String>>();
        this.wagons = new Wagons[NB_WAGONS+1];
        this.id_joueur = 0;
        for (int i = 0; i < NB_WAGONS+1; i++) {
            this.wagons[i] = new Wagons(i);
        }

        //Création de la liste des joueurs.
        this.bandits = new HashMap<>();
        for (int i = 0; i < NB_JOUEURS; i++) {
            this.bandits.put(i,new Bandits(i));
        }

        //Ajoute les joueurs sur le toit du dernier wagon d'id 0.
        this.wagons[0].setBandits(this.getBanditsList());

        //Ajoute le Marshall et le magot dans la locomotive
        this.marshall = new Marshall();
        this.wagons[NB_WAGONS].setMarshall(this.marshall);
        this.wagons[NB_WAGONS].addButins(new Butins(-1, TModele.NB_WAGONS,true));
    }

    public ArrayList<Bandits> getBanditsList() {
        ArrayList<Bandits> bandits = new ArrayList<>();
        for (Map.Entry<Integer,Bandits> bandit: this.bandits.entrySet()) {
            bandits.add(bandit.getValue());
        }
        return bandits;
    }
    /**
     * @Parametres aucuns.
     * @Output Wagons[].
     * @Return la liste des wagons qui compose le train.
     */
    public Wagons[] getWagons() {
        return this.wagons;
    }

    /**
     * @Parametres aucuns.
     * @Output ArrayList<Bandits>.
     * @Return la liste des bandits qui jouent.
     */

    public HashMap<Integer,Bandits> getBandits() {
        return this.bandits;
    }

    /**
     * @Parametres aucuns.
     * @Output Marshall.
     * @Return le marshall qui est dans le train.
     */
    public Marshall getMarshall() {
        return this.marshall;
    }

    /**
     * @Parametres aucuns.
     * @Output int.
     * @Return l'id du joueur en cours.
     */
    public int getIdJoueur() {
        return this.id_joueur;
    }

    /**
     * @Parametres aucuns.
     * @Output HashMap<Integer, ArrayList<String>>.
     * @Return les actions de chaques joueurs sur un tour.
     */
    public HashMap<Integer, ArrayList<String>> getActions() {
        return this.actions;
    }

    /**
     * @Parametres aucuns.
     * @Output int.
     * @Return le nombre de coup d'un joueur pendant la planification
     * d'un tour.
     */
    public int getCoupJoueur() {
        return this.coup_joueur;
    }

    /**
     * @Parametres aucuns.
     * @Output int.
     * @Return le nombre de coup joueur par tous les joueurs pendant
     * la planification d'un tour.
     */
    public int getCoupTour() {
        return this.coup_tour;
    }

    /**
     * @Parametres aucuns.
     * @Output int.
     * @Return le nombre d'actions planifiés au tour d'avant qui ont
     * été joué pour le moment.
     */
    public int getCoupAction() {
        return this.coup_action;
    }

    /**
     * @Parametres aucuns.
     * @Output int
     * @Return l'identifiant du joueur en train de réaliser une action.
     */
    public int getIdJoueurAction() {
        return this.id_joueur_action;
    }

    /**
     * @Parametres Wagons[] une liste de wagons.
     * @Output void.
     * @Modifie la liste des wagons.
     */
    public void setWagons(Wagons[] wagons) {
        this.wagons = wagons;
    }

    /**
     * @Parametres Marshall.
     * @Output void.
     * @Modifie le marshall qui est dans le train.
     */
    public void setMarshall(Marshall marshall) {
        this.marshall = marshall;
    }

    /**
     * @Parametres int.
     * @Output void.
     * @Modifie l'identifiant du joueur en cours.
     */
    public void setIdJoueur(int id_joueur) {
        this.id_joueur = id_joueur;
    }

    /**
     * @Parametres HashMap<Integer, ArrayList<String>> un dictionnaire.
     * @Output void.
     * @Modifie le dictionnaire des actions de tous les joueurs.
     * PS : integer -> identifiant du joueur
     *      ArrayList<String> -> liste des actions "TirEnHaut"...
     */
    public void setActions(HashMap<Integer, ArrayList<String>> actions) {
        this.actions = actions;
    }

    /**
     * @Parametres int.
     * @Output void.
     * @Modifie le nombre de coup qu'a joué le joueur pendant le tour.
     */
    public void setCoupJoueur(int coup_joueur) {
        this.coup_joueur = coup_joueur;
    }

    /**
     * @Parametres int.
     * @Output void.
     * @Modifie le nombre de coups joués pendant le tour de planification.
     */
    public void setCoupTour(int coup_tour) {
        this.coup_tour = coup_tour;
    }

    /**
     * @Parametres int.
     * @Output void.
     * @Modifie le nombre de coup joués pendant le tour d'action.
     */
    public void setCoupAction(int coup_action) {
        this.coup_action = coup_action;
    }

    /**
     * @Parametres int.
     * @Output void.
     * @Modifie l'identifiant du joueur pendant la phase action.
     */
    public void setIdJoueurAction(int id_joueur_action) {
        this.id_joueur_action = id_joueur_action;
    }

    /**
     * Listes des fonctions qui change le modèle lorsqu'on fait une action sur la vue.
     */

    /**
     * @Parametres aucuns.
     * @Output int.
     * @Return la position X du bandit actuel [this] dans le train
     * c'est-à-dire l'id du wagon où il se trouve.
     */
    public int posX() {
        return this.bandits.get(id_joueur).getIdWagon();
    }

    /**
     * @Parametres aucuns.
     * @Output int.
     * @Return la position Y du bandit actuel [this] dans le train
     * c'est-à-dire si il est sur le toit ou non.
     */
    public boolean posY() {
        return this.bandits.get(id_joueur).getToit();
    }

    /**
     * @Parametres aucuns.
     * @Output Bandits.
     * @Return le bandit actuel.
     */
    public Bandits joueur() {
        return this.bandits.get(id_joueur);
    }

    /**
     * @Parametres aucuns.
     * @Output boolean.
     * @Return vrai si le bandit se trouve dans le dernier wagon
     * du train (idWagon==0).
     */
    public boolean dernierWagon() {
        return this.posX() == 0;
    }

    /**
     * @Parametres aucuns.
     * @Output boolean.
     * @Return vrai si le bandit se trouve dans le premier
     * wagon du train (idWagon == NB_WAGONS).
     */
    public boolean premierWagon() {
        return this.posX() == NB_WAGONS;
    }

    /**
     * @Parametres aucuns.
     * @Output boolean
     * @Return vrai si le bandit se trouve dans le train.
     */
    public boolean dansTrain() {
        return this.posX() >= 0 && this.posX() <= NB_WAGONS;
    }


    /**
     * @Parametres aucuns.
     * @Output Wagons.
     * @Return le wagon à gauche du bandit si il ne se trouve pas dans le dernier.
     */
    public Wagons wagonGauche() {
        if (!this.dernierWagon() && this.dansTrain()) {
            return this.wagons[this.posX()-1];
        }
        return null;
    }

    /**
     * @Parametres aucuns.
     * @Output Wagons.
     * @Return le wagon à droite du bandit si il ne se trouve pas dans le premier.
     */
    public Wagons wagonDroite()  {
        if (!this.premierWagon() && this.dansTrain()) {
            return this.wagons[this.posX()+1];
        }
        return null;
    }

    /**
     * @Parametres aucuns.
     * @Output Wagons.
     * @Return le wagon actuel dans lequel se trouve le joueur.
     */
    public Wagons wagonActuel() {
        if (this.dansTrain()) {
            return this.wagons[this.posX()];
        }
        return null;
    }

    /**
     * @Parametres un wagon.
     * @Output une liste de bandits.
     * @Return la liste des bandits du wagon.
     */
    public ArrayList<Bandits> banditsWagons(Wagons w) {
        if (w != null) {
            return w.getBandits();
        }
        return null;
    }

    /**
     * @Parametres ArrayList<Bandits>.
     * @Output ArrayList<Bandits>.
     * @Return la liste des bandits sur le toit du wagon.
     */
    public ArrayList<Bandits> banditsToit(ArrayList<Bandits> b) {
        if (b == null) { return null; }
        ArrayList<Bandits> bToit = new ArrayList<>();
        for (int i = 0; i < b.size(); i++) {
            if (b.get(i).getToit()) {
                bToit.add(b.get(i));
            }
        }
        return bToit;
    }

    /**
     * @Parametres ArrayList<Bandits>.
     * @Output ArrayList<Bandits>.
     * @Return la liste des bandits à l'interieur du wagon.
     */
    public ArrayList<Bandits> banditsInterieur(ArrayList<Bandits> b) {
        if (b == null) { return null; }
        ArrayList<Bandits> bInterieur = new ArrayList<>();
        for (int i = 0; i < b.size(); i++) {
            if (!b.get(i).getToit()) {
                bInterieur.add(b.get(i));
            }
        }
        return bInterieur;
    }

    /**
     * @Output int.
     * @Return le nombre de bandit à sa gauche.
     * Si le joueur est dans le dernier wagon alors on return -1.
     * Sinon
     *      Si la liste des bandits du wagon est vide alors on return 0.
     *      Sinon
     *          Si le joueur est sur le toit on return la taille de la liste des bandits qui sont sur le toit.
     *          Sinon on return la taille de la liste des bandits à l'intérieur.
     */
    public int compteVoisinGauche() {
        Wagons w = this.wagonGauche();
        if (w == null) { return -1; }
        ArrayList<Bandits> bandits = this.banditsWagons(w);
        if (bandits.isEmpty()) { return 0; }
        if (this.posY()) { return this.banditsToit(bandits).size(); }
        return this.banditsInterieur(bandits).size();
    }

    /**
     * @Output int.
     * @Return le nombre de bandit à sa droite.
     * Si le joueur est dans le premier wagon alors on return -1.
     * Sinon
     *      Si la liste des bandits du wagon est vide alors on return 0.
     *      Sinon
     *          Si le joueur est sur le toit on return la taille de la liste des bandits qui sont sur le toit.
     *          Sinon on return la taille de la liste des bandits à l'intérieur.
     */
    public int compteVoisinDroite() {
        Wagons w = this.wagonDroite();
        if (w == null) { return -1; }
        ArrayList<Bandits> bandits = this.banditsWagons(w);
        if (bandits.isEmpty()) { return 0; }
        if (this.posY()) { return this.banditsToit(bandits).size(); }
        return this.banditsInterieur(bandits).size();
    }

    /**
     * @Parametres aucuns.
     * @Output int.
     * @Return le nombre de bandits au dessus.
     */
    public int compteVoisinDessus() {
        Wagons w = this.wagonActuel();
        if (w == null) { return -1; }
        ArrayList<Bandits> bandits = this.banditsWagons(w);
        if (this.posY()) { return this.banditsToit(bandits).size()-1; }
        return this.banditsInterieur(bandits).size();
    }


    /**
     * @Parametres aucuns.
     * @Output int.
     * @Return le nombre de bandits en dessous.
     */
    public int compteVoisinDessous() {
        Wagons w = this.wagonActuel();
        if (w == null) { return -1; }
        ArrayList<Bandits> bandits = this.banditsWagons(w);
        if (this.posY()) { return this.banditsInterieur(bandits).size(); }
        return this.banditsToit(bandits).size()-1;
    }

    /**
     * @Parametres aucuns.
     * @Output int.
     * @Return la position du marshall dans le train.
     */
    public int posMarshall() {
        Wagons w = this.wagonActuel();
        if (w == null ) {
            return -1;
        }
        if (w.getMarshall() == null) {
            return -2;
        }
        return w.getMarshall().getIdWagon();
    }

    /**
     * @Output boolean.
     * @Return true si le marshall se trouve dans le même wagon
     * et le force à se retrancher sur le toit sinon false;
     */
    public boolean leMarshallEstLa() {
        if (!(this.posMarshall() == -1) || !(this.posMarshall() == -2)) {
            if (this.posX() == this.marshall.getIdWagon()) {
                return true;
            }
        }
        return false;
    }

    public void actionSiMarshall() {
        Wagons w = this.wagonActuel();
        if (w == null) {
            System.out.println(this.joueur().getNom() + " n'est pas dans le train ");
            return;
        }
        if (this.leMarshallEstLa()) {
            ArrayList<Butins> butins = this.joueur().getButin();
            if (!butins.isEmpty()) {
                Butins butin = this.aleaButins(butins);
                if (butin != null) {
                    w.addButins(butin);
                    this.joueur().removeButin(butin);
                }
            }
            this.joueur().setToit(true);
        }
        notifyObserver();
    }

    /**
     * @Parametres une liste de bandits.
     * @Output un bandit.
     * @Return un bandit tiré aléatoirement dans la liste donnée en paramètre.
     */
    public Bandits aleaBandits(ArrayList<Bandits> bandits) {
        Random random = new Random(new Date().getTime());
        if (bandits.size() == 0) {
            return null;
        }
        int indiceRandom = random.nextInt(bandits.size());
        return bandits.get(indiceRandom);
    }

    /**
     * @Parametres une liste de bandits.
     * @Output un bandit.
     * @Return un bandit tiré aléatoirement dans la liste donnée en paramètre.
     */
    private Butins aleaButins(ArrayList<Butins> butins) {
        Random random = new Random(new Date().getTime());
        if (butins.size() == 0) {
            return null;
        }
        int indiceRandom = random.nextInt(butins.size());
        return butins.get(indiceRandom);
    }

    public void tirEnHaut() {
        int voisin = this.compteVoisinDessus();
        String nom = this.joueur().getNom();
        Wagons w = this.wagonActuel();
        if (w == null) { System.out.println(nom + " n'est pas dans le Train"); return; }
        ArrayList<Bandits> b = this.banditsWagons(w);
        ArrayList<Bandits> bt = this.banditsToit(b);
        if (voisin == -1) {
            System.out.println(nom + " n'est pas dans le Train");
        } else if (voisin == 0) {
            System.out.println(nom + " tire en haut mais il n'y a personne dans le toit du wagon " + this.joueur().getIdWagon() + ".");
        } else if (this.posY()) {
            bt.remove(this.joueur());
            Bandits ba1 = this.aleaBandits(bt);
            if (ba1 == null) { return; }
            if (!ba1.getButin().isEmpty()) {
                Butins butin = this.aleaButins(ba1.getButin());
                ba1.removeButin(butin);
                w.addButins(butin);
            }
            System.out.println(nom + " tire en haut sur " + ba1.getNom() + " dans le wagon " + this.joueur().getIdWagon() + ".");
        } else if (!this.posY()) {
            Bandits ba2 = this.aleaBandits(bt);
            if (ba2 == null) { return; }
            if (!ba2.getButin().isEmpty()) {
                Butins butin = this.aleaButins(ba2.getButin());
                ba2.removeButin(butin);
                w.addButins(butin);
            }
            System.out.println(nom + " tire en haut sur " + ba2.getNom() + " dans le wagon " + this.joueur().getIdWagon() + ".");
        }
        this.joueur().tirerSurBandit(); // retire les balles
        /**
         * Pour finir, le modèle ayant changé, on signale aux observateurs
         * qu'ils doivent se mettre à jour.
         */
        notifyObserver();
    }

    public void tirEnBas() {
        int voisin = this.compteVoisinDessous();
        String nom = this.joueur().getNom();
        Wagons w = this.wagonActuel();
        if (w == null) { System.out.println(nom + " n'est pas dans le Train"); return; }
        ArrayList<Bandits> b = this.banditsWagons(w);
        ArrayList<Bandits> bt = this.banditsInterieur(b);

        if (voisin == -1) {
            System.out.println(nom + " n'est pas dans le Train");
        } else if (voisin == 0) {
            System.out.println(nom + " tire en bas mais il n'y a personne à l'intérieur du wagon " + this.joueur().getIdWagon() + ".");
        } else if (!this.posY()) {
            bt.remove(this.joueur());
            Bandits ba1 = this.aleaBandits(bt);
            if (ba1 == null) { return; }
            if (!ba1.getButin().isEmpty()) {
                Butins butin = this.aleaButins(ba1.getButin());
                ba1.removeButin(butin);
                w.addButins(butin);
            }
            System.out.println(nom + " tire en bas sur " + ba1.getNom() + " dans le wagon " + this.joueur().getIdWagon() + ".");
        } else if (this.posY()) {
            Bandits ba2 = this.aleaBandits(bt);
            if (ba2 == null) { return; }
            if (!ba2.getButin().isEmpty()) {
                Butins butin = this.aleaButins(ba2.getButin());
                ba2.removeButin(butin);
                w.addButins(butin);
            }
            System.out.println(nom + " tire en bas sur " + ba2.getNom() + " dans le wagon " + this.joueur().getIdWagon() + ".");
        }
        this.joueur().tirerSurBandit(); // retire les balles
        /**
         * Pour finir, le modèle ayant changé, on signale aux observateurs
         * qu'ils doivent se mettre à jour.
         */
        notifyObserver();
    }

    /**
     * Que se passe t-il sur le plateau lorsque le joueur appuie sur le bouton tirADroite ?
     */
    public void tirADroite() {
        int voisin = compteVoisinDroite();
        String nom = this.joueur().getNom();
        Wagons w = this.wagonDroite();
        if (w == null) { System.out.println(nom + " essaye de tirer à droite mais il est dans le premier wagon"); return; }
        ArrayList<Bandits> b = this.banditsWagons(w);
        ArrayList<Bandits> bt = this.banditsToit(b);
        ArrayList<Bandits> bi = this.banditsInterieur(b);
        if (voisin == -1) {
            System.out.println(nom + " essaye de tirer à droite mais il est dans le premier wagon");
        } else if (voisin == 0) {
            System.out.println(nom + " essaye de tirer à droite mais il n'y a pas de bandits dans le wagon " + this.joueur().getIdWagon() + " de droite.");
        } else if (this.posY()) {
            Bandits b1 = this.aleaBandits(bt);
            if (b1 == null) { return; }
            if (!b1.getButin().isEmpty()) {
                Butins butin = this.aleaButins(b1.getButin());
                b1.removeButin(butin);
                w.addButins(butin);
            }
            System.out.println(nom + " tire sur le toit du wagon " + this.joueur().getIdWagon() + " de droite sur " + b1.getNom());
        } else if (!this.posY()) {
            Bandits b2 = this.aleaBandits(bi);
            if (b2 == null) { return; }
            if (!b2.getButin().isEmpty()) {
                Butins butin = this.aleaButins(b2.getButin());
                b2.removeButin(butin);
                w.addButins(butin);
            }
            System.out.println(nom + " tire à l'intérieur du wagon " + this.joueur().getIdWagon() + "de droite sur " + b2.getNom());
        }
        this.joueur().tirerSurBandit(); // retire les balles
        /**
         * Pour finir, le modèle ayant changé, on signale aux observateurs
         * qu'ils doivent se mettre à jour.
         */
        notifyObserver();
    }

    /**
     * Que se passe t-il sur le plateau lorsque le joueur appuie sur le bouton tirAGauche?
     */
    public void tirAGauche() {
        int voisin = compteVoisinGauche();
        String nom = this.joueur().getNom();
        Wagons w = this.wagonGauche();
        if (w == null) { System.out.println(nom + " essaye de tirer à gauche mais il est dans le dernier wagon"); return; }
        ArrayList<Bandits> b = this.banditsWagons(w);
        ArrayList<Bandits> bt = this.banditsToit(b);
        ArrayList<Bandits> bi = this.banditsInterieur(b);
        if (voisin == -1) {
            System.out.println(nom + " essaye de tirer à gauche mais il est dans le dernier wagon");
        } else if (voisin == 0) {
            System.out.println(nom + " essaye de tirer à gauche mais il n'y a pas de bandits dans le wagon " + this.joueur().getIdWagon() + " de gauche");
        } else if (this.posY()) {
            Bandits b1 = this.aleaBandits(bt);
            if (b1 == null) { return; }
            if (!b1.getButin().isEmpty()) {
                Butins butin = this.aleaButins(b1.getButin());
                b1.removeButin(butin);
                w.addButins(butin);
            }
            System.out.println(nom + " tire sur le toit du wagon " + this.joueur().getIdWagon() + " de gauche sur " + b1.getNom());
        } else if (!this.posY()) {
            Bandits b2 = this.aleaBandits(bi);
            if (b2 == null) { return; }
            if (!b2.getButin().isEmpty()) {
                Butins butin = this.aleaButins(b2.getButin());
                b2.removeButin(butin);
                w.addButins(butin);
            }
            System.out.println(nom + " tire à l'intérieur du wagon " + this.joueur().getIdWagon() + "de gauche sur " + b2.getNom());
        }
        this.joueur().tirerSurBandit(); // retire les balles
        /**
         * Pour finir, le modèle ayant changé, on signale aux observateurs
         * qu'ils doivent se mettre à jour.
         */
        notifyObserver();
    }

    /**
     * Que se passe t-il sur le plateau lorsque le joueur appuie sur le bouton deplEnHaut ?
     */
    public void deplEnHaut() {
        this.joueur().deplWagonMonter();
        /**
         * Pour finir, le modèle ayant changé, on signale aux observateurs
         * qu'ils doivent se mettre à jour.
         */
        notifyObserver();
    }

    /**
     * Que se passe t-il sur le plateau lorsque le joueur appuie sur le bouton deplEnBas?
     */
    public void deplEnBas() {
        this.joueur().deplWagonDescendre();
        /**
         * Pour finir, le modèle ayant changé, on signale aux observateurs
         * qu'ils doivent se mettre à jour.
         */
        notifyObserver();
    }

    /**
     * Que se passe t-il sur le plateau lorsque le joueur appuie sur le bouton deplADroite ?
     */
    public void deplADroite() {
        Wagons w = this.wagonActuel();
        Wagons wd = this.wagonDroite();
        if (w == null) {
            System.out.println(this.joueur().getNom() + " n'est pas dans le train");
            return;
        }
        if (wd == null) {
            System.out.println(this.joueur().getNom() + " essaye d'aller à droite mais est déjà dans le dernier wagon de droite");
            return;
        }
        w.removeBandits(this.joueur());
        this.joueur().deplWagonDroite();
        wd.addBandits(this.joueur());
        /**
         * Pour finir, le modèle ayant changé, on signale aux observateurs
         * qu'ils doivent se mettre à jour.
         */
        notifyObserver();
    }

    /**
     * Que se passe t-il sur le plateau lorsque le joueur appuie sur le bouton deplAGauche ?
     */
    public void deplAGauche() {
        Wagons w = this.wagonActuel();
        Wagons wg = this.wagonGauche();
        if (w == null) {
            System.out.println(this.joueur().getNom() + " n'est pas dans le train");
            return;
        }
        if (wg == null) {
            System.out.println(this.joueur().getNom() + " essaye d'aller à gauche mais est déjà dans le dernier wagon de gauche");
            return;
        }
        w.removeBandits(this.joueur());
        this.joueur().deplWagonGauche();
        wg.addBandits(this.joueur());

        /**
         * Pour finir, le modèle ayant changé, on signale aux observateurs
         * qu'ils doivent se mettre à jour.
         */
        notifyObserver();
    }

    /**
     * Que se passe t-il sur le plateau lorsque le joueur appuie sur le bouton braquage ?
     */
    public void braquage() {
        Wagons w = this.wagonActuel();
        String nom = this.joueur().getNom();
        if (w == null) { System.out.println(nom + " n'est pas dans le train."); return; }
        ArrayList<Butins> b = w.getButins();

        if (b.isEmpty()) {
            System.out.println(this.joueur().getNom() + " essaye de braquer un butin mais le wagon " + this.joueur().getIdWagon() + " n'en possède pas.");
            return;
        }
        Butins butin = this.aleaButins(w.getButins());
        this.joueur().ajouteButin(butin);
        this.wagonActuel().removeButins(butin);
        /**
         * Pour finir, le modèle ayant changé, on signale aux observateurs
         * qu'ils doivent se mettre à jour.
         */
        notifyObserver();
    }

    /**
     * @Parametres aucuns.
     * @Output void.
     * @Modifie l'identifiant du joueur durant la phase de planification.
     *
     * Incrémente le compteur dès que le joueur actif appuie sur un bouton
     * lors de la phase de planification. Et si son nombre d'action équivaut
     * au nombre d'action maximal défini pour un seul joueur (ici 4) alors
     * on change de joueur. Et ainsi de suite jusqu'au dernier joueur. Puis
     * on reprend le tour au premier joueur.
     */
    private void updateAction() {
        this.coup_joueur++;
        if (this.coup_joueur == NB_COUPS_JOUEUR) {
            this.coup_joueur = 0;
            if (this.id_joueur == NB_JOUEURS-1) {
                this.id_joueur = 0;
            } else {
                this.id_joueur++;
            }
        }
    }

    /**
     * @Parametres aucuns.
     * @Output void.
     * @Modifie l'identifiant du joueur durant la phase action.
     *
     * Si l'identifiant du joueur actuel est égal au nombre de joueur
     * dans la partie alors on revient au premier joueur et ainsi de
     * suite. Cela va permettre de réaliser la 1ère action du joueur 0,
     * puis la 1ère du joueur 1, puis la 2ème de joueur 0 etc.
     */
    private void actionsJoueurs() {
        this.id_joueur_action++;
        if (this.id_joueur_action == NB_JOUEURS) {
            this.id_joueur_action = 0;
        }
    }

    /**
     * @Parametres aucuns.
     * @Output void.
     * @Compte le nombre de boutons sur lesquels on a appuyé durant la phase de planification.
     *
     * Cela va permettre de passer de la phase planification à la phase action. En effet, si
     * le nombre de coup joué sur un tour est égal au nombre maximal de coup autorisé, alors
     * on peut changer de phase. (et on reprend à 1 pour la prochaine phase de planification).
     */
    private void actionsTour() {
        this.coup_tour++;
        if (this.coup_tour == NB_COUPS_TOUR+1) {
            this.coup_tour = 1;
        }
    }

    /**
     * @Parametres aucuns.
     * @Output void.
     * @Compte le nombre de fois qu'on a appuyé sur le bouton action durant la phase d'action.
     *
     * Si le nombre d'action que l'on a réalisé équivaut au nombre de coup pendant un tour
     * alors on peut repasser à la phase de planification. Autrement on repart à 1.
     */
    private void boutonAction() {
        this.coup_action++;
        if (this.coup_action == NB_COUPS_TOUR+1) {
            this.coup_action = 1;
        }
        if (this.coup_action == NB_COUPS_TOUR) {
            this.id_joueur = 0;
        }
    }

    /**
     * @Parametres int, String.
     * @Output void.
     * @Ajoute pour l'id d'un joueur un nombre d'action de type String au dictionnaire actions.
     *
     * Si on est du point de vue du joueur d'id_joueur et qu'il clique sur un des boutons lors de
     * la planification, alors la clé id_joueur aura pour valeur un ArrayList auquel on ajoutera
     * le String action.
     * Si id_joueur est déjà dans le dictionnaire alors pas besoin de le créer et on peut directe-
     * ment l'ajouter au dictionnaire.
     * Sinon on doit créer une nouvelle liste d'action avec notre action et l'ajouter pour la clé
     * d'id_joueur.
     */
    public void add_action(int id_joueur, String action){
        if (this.actions.containsKey(id_joueur)){
            this.actions.get(id_joueur).add(action);
        } else {
            ArrayList<String> actions_joueur = new ArrayList<>();
            actions_joueur.add(action);
            this.actions.put(id_joueur, actions_joueur);
        }
        this.actionsTour();
        this.updateAction();
    }

    /**
     * @Parametres aucuns.
     * @Output void.
     * @Modifie le modèle en fonction des actions qui ont été réalisés avant durant la
     * planification par tous les joueurs.
     *
     * On commence par définir l'ordre de passage des joueurs avec la première instruction.
     * Si le nombre d'action du joueur est vide alors on return rien. Sinon, on enlève à sa
     * liste d'action dans le HashMap la première action dont on garde la valeur dans la
     * variable action. Ensuite, si cette variable équivaut à "braquage" alors on effectue
     * l'action braquage pour le joueur en question. Et ceci pour toutes les actions possibles
     * "TirEnHaut", "DeplAGauche"....
     * Une fois qu'on a fait la première action du premier joueur, on passe au deuxième joueur
     * avec this.actionsJoueurs et on signal avec boutonAction qu'on vient de cliquer une fois
     * sur le bouton d'action.
     */
    public void action() {
        this.id_joueur = this.id_joueur_action;
        if (this.actions.get(id_joueur_action) == null)
            return;
        String action = this.actions.get(id_joueur_action).remove(0);
        switch (action) {
            case "braquage":
                if (!this.posY()) {
                    this.braquage();
                    System.out.println(this.joueur().getNom() + " braque un butin dans le wagon " + this.joueur().getIdWagon() + ".");
                } else {
                    System.out.println(this.joueur().getNom() + " essaye de braquer un butin mais le wagon " + this.joueur().getIdWagon() + " n'en possède pas.");
                }
                break;
            case "tireEnHaut":
                this.tirEnHaut();
                break;
            case "tireEnBas":
                this.tirEnBas();
                break;
            case "tireADroite":
                this.tirADroite();
                break;
            case "tireAGauche":
                this.tirAGauche();
                break;
            case "deplEnHaut":
                this.deplEnHaut();
                break;
            case "deplEnBas":
                this.deplEnBas();
                break;
            case "deplAGauche":
                this.deplAGauche();
                break;
            case "deplADroite":
                this.deplADroite();
                break;
        }
        this.marshall.deplMarshall();
        this.actionSiMarshall();
        notifyObserver();
        this.actionsJoueurs();
        this.boutonAction();
    }
}

