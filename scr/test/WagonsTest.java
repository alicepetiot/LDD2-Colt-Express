package test;
/**test */
import com.covidexpress.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Classe de tests pour la classe Wagons.
 */
public class WagonsTest {
        private Wagons wagon0;
        private Wagons wagon1;
        private Marshall marshall;
        private Bandits bandit0;
        private Bandits bandit1;
        private Bandits bandit2;
        private Butins butin0;
        private Butins butin1;
        private Butins butin2;
        private ArrayList<Butins> b0;
        private ArrayList<Butins> b1;
        private ArrayList<Bandits> ba0;
        private ArrayList<Bandits> ba1;

        @Before
        public void setUp() {
            wagon0 = new Wagons(0);
            wagon1 = new Wagons(1);
            butin0 = new Butins(0,0);
            butin1 = new Butins(1,0);
            butin2 = new Butins(2,0);
            marshall = new Marshall();
            bandit0 = new Bandits(0);
            bandit1 = new Bandits(1);
            bandit2 = new Bandits(2);
            b0 = new ArrayList<Butins>() {{ add(butin0); }};
            b1 = new ArrayList<Butins>() {{ add(butin1); add(butin2); }};
            ba0 = new ArrayList<Bandits>() {{ add(bandit2); }};
            ba1 = new ArrayList<Bandits>() {{ add(bandit0); add(bandit1); }};
        }

        @After
        public void setDown() {
            wagon0 = null;
            wagon1 = null;
            butin0 = null;
            butin1 = null;
            butin2 = null;
            b0 = null;
            b1 = null;
            ba0 = null;
            ba1 = null;
            bandit0 = null;
            bandit1 = null;
            bandit2 = null;
            marshall = null;
        }

        @Test
        public void setUpTest() {
            Assert.assertNotNull(this.wagon0);
            Assert.assertNotNull(this.wagon1);
            Assert.assertNotNull(this.butin0);
            Assert.assertNotNull(this.butin1);
            Assert.assertNotNull(this.butin2);
            Assert.assertNotNull(this.b0);
            Assert.assertNotNull(this.b1);
            Assert.assertNotNull(this.ba0);
            Assert.assertNotNull(this.ba1);
            Assert.assertNotNull(this.marshall);
        }

        @Test
        public void initWagonsTest() {
            Assert.assertEquals(0,this.wagon0.getId());
            Assert.assertEquals(1,this.wagon0.getNbButinsMIN());
            Assert.assertEquals(4,this.wagon0.getNbButinsMAX());
            Assert.assertTrue(this.wagon0.getBandits().isEmpty());
            Assert.assertFalse(this.wagon0.getButins().isEmpty());
            Assert.assertTrue(this.wagon0 instanceof Wagons);
        }

        @Test
        public void getIdTest() {
            Assert.assertEquals(0,this.wagon0.getId());
            Assert.assertEquals(1,this.wagon1.getId());
        }

        @Test
        public void getNbButinsMINTest() {
            Assert.assertEquals(1,this.wagon0.getNbButinsMIN());
            Assert.assertEquals(1,this.wagon1.getNbButinsMIN());
        }

        @Test
        public void getNbButinsMAXTest() {
            Assert.assertEquals(4,this.wagon0.getNbButinsMAX());
            Assert.assertEquals(4,this.wagon1.getNbButinsMAX());
        }

        @Test
        public void getBanditsTest() {
            this.wagon0.setBandits(this.ba0);
            this.wagon1.setBandits(this.ba1);
            Assert.assertEquals(this.ba0,this.wagon0.getBandits());
            Assert.assertEquals(this.ba1,this.wagon1.getBandits());
        }

        @Test
        public void getButinsTest() {
            this.wagon0.setButins(b0);
            this.wagon1.setButins(b1);
            Assert.assertEquals(this.b0,this.wagon0.getButins());
            Assert.assertEquals(this.b1,this.wagon1.getButins());
        }

        @Test
        public void getMarshallTest() {
            this.wagon0.setMarshall(this.marshall);
            Assert.assertEquals(this.marshall,this.wagon0.getMarshall());
        }

        @Test
        public void setIdTest() {
            this.wagon0.setId(2);
            this.wagon1.setId(5);
            Assert.assertEquals(2,this.wagon0.getId());
            Assert.assertEquals(1,this.wagon1.getId());
        }

        @Test
        public void setBanditsTest() {
            this.wagon0.setBandits(this.ba0);
            this.wagon1.setBandits(this.ba1);
            Assert.assertEquals(this.ba0,this.wagon0.getBandits());
            Assert.assertEquals(this.ba1,this.wagon1.getBandits());
        }

        @Test
        public void setButinsTest() {
            this.wagon0.setButins(this.b0);
            this.wagon1.setButins(this.b1);
            Assert.assertEquals(this.b0,this.wagon0.getButins());
            Assert.assertEquals(this.b1,this.wagon1.getButins());
        }

        @Test
        public void setMarshallTest() {
            this.wagon0.setMarshall(this.marshall);
            Assert.assertEquals(this.marshall,this.wagon0.getMarshall());
        }

        @Test
        public void randomButinsTest() {
            this.wagon0.setButins(new ArrayList<>());
            this.wagon1.setButins(new ArrayList<>());
            this.wagon0.randomButins();
            this.wagon1.randomButins();
            int sizeB0 = this.wagon0.getButins().size();
            int sizeB1 = this.wagon1.getButins().size();
            Assert.assertTrue(sizeB0 >= this.wagon0.getNbButinsMIN() && sizeB0 <= this.wagon0.getNbButinsMAX());
            Assert.assertTrue(sizeB1 >= this.wagon1.getNbButinsMIN() && sizeB1 <= this.wagon1.getNbButinsMAX());
        }

        @Test
        public void afficheWagonTest() {
            this.wagon0.afficheWagon();
            this.wagon1.afficheWagon();
        }

        @Test
        public void addRemoveBanditsTest() {
            this.wagon0.setBandits(this.ba0);
            this.wagon0.addBandits(this.bandit1);
            this.wagon0.removeBandits(this.bandit0);
            this.wagon1.setBandits(this.ba1);
            this.wagon1.removeBandits(this.bandit1);
            ArrayList<Bandits> new_ba0 = new ArrayList<Bandits>() {{ add(bandit2); add(bandit1); }};
            ArrayList<Bandits> new_ba1 = new ArrayList<>() {{ add(bandit0); }};
            Assert.assertEquals(new_ba0,this.wagon0.getBandits());
            Assert.assertEquals(new_ba1,this.wagon1.getBandits());
        }

        @Test
        public void addRemoveButinsTest() {
            this.wagon0.setButins(this.b0);
            this.wagon1.setButins(this.b1);
            this.wagon0.addButins(butin1);
            this.wagon0.addButins(butin2);
            this.wagon1.removeButins(butin1);
            this.wagon1.removeButins(butin2);
            this.wagon1.removeButins(butin1);
            ArrayList<Butins> new_b0 = new ArrayList<>() {{ add(butin0); add(butin1); add(butin2); }};
            ArrayList<Butins> new_b1 = new ArrayList<>();
            Assert.assertEquals(new_b0,this.wagon0.getButins());
            Assert.assertEquals(new_b1,this.wagon1.getButins());
        }
}
