package herdenmanagement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import herdenmanagement.model.Acker;
import herdenmanagement.model.Rindvieh;
import herdenmanagement.model.SteuerRindvieh;
import herdenmanagement.model.Steuerung;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProjektUnitTest {
    @Before
    public void setUp() {

    }

    @After
    public void shutDown() {
        // Daten löschen
    }


    @Test
    public void gibKotTest() {
        Acker acker = new Acker(5, 5); //erzeuge einen Acker

        SteuerRindvieh rindvieh = new SteuerRindvieh("Vera"); //erzeuge ein Rind

        acker.lassRindWeiden(rindvieh); //setze das Rind auf die Weide

        acker.lassGrasWachsen(rindvieh.gibPosition()); //lass Gras an der Position des Rindes wachsen

        assertTrue(acker.istDaGras(rindvieh.gibPosition())); //prüfe ob an der Position des Rindes Gras ist

        rindvieh.frissGras(); //lass das Rind das Gras fressen

        assertTrue(acker.istDaKot(rindvieh.gibPosition())); //prüfe ob der Kot erfolgreich gesetzt wurde

    }

    @Test
    public void gibSichtbarkeitTest() {
        Acker acker = new Acker(5, 5); //erzeuge einen Acker

        SteuerRindvieh rindvieh = new SteuerRindvieh("Vera"); //erzeuge ein Rind

        acker.lassRindWeiden(rindvieh); //setze das Rind auf die Weide

        rindvieh.setSichtbarkeit(false); //setze die Sichtbarkeit des Rinds auf false

        assertFalse(rindvieh.getSichtbarkeit()); //prüfe  ob die Sichtbarkeit auf false gesetzt ist
    }
}
