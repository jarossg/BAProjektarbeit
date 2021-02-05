package herdenmanagement.model;


import herdenmanagement.HerdenManager;

public class SteuerRindvieh extends Rindvieh {

    private boolean sichtbar;

    public SteuerRindvieh(String name) {
        super(name);
        this.sichtbar =true;
    }
    @Override
    public void frissGras() {
        super.frissGras();
        gibAcker().setzeKot(gibPosition());



    }
    public void bewegeRind(RichtungsTyp richtung) {
        if (richtung.equals(this.gibRichtung())) {
            this.geheVor();
        } else {
            if (((richtung == RichtungsTyp.NORD) && (this.gibRichtung() == RichtungsTyp.SUED)) || ((richtung == RichtungsTyp.SUED) && (this.gibRichtung() == RichtungsTyp.NORD)) || ((richtung == RichtungsTyp.WEST) && (this.gibRichtung() == RichtungsTyp.OST)) || ((richtung == RichtungsTyp.OST) && (this.gibRichtung() == RichtungsTyp.WEST))) {
                this.dreheDichRechtsRum();
                this.dreheDichRechtsRum();
            } else {
                this.setRichtung(richtung);
            }
        }
    }

    public boolean getSichtbarkeit()  {
        return this.sichtbar;
    }
    public void setSichtbarkeit(boolean bool)  {
        this.sichtbar = bool;
    }
}
