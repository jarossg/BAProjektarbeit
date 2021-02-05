package herdenmanagement;

import de.ba.herdenmanagement.R;
import herdenmanagement.model.Acker;
import herdenmanagement.model.Position;
import herdenmanagement.model.Rindvieh;
import herdenmanagement.model.SteuerRindvieh;
import herdenmanagement.view.AckerView;


public class HerdenManager
{

    public Acker acker;
    private SteuerRindvieh rind;

    public void richteAckerEin(MainActivity mainActivity)
    {
        // Die View, die die Acker Elememnte anzeigen kann, wird in der Datei
        // res/activity_main.xml optisch ausgerichtet und in der App platziert
        AckerView ackerView = mainActivity.findViewById(R.id.acker_view);

        // Acker erzeugen
        acker = new Acker(5, 7);

        // AckerView mit Acker verknüpfen
        ackerView.setAcker(acker);

        // Acker befüllen
        acker.lassGrasWachsen(new Position(1, 1));
        acker.stelleEimerAuf(new Position(2, 2));
        acker.lassGrasWachsen(new Position(2, 4));
        acker.lassGrasWachsen(new Position(2, 5));
        acker.lassGrasWachsen(new Position(3, 2));
        acker.lassGrasWachsen(new Position(3, 4));
    }

    public void manageHerde(final MainActivity mainActivity)
    {
        setRind(new SteuerRindvieh("Eugene"));
        acker.lassRindWeiden(getRind());
        //rind.raucheGras();


    }

    public void setRind(SteuerRindvieh rind) {
        this.rind = rind;
    }

    public SteuerRindvieh getRind() {
        return rind;
    }
}
