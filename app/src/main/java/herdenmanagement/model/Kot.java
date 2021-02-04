package herdenmanagement.model;

public class Kot extends PositionsElement
{
    Position position;

    public void positioniereDich(Acker acker, Position position)
    {
        setzeAcker(acker);
        setzePosition(position);
    }

}
