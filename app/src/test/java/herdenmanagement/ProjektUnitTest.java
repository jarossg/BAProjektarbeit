package herdenmanagement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import herdenmanagement.model.Acker;
import herdenmanagement.model.Rindvieh;
import herdenmanagement.model.Steuerung;

import static org.junit.Assert.assertEquals;

public class ProjektUnitTest
{
    @Before
    public void setUp() {

    }

    @After
    public void shutDown() {
        // Daten löschen
    }



    @Test
    public void gibKotTest()
    {
        Acker acker = new Acker(5, 5);

        Rindvieh rindvieh = new Rindvieh("Vera");

        acker.lassRindWeiden(rindvieh);

        acker.lassGrasWachsen(rindvieh.gibPosition());

        assertEquals(true, acker.istDaGras(rindvieh.gibPosition()));

        rindvieh.frissGras();

        assertEquals(true, acker.istDaKot(rindvieh.gibPosition()));

    }

}
