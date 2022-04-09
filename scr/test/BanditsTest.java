package test;
/**test */
import com.covidexpress.Bandits;
import com.covidexpress.Butins;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Classe de test pour la classe Bandits.
 */
public class BanditsTest {

    private Bandits bandit0;
    private Bandits bandit1;
    private Bandits bandit2;
    private Butins butin0;
    private Butins butin1;
    private Butins butin2;

    @Before
    public void setUp() {
        bandit0 = new Bandits(0);
        bandit1 = new Bandits(1);
        bandit2 = new Bandits(2);
        butin0 = new Butins(0,0);
        butin1 = new Butins(1,0);
        butin2 = new Butins(2,0);
    }

    @After
    public void setDown() {
        bandit0 = null;
        bandit1 = null;
        bandit2 = null;
        butin0 = null;
        butin1 = null;
        butin2 = null;
    }


    @Test
    public void setUpTest() {
        Assert.assertNotNull(this.bandit0);
        Assert.assertNotNull(this.bandit1);
        Assert.assertNotNull(this.bandit2);
        Assert.assertNotNull(this.butin0);
        Assert.assertNotNull(this.butin1);
        Assert.assertNotNull(this.butin2);
    }

    @Test
    public void initBandit() {
        System.out.println(this.bandit0.getId());
        System.out.println(this.bandit1.getId());
        System.out.println(this.bandit2.getId());
        Assert.assertEquals(0,this.bandit0.getIdWagon());
        Assert.assertEquals(6,this.bandit0.getNB_BALLES());
        Assert.assertEquals(0,this.bandit0.getId());
        Assert.assertEquals("Bella",this.bandit0.getNom());
        Assert.assertTrue(this.bandit0.getToit());
        Assert.assertTrue(this.bandit0.getButin().isEmpty());
        boolean instance = true;
        for (int i = 0; i < this.bandit0.getButin().size(); i++) {
            if (!(this.bandit0.getButin().get(i) instanceof Butins)) {
                instance = false;
            }
        }
        Assert.assertTrue(instance);
    }

    @Test
    public void getNomTest() {
        Assert.assertEquals(this.bandit0.getNom(),"Bella");
        Assert.assertEquals(this.bandit1.getNom(),"Cheyenne");
        Assert.assertEquals(this.bandit2.getNom(),"Django");
    }

    @Test
    public void getIdTest() {
        Assert.assertEquals(0,this.bandit0.getId());
        Assert.assertEquals(1,this.bandit1.getId());
        Assert.assertEquals(2,this.bandit2.getId());
    }

    @Test
    public void getNB_BALLESTest() {
        Assert.assertEquals(6,this.bandit0.getNB_BALLES());
        Assert.assertEquals(6,this.bandit1.getNB_BALLES());
        Assert.assertEquals(6,this.bandit2.getNB_BALLES());
    }

    @Test
    public void getToitTest() {
        Assert.assertTrue(this.bandit0.getToit());
        Assert.assertTrue(this.bandit1.getToit());
        Assert.assertTrue(this.bandit2.getToit());
    }

    @Test
    public void getIdWagonTest() {
        Assert.assertEquals(0,this.bandit0.getIdWagon());
        Assert.assertEquals(0,this.bandit1.getIdWagon());
        Assert.assertEquals(0,this.bandit2.getIdWagon());
    }

    @Test
    public void getButinsTest() {
        Assert.assertEquals(new ArrayList<Butins>(),this.bandit0.getButin());
        Assert.assertEquals(new ArrayList<Butins>(),this.bandit1.getButin());
        Assert.assertEquals(new ArrayList<Butins>(),this.bandit2.getButin());
    }

    @Test
    public void setIdTest() {
        this.bandit0.setId(2);
        this.bandit1.setId(1);
        this.bandit2.setId(0);
        Assert.assertEquals(2,this.bandit0.getId());
        Assert.assertEquals(1,this.bandit1.getId());
        Assert.assertEquals(0,this.bandit2.getId());
    }

    @Test
    public void setToitTest() {
        this.bandit1.setToit(false);
        Assert.assertFalse(this.bandit1.getToit());
    }

    @Test
    public void setIdWagonTest() {
        this.bandit0.setIdWagon(2);
        this.bandit1.setIdWagon(1);
        this.bandit2.setIdWagon(-1);
        Assert.assertEquals(2,this.bandit0.getIdWagon());
        Assert.assertEquals(1,this.bandit1.getIdWagon());
        Assert.assertEquals(0,this.bandit2.getIdWagon());
    }

    @Test
    public void setButinsTest() {
        ArrayList<Butins> b0 = new ArrayList<Butins>() {{ add(butin0); }};
        ArrayList<Butins> b1 = new ArrayList<Butins>() {{ add(butin0); add(butin1); add(butin2); }};
        ArrayList<Butins> b2 = new ArrayList<Butins>();
        this.bandit0.setButins(b0);
        this.bandit1.setButins(b1);
        this.bandit2.setButins(b2);
        Assert.assertEquals(b0,this.bandit0.getButin());
        Assert.assertEquals(b1,this.bandit1.getButin());
        Assert.assertEquals(b2,this.bandit2.getButin());
    }

    @Test
    public void setNB_BALLESTest() {
        this.bandit0.setNB_BALLES(2);
        this.bandit1.setNB_BALLES(7);
        this.bandit2.setNB_BALLES(-1);
        Assert.assertEquals(2,this.bandit0.getNB_BALLES());
        Assert.assertEquals(6,this.bandit1.getNB_BALLES());
        Assert.assertEquals(6,this.bandit2.getNB_BALLES());
    }

    @Test
    public void afficheBanditTest() {
        this.bandit0.afficheBandit();
        this.bandit1.afficheBandit();
        this.bandit2.afficheBandit();
    }

    @Test
    public void ajouteRemoveButinTest() {
        this.bandit0.ajouteButin(this.butin0);
        this.bandit0.ajouteButin(this.butin2);
        this.bandit1.ajouteButin(this.butin1);
        this.bandit1.ajouteButin(this.butin1);
        Assert.assertEquals(new ArrayList<>() {{ add(butin0); add(butin2); }},this.bandit0.getButin());
        Assert.assertEquals(new ArrayList<>() {{ add(butin1); }},this.bandit1.getButin());
        this.bandit0.removeButin(this.butin2);
        this.bandit1.removeButin(this.butin1);
        this.bandit1.removeButin(this.butin1);
        this.bandit1.removeButin(this.butin0);
        Assert.assertEquals(new ArrayList<>() {{ add(butin0); }},this.bandit0.getButin());
        Assert.assertEquals(new ArrayList<>(),this.bandit1.getButin());
    }


    @Test
    public void deplWagonAvantTest() {
        this.bandit0.setIdWagon(2);
        this.bandit0.deplWagonGauche();
        this.bandit0.deplWagonGauche();
        this.bandit1.setIdWagon(0);
        this.bandit1.deplWagonGauche();
        this.bandit2.setIdWagon(4);
        Assert.assertEquals(0,this.bandit0.getIdWagon());
        Assert.assertEquals(0,this.bandit1.getIdWagon());
        Assert.assertEquals(4,this.bandit2.getIdWagon());
    }

    @Test
    public void deplWagonDroiteTest() {
        this.bandit0.setIdWagon(2);
        this.bandit0.deplWagonDroite();
        this.bandit0.deplWagonDroite();
        this.bandit1.setIdWagon(4);
        this.bandit1.deplWagonDroite();
        this.bandit2.setIdWagon(0);
        Assert.assertEquals(4,this.bandit0.getIdWagon());
        Assert.assertEquals(4,this.bandit1.getIdWagon());
        Assert.assertEquals(0,this.bandit2.getIdWagon());
    }

    @Test
    public void deplWagonMonterTest() {
        this.bandit0.setToit(true);
        this.bandit1.setToit(false);
        this.bandit0.deplWagonMonter();
        this.bandit1.deplWagonMonter();
        Assert.assertTrue(this.bandit0.getToit());
        Assert.assertTrue(this.bandit1.getToit());
    }

    @Test
    public void deplWagonDescendreTest() {
        this.bandit0.setToit(true);
        this.bandit1.setToit(false);
        this.bandit0.deplWagonDescendre();
        this.bandit1.deplWagonDescendre();
        Assert.assertFalse(this.bandit0.getToit());
        Assert.assertFalse(this.bandit1.getToit());
    }

    @Test
    public void tirerSurBanditTest() {
        this.bandit0.setNB_BALLES(3);
        this.bandit0.tirerSurBandit();
        this.bandit0.tirerSurBandit();
        this.bandit1.setNB_BALLES(0);
        this.bandit1.tirerSurBandit();
        Assert.assertEquals(1,this.bandit0.getNB_BALLES());
        Assert.assertEquals(0,this.bandit1.getNB_BALLES());
    }
}
