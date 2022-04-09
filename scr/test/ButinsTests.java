package test;
/**test */
import com.covidexpress.Butins;
import com.covidexpress.TModele;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.covidexpress.Butins.Nature;

public class ButinsTests {
    private Butins butin0;
    private Butins butin1;
    private Butins butin2;

    @Before
    public void setUp() {
        butin0 = new Butins(0,0);
        butin1 = new Butins(1,1);
        butin2 = new Butins(2,4,true);
    }

    @After
    public void setDown() {
        butin0 = null;
        butin1 = null;
        butin2 = null;
    }

    @Test
    public void setUpTest() {
        Assert.assertNotNull(this.butin0);
        Assert.assertNotNull(this.butin1);
        Assert.assertNotNull(this.butin2);
    }

    @Test
    public void initMarshallTest() {
        if (Nature.Bijou == this.butin0.getNature()) {
            Assert.assertEquals(this.butin0.getValeurBijou(),this.butin0.getValeur());
        } else {
            Assert.assertTrue(this.butin0.getValeurBijou() >= 0 && this.butin0.getValeur() <= 500);
        }
        Assert.assertEquals(1000,this.butin2.getValeur());
        Assert.assertEquals(Nature.Magot,this.butin2.getNature());
        Assert.assertFalse(this.butin0.getToit());
        Assert.assertEquals(0,this.butin0.getId());
        Assert.assertEquals(0,this.butin0.getIdWagon());
        Assert.assertTrue(this.butin0 instanceof Butins);
    }

    @Test
    public void getValeurTest() {
        if (Nature.Bijou == this.butin1.getNature()) {
            Assert.assertEquals(this.butin1.getValeurBijou(),this.butin1.getValeur());
        } else {
            Assert.assertTrue(this.butin1.getValeurBijou() >= 0 && this.butin1.getValeur() <= 500);
        }
        Assert.assertEquals(1000,this.butin2.getValeur());
    }

    @Test
    public void getIdTest() {
        Assert.assertEquals(0,this.butin0.getId());
        Assert.assertEquals(1,this.butin1.getId());
        Assert.assertEquals(2,this.butin2.getId());
    }

    @Test
    public void getValeurBijouTest() {
        Assert.assertEquals(500,this.butin0.getValeurBijou());
        Assert.assertEquals(500,this.butin1.getValeurBijou());
    }

    @Test
    public void getNatureTest() {
        Assert.assertTrue(this.butin0.getNature() == Nature.Bijou ||
                this.butin0.getNature() == Nature.Bourse);
        Assert.assertTrue(this.butin1.getNature() == Nature.Bijou ||
                this.butin1.getNature() == Nature.Bourse);
        Assert.assertEquals(Nature.Magot,this.butin2.getNature());
    }

    @Test
    public void getToitTest() {
        Assert.assertFalse(this.butin0.getToit());
        Assert.assertFalse(this.butin1.getToit());
        Assert.assertFalse(this.butin2.getToit());
    }

    @Test
    public void getIdWagonTest() {
        Assert.assertEquals(0,this.butin0.getIdWagon());
        Assert.assertEquals(1,this.butin1.getIdWagon());
        Assert.assertEquals(4,this.butin2.getIdWagon());
    }

    @Test
    public void setIdWagonTest() {
        this.butin0.setIdWagon(3);
        this.butin1.setIdWagon(-1);
        this.butin2.setIdWagon(TModele.NB_WAGONS+1);
        Assert.assertEquals(3,this.butin0.getIdWagon());
        Assert.assertEquals(1,this.butin1.getIdWagon());
        Assert.assertEquals(4,this.butin2.getIdWagon());
    }

    @Test
    public void setValeurTest() {
        this.butin0.setValeur(0);
        this.butin1.setValeur(300);
        Assert.assertEquals(300,this.butin1.getValeur());
    }

    @Test
    public void setIdTest() {
        this.butin0.setId(1);
        this.butin2.setId(0);
        Assert.assertEquals(1,this.butin0.getId());
        Assert.assertEquals(0,this.butin2.getId());
    }

    @Test
    public void setNatureTest() {
        this.butin0.setNature(Nature.Bijou);
        this.butin1.setNature(Nature.Bourse);
        this.butin2.setNature(Nature.Bijou);
        Assert.assertEquals(Nature.Bijou,this.butin0.getNature());
        Assert.assertEquals(Nature.Bourse,this.butin1.getNature());
        Assert.assertEquals(Nature.Magot,this.butin2.getNature());
        this.butin0.setNature(Nature.Magot);
    }

    @Test
    public void setToitTest() {
        this.butin0.setToit(true);
        this.butin1.setToit(false);
        this.butin2.setToit(true);
        Assert.assertTrue(this.butin0.getToit());
        Assert.assertFalse(this.butin1.getToit());
        Assert.assertTrue(this.butin2.getToit());
    }

    @Test
    public void afficheButinTest() {
        this.butin0.afficheButin();
        this.butin1.afficheButin();
        this.butin2.afficheButin();
    }

    @Test
    public void afficheButin2Test() {
        this.butin0.afficheButin2();
        System.out.println();
        this.butin1.afficheButin2();
        System.out.println();
        this.butin2.afficheButin2();
        System.out.println();
    }

    @Test
    public void randomValeurTest() {
        butin0.randomValeur();
        butin2.randomValeur();
        Assert.assertTrue(butin0.getValeur() >= 250 && butin0.getValeur() <= 500);
        Assert.assertTrue(butin2.getValeur() >= 250 && butin2.getValeur() <= 500);
    }
}
