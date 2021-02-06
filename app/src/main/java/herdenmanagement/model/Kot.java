package herdenmanagement.model;

public class Kot extends PositionsElement {
    Position position;

    //setze die Position des Kotes auf dem Acker
    public void positioniereDich(Acker acker, Position position) {
        setzeAcker(acker);
        setzePosition(position);
    }

}
