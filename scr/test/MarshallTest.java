package test;
/**test */
import com.covidexpress.Marshall;
import com.covidexpress.Butins;
import com.covidexpress.TModele;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de tests pour la classe Marshall.
 */
public class MarshallTest {
    private Marshall marshall0;
    private Marshall marshall1;
    private Butins butin0;
    private Butins butin1;
    private Butins butin2;

    @Before
    public void setUp() {
        marshall0 = new Marshall();
        marshall1 = new Marshall();
        butin0 = new Butins(0,0);
        butin1 = new Butins(1,0);
        butin2 = new Butins(2,0);
    }

    @After
    public void setDown() {
        marshall0 = null;
        marshall1 = null;
        butin0 = null;
        butin1 = null;
        butin2 = null;
    }

    @Test
    public void setUpTest() {
        Assert.assertNotNull(this.marshall0);
        Assert.assertNotNull(this.marshall1);
        Assert.assertNotNull(this.butin0);
        Assert.assertNotNull(this.butin1);
        Assert.assertNotNull(this.butin2);
    }

    @Test
    public void initMarshallTest() {
        Assert.assertEquals(0.3, this.marshall0.getNERVOSITE_MARSHALL(), 0);
        Assert.assertEquals("Samuel Ford", this.marshall0.getNom());
        Assert.assertFalse(this.marshall0.getToit());
        Assert.assertEquals(-1, this.marshall0.getId());
        Assert.assertEquals(TModele.NB_WAGONS, this.marshall0.getIdWagon());
        Assert.assertTrue(marshall0 instanceof Marshall);
    }

    @Test
    public void getNERVOSITE_MARSHALLTest() {
        Assert.assertEquals(0.3, this.marshall0.getNERVOSITE_MARSHALL(), 0);
        Assert.assertEquals(0.3, this.marshall1.getNERVOSITE_MARSHALL(), 0);
    }

    @Test
    public void getNomTest() {
        Assert.assertEquals("Samuel Ford", this.marshall0.getNom());
        Assert.assertEquals("Samuel Ford", this.marshall1.getNom());
    }

    @Test
    public void getToitTest() {
        Assert.assertFalse(this.marshall0.getToit());
        Assert.assertFalse(this.marshall1.getToit());
    }

    @Test
    public void getIdTest() {
        Assert.assertEquals(-1, this.marshall0.getId());
        Assert.assertEquals(-1, this.marshall1.getId());
    }

    @Test
    public void getIdWagonTest() {
        Assert.assertEquals(TModele.NB_WAGONS, this.marshall0.getIdWagon());
        Assert.assertEquals(TModele.NB_WAGONS, this.marshall1.getIdWagon());
    }

    @Test
    public void deplMarshall() {
        this.marshall0.deplMarshall();
        Assert.assertTrue(marshall0.getIdWagon() >= 0 && marshall0.getIdWagon() <= TModele.NB_WAGONS);
    }


    @Test
    public void deplDroiteGauche() {
        this.marshall0.deplGauche();
        this.marshall0.deplGauche();
        this.marshall0.deplDroite();
        Assert.assertTrue(marshall0.getIdWagon() >= 0 && marshall0.getIdWagon() <= TModele.NB_WAGONS);
        Assert.assertEquals(TModele.NB_WAGONS - 1, this.marshall0.getIdWagon());
        this.marshall1.deplDroite();
        Assert.assertTrue(marshall1.getIdWagon() >= 0 && marshall1.getIdWagon() <= TModele.NB_WAGONS);
        Assert.assertEquals(TModele.NB_WAGONS, this.marshall1.getIdWagon());
        for (int i = 0; i < TModele.NB_WAGONS; i++) {
            this.marshall0.deplGauche();
        }
    }
}
