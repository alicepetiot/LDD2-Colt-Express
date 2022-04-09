package test;

/**test */
import com.covidexpress.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TModeleTest {
    private TModele modele;

    @Before
    public void setUp() {
        this.modele = new TModele();
    }

    @After
    public void setDown() {
        this.modele = null;
    }

    @Test
    public void setUpTest() {
        Assert.assertNotNull(this.modele);
    }

    @Test
    public void initModeleTest() {
        Assert.assertEquals(TModele.NB_WAGONS+1,this.modele.getWagons().length);
        for (int i = 0; i < TModele.NB_WAGONS+1; i++) {
            Assert.assertEquals(i,this.modele.getWagons()[i].getId());
            if (i != TModele.NB_WAGONS) {
                Assert.assertNull(this.modele.getWagons()[i].getMarshall());
                Assert.assertTrue(this.modele.getWagons()[i].getButins().size() >= Wagons.nbButinsMIN && this.modele.getWagons()[i].getButins().size() <= Wagons.nbButinsMAX);
            }
        }
        Assert.assertNotNull(this.modele.getWagons()[TModele.NB_WAGONS].getMarshall());
        Assert.assertEquals(1,this.modele.getWagons()[TModele.NB_WAGONS].getButins().size());
        Assert.assertEquals(Butins.Nature.Magot,this.modele.getWagons()[TModele.NB_WAGONS].getButins().get(0).getNature());
        Assert.assertEquals(-1,this.modele.getWagons()[TModele.NB_WAGONS].getButins().get(0).getId());
        Assert.assertEquals(0,this.modele.getIdJoueur());
        Assert.assertEquals(TModele.NB_JOUEURS,this.modele.getBandits().size());
        for (int i = 0; i < this.modele.getBandits().size(); i++) {
            Assert.assertEquals(i,this.modele.getBandits().get(i).getId());
            Assert.assertTrue(modele.getBandits().get(i).getToit());
        }
    }

    @Test
    public void getSetBanditsListTest() {
        Assert.assertEquals(TModele.NB_JOUEURS,this.modele.getBanditsList().size());
        for (Map.Entry<Integer,Bandits> bandits : this.modele.getBandits().entrySet()) {
            Bandits b = bandits.getValue();
            int id = bandits.getKey();
            Assert.assertEquals(bandits.getValue(),this.modele.getBanditsList().get(id));
        }
        for (Bandits bandits : this.modele.getBanditsList()) {
            bandits.afficheBandit();
        }
    }

    @Test
    public void getWagonsTest() {
        Assert.assertEquals(TModele.NB_WAGONS+1,this.modele.getWagons().length);
        for (int i = 0; i < TModele.NB_WAGONS+1; i++) {
            Assert.assertEquals(i,this.modele.getWagons()[i].getId());
            if (i != TModele.NB_WAGONS) {
                Assert.assertNull(this.modele.getWagons()[i].getMarshall());
                Assert.assertTrue(this.modele.getWagons()[i].getButins().size() >= Wagons.nbButinsMIN && this.modele.getWagons()[i].getButins().size() <= Wagons.nbButinsMAX);
            }
        }
        Assert.assertNotNull(this.modele.getWagons()[TModele.NB_WAGONS].getMarshall());
    }

    @Test
    public void getBanditsTest() {
        Assert.assertEquals(TModele.NB_JOUEURS,this.modele.getBandits().size());
        for (int i = 0; i < this.modele.getBandits().size(); i++) {
            Assert.assertEquals(i,this.modele.getBandits().get(i).getId());
            Assert.assertTrue(modele.getBandits().get(i).getToit());
        }
    }

    @Test
    public void getMarshallTest() {
        Assert.assertTrue(this.modele.getMarshall() instanceof Marshall);
        Assert.assertNotNull(this.modele.getMarshall());
    }


    @Test
    public void setWagonsTest() {
        Wagons wagon0 = new Wagons(0);
        Wagons wagon1 = new Wagons(1);
        Wagons wagon2 = new Wagons(2);
        Wagons list[] = {wagon0,wagon1,wagon2};
        this.modele.setWagons(list);
        Assert.assertEquals(3,this.modele.getWagons().length);
        Assert.assertEquals(this.modele.getWagons()[0],wagon0);
    }

    @Test
    public void setMarshallTest() {
        this.modele.setMarshall(null);
        Assert.assertNull(modele.getMarshall());
        Marshall marshall = new Marshall();
        this.modele.setMarshall(marshall);
        Assert.assertEquals(marshall,modele.getMarshall());
    }

    @Test
    public void setIdJoueurTest() {
        this.modele.setIdJoueur(3);
        Assert.assertEquals(3,this.modele.getIdJoueur());
    }

    @Test
    public void setActionsTest() {
        HashMap<Integer, ArrayList<String>>action= new HashMap<Integer, ArrayList<String>>();
        ArrayList<String> list = new ArrayList<String>();
        list.add("tireEnHaut");
        action.put(0,list);
        this.modele.setActions(action);
        Assert.assertEquals(action,modele.getActions());
    }

    @Test
    public void setCoupJoueurTest() {
        modele.setCoupJoueur(3);
        Assert.assertEquals(3,modele.getCoupJoueur());
    }

    @Test
    public void setCoupTourTest() {
        this.modele.setCoupTour(3);
        Assert.assertEquals(3,this.modele.getCoupTour());
    }

    @Test
    public void setCoupActionTest() {
        this.modele.setCoupAction(3);
        Assert.assertEquals(3,this.modele.getCoupAction());
    }

    @Test
    public void setIdJoueurActionTest() {}

    @Test
    public void posXTest() {
        this.modele.setIdJoueur(0);
        Assert.assertEquals(0,this.modele.posX());
        this.modele.joueur().setIdWagon(1);
        Assert.assertEquals(1,this.modele.posX());
        this.modele.setIdJoueur(1);
        Assert.assertEquals(0,this.modele.posX());
    }

    @Test
    public void posYTest() {
        this.modele.setIdJoueur(0);
        Assert.assertTrue(this.modele.posY());
        this.modele.setIdJoueur(1);
        Assert.assertTrue(this.modele.posY());
        this.modele.joueur().setToit(false);
        Assert.assertFalse(this.modele.posY());
    }

    @Test
    public void joueurTest() {
        if (TModele.NB_JOUEURS == 2) {
            Assert.assertEquals(this.modele.getBandits().get(0),this.modele.joueur());
            this.modele.setIdJoueur(1);
            Assert.assertEquals(this.modele.getBandits().get(1),this.modele.joueur());
        }
    }

    @Test
    public void dernierWagonTest() {
        Assert.assertTrue(this.modele.dernierWagon());
        modele.setIdJoueur(1);
        Assert.assertTrue(this.modele.dernierWagon());
    }

    @Test
    public void premierWagonTest() {
        Assert.assertFalse(this.modele.premierWagon());
        this.modele.setIdJoueur(1);
        Assert.assertFalse(this.modele.premierWagon());
        this.modele.joueur().setIdWagon(TModele.NB_WAGONS);
        Assert.assertTrue(this.modele.premierWagon());
    }

    @Test
    public void dansTrainTest() {
        this.modele.joueur().setIdWagon(-1);
        this.modele.joueur().setIdWagon(TModele.NB_WAGONS+1);
        this.modele.joueur().setIdWagon(TModele.NB_WAGONS);
        Assert.assertTrue(this.modele.dansTrain());
        this.modele.joueur().setIdWagon(0);
        Assert.assertTrue(this.modele.dansTrain());
        this.modele.joueur().setIdWagon(TModele.NB_WAGONS-1);
        Assert.assertTrue(this.modele.dansTrain());

    }

    @Test
    public void wagonGaucheTest() {
        Assert.assertNull(this.modele.wagonGauche());
        this.modele.joueur().setIdWagon(TModele.NB_WAGONS);
        Assert.assertEquals(this.modele.getWagons()[TModele.NB_WAGONS-1],this.modele.wagonGauche());
    }

    @Test
    public void wagonDroiteTest() {
        Assert.assertNotNull(this.modele.wagonDroite());
        this.modele.joueur().setIdWagon(TModele.NB_WAGONS);
        Assert.assertNull(this.modele.wagonDroite());
        this.modele.joueur().setIdWagon(0);
        Assert.assertEquals(this.modele.getWagons()[1],this.modele.wagonDroite());
    }

    @Test
    public void wagonActuelTest() {
        Assert.assertEquals(this.modele.getWagons()[0],this.modele.wagonActuel());
    }

    @Test
    public void banditsWagonsTest() {
        Assert.assertEquals(2,this.modele.banditsWagons(this.modele.wagonActuel()).size());
        Assert.assertEquals(0,this.modele.banditsWagons(this.modele.wagonDroite()).size());
        Assert.assertNull(this.modele.banditsWagons(this.modele.wagonGauche()));
    }

    @Test
    public void banditsToitTest() {
        Assert.assertEquals(2,this.modele.banditsToit(this.modele.banditsWagons(this.modele.wagonActuel())).size());
        Assert.assertEquals(0,this.modele.banditsToit(this.modele.banditsWagons(this.modele.wagonDroite())).size());
        Assert.assertNull(this.modele.banditsToit(this.modele.banditsWagons(this.modele.wagonGauche())));
    }

    @Test
    public void banditsInterieurTest() {
        Assert.assertEquals(0,this.modele.banditsInterieur(this.modele.banditsWagons(this.modele.wagonActuel())).size());
        Assert.assertEquals(0,this.modele.banditsInterieur(this.modele.banditsWagons(this.modele.wagonDroite())).size());
        Assert.assertNull(this.modele.banditsInterieur(this.modele.banditsWagons(this.modele.wagonGauche())));
    }

    @Test
    public void compteVoisinGaucheTest() {
        Assert.assertEquals(-1,this.modele.compteVoisinGauche());
        this.modele.joueur().setIdWagon(1);
        this.modele.getWagons()[0].removeBandits(this.modele.joueur());
        Assert.assertEquals(1,this.modele.compteVoisinGauche());
        this.modele.joueur().setToit(false);
        Assert.assertEquals(0,this.modele.compteVoisinGauche());
    }

    @Test
    public void compteVoisinDroiteTest() {
        this.modele.getWagons()[1].addBandits(new Bandits(2));
        this.modele.getWagons()[1].addBandits(new Bandits(3));
        Assert.assertEquals(2,this.modele.compteVoisinDroite());
        this.modele.joueur().setToit(false);
        Assert.assertEquals(0,this.modele.compteVoisinDroite());
        this.modele.joueur().setIdWagon(TModele.NB_WAGONS);
        Assert.assertEquals(-1,this.modele.compteVoisinDroite());
    }

    @Test
    public void compteVoisinDessusTest() {
        Assert.assertEquals(1,this.modele.compteVoisinDessus());
        this.modele.setIdJoueur(1);
        this.modele.joueur().setToit(false);
        this.modele.setIdJoueur(0);
        Assert.assertEquals(0,this.modele.compteVoisinDessus());
    }

    @Test
    public void compteVoisinDessousTest() {
        Assert.assertEquals(0,this.modele.compteVoisinDessous());
        this.modele.setIdJoueur(1);
        this.modele.joueur().setToit(false);
        this.modele.setIdJoueur(0);
        Assert.assertEquals(1,this.modele.compteVoisinDessous());
    }

    @Test
    public void posMarshallTest() {
        Assert.assertEquals(-2,this.modele.posMarshall());
        this.modele.joueur().setIdWagon(TModele.NB_WAGONS);
        Assert.assertEquals(TModele.NB_WAGONS,this.modele.posMarshall());
    }

    @Test
    public void leMarshallEstLaTest() {
        Assert.assertFalse(this.modele.leMarshallEstLa());
        this.modele.joueur().setIdWagon(TModele.NB_WAGONS);
        Assert.assertTrue(this.modele.leMarshallEstLa());
    }

    @Test
    public void actionSiMarshall() {
        this.modele.joueur().setIdWagon(TModele.NB_WAGONS);
        Butins b = new Butins(100,this.modele.joueur().getIdWagon());
        this.modele.joueur().ajouteButin(b);
        System.out.println(this.modele.joueur().getButin());
        this.modele.joueur().setToit(false);
        System.out.println(this.modele.getWagons()[TModele.NB_WAGONS].getButins());
        int sizeAvant = this.modele.getWagons()[TModele.NB_WAGONS].getButins().size();
        this.modele.actionSiMarshall();
        int sizeApres = this.modele.getWagons()[TModele.NB_WAGONS].getButins().size();
        Assert.assertTrue(this.modele.joueur().getToit());
        Assert.assertTrue(this.modele.joueur().getButin().isEmpty());
        System.out.println(this.modele.getWagons()[TModele.NB_WAGONS].getButins());
        Assert.assertTrue(this.modele.getWagons()[TModele.NB_WAGONS].getButins().contains(b));
        Assert.assertEquals(sizeApres,sizeAvant+1);
    }


    @Test
    public void tirEnHautTest() {
        int balleAvant = this.modele.joueur().getNB_BALLES();
        this.modele.setIdJoueur(1);
        Butins b = new Butins(101,0);
        Butins b2 = new Butins(102,0);
        this.modele.joueur().ajouteButin(b);
        this.modele.joueur().ajouteButin(b2);
        this.modele.setIdJoueur(0);
        this.modele.tirEnHaut();
        int balleApres = this.modele.joueur().getNB_BALLES();
        Assert.assertEquals(balleApres,balleAvant-1);
        Assert.assertTrue(this.modele.wagonActuel().getButins().contains(b)
                || this.modele.wagonActuel().getButins().contains(b2)
        );
        this.modele.setIdJoueur(1);
        Assert.assertEquals(1,this.modele.joueur().getButin().size());
    }

    @Test
    public void tirEnBasTest() {
        int balleAvant = this.modele.joueur().getNB_BALLES();
        this.modele.setIdJoueur(1);
        Butins b = new Butins(101,0);
        Butins b2 = new Butins(102,0);
        this.modele.joueur().setToit(false);
        this.modele.joueur().ajouteButin(b);
        this.modele.joueur().ajouteButin(b2);
        System.out.println(b);
        System.out.println(b2);
        this.modele.setIdJoueur(0);
        this.modele.tirEnBas();
        int balleApres = this.modele.joueur().getNB_BALLES();
        Assert.assertEquals(balleApres,balleAvant-1);
        Assert.assertTrue(this.modele.wagonActuel().getButins().contains(b)
                || this.modele.wagonActuel().getButins().contains(b2)
        );
        this.modele.setIdJoueur(1);
        Assert.assertEquals(1,this.modele.joueur().getButin().size());
    }

    @Test
    public void tirADroiteTest() {
        int balleAvant = this.modele.joueur().getNB_BALLES();
        this.modele.setIdJoueur(1);
        this.modele.joueur().setIdWagon(1);
        this.modele.wagonActuel().addBandits(this.modele.joueur());
        Butins b = new Butins(101,0);
        Butins b2 = new Butins(102,0);
        this.modele.joueur().ajouteButin(b);
        this.modele.joueur().ajouteButin(b2);
        this.modele.setIdJoueur(0);
        this.modele.tirADroite();
        int balleApres = this.modele.joueur().getNB_BALLES();
        Assert.assertEquals(balleApres,balleAvant-1);
        Assert.assertTrue(this.modele.wagonDroite().getButins().contains(b)
                || this.modele.wagonDroite().getButins().contains(b2)
        );
        this.modele.setIdJoueur(1);
        Assert.assertEquals(1,this.modele.joueur().getButin().size());
        this.modele.tirADroite();
        Assert.assertEquals(5,this.modele.joueur().getNB_BALLES());
    }

    @Test
    public void tirAGaucheTest() {
        int balleAvant = this.modele.joueur().getNB_BALLES();
        Butins b = new Butins(101,0);
        Butins b2 = new Butins(102,0);
        this.modele.joueur().ajouteButin(b);
        this.modele.joueur().ajouteButin(b2);
        this.modele.setIdJoueur(1);
        this.modele.joueur().setIdWagon(1);
        this.modele.wagonGauche().removeBandits(this.modele.joueur());
        this.modele.wagonActuel().addBandits(this.modele.joueur());
        this.modele.tirAGauche();
        int balleApres = this.modele.joueur().getNB_BALLES();
        Assert.assertEquals(balleApres,balleAvant-1);
        Assert.assertTrue(this.modele.wagonGauche().getButins().contains(b)
                || this.modele.wagonGauche().getButins().contains(b2)
        );
        this.modele.setIdJoueur(0);
        Assert.assertEquals(1,this.modele.joueur().getButin().size());
        this.modele.tirADroite();
        Assert.assertEquals(5,this.modele.joueur().getNB_BALLES());
    }

    @Test
    public void deplEnHautTest() {
        this.modele.deplEnHaut(); //non
        this.modele.deplEnBas(); //oui
        this.modele.deplEnBas(); //non
        this.modele.deplEnHaut(); //oui
    }


    @Test
    public void deplADroiteTest() {
        for (int i = 0; i <= TModele.NB_WAGONS; i++) {
            this.modele.deplADroite();
        }
    }

    @Test
    public void deplAGaucheTest() {
        if (TModele.NB_WAGONS == 4) {
            Assert.assertTrue(this.modele.dernierWagon());
            this.modele.deplAGauche(); //0
            Assert.assertTrue(this.modele.dernierWagon());
            this.modele.deplADroite(); //1
            this.modele.deplADroite(); //2
            this.modele.deplADroite(); //3
            this.modele.deplADroite(); //4
            Assert.assertFalse(this.modele.dernierWagon());
            Assert.assertTrue(this.modele.premierWagon());
            this.modele.deplAGauche(); //3
            this.modele.deplAGauche(); //2
            this.modele.deplAGauche(); //1
            Assert.assertFalse(modele.dernierWagon());
            this.modele.deplAGauche(); //0
            Assert.assertTrue(modele.dernierWagon());
        }
    }
}