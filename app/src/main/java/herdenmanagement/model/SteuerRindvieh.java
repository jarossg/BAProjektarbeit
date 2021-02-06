package herdenmanagement.model;


import herdenmanagement.HerdenManager;

public class SteuerRindvieh extends Rindvieh {

    private boolean sichtbar; //Attribut für die Sichtbarkeit

    public SteuerRindvieh(String name) {
        super(name);
        this.sichtbar = true;
    }//Konstruktor

    @Override
    public void frissGras() {
        super.frissGras();
        gibAcker().setzeKot(gibPosition()); //setze Kot auf die Position des Rindes
    }

    public void bewegeRind(RichtungsTyp richtung) {
        if (richtung.equals(this.gibRichtung())) {
            this.geheVor();
        } else {
            //Drehung wenn das Rind nicht korrekt ausgerichtet ist
            //Die Drehung soll möglichst effizient passieren
            if (((richtung == RichtungsTyp.NORD) && (this.gibRichtung() == RichtungsTyp.SUED)) || ((richtung == RichtungsTyp.SUED) && (this.gibRichtung() == RichtungsTyp.NORD)) || ((richtung == RichtungsTyp.WEST) && (this.gibRichtung() == RichtungsTyp.OST)) || ((richtung == RichtungsTyp.OST) && (this.gibRichtung() == RichtungsTyp.WEST))) {
                this.dreheDichRechtsRum();
                this.dreheDichRechtsRum();
            } else {
                this.setRichtung(richtung);
            }
        }
    }

    public boolean getSichtbarkeit() {
        return this.sichtbar;
    } //getter für Sichtbarkeit

    public void setSichtbarkeit(boolean bool) {
        this.sichtbar = bool;
    }//Setter für Sichtbarkeit
}
