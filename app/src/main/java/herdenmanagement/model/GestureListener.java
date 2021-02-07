package herdenmanagement.model;

import android.view.GestureDetector;
import android.view.MotionEvent;

import herdenmanagement.HerdenManager;

public class GestureListener extends GestureDetector.SimpleOnGestureListener {
    private HerdenManager herdenManager;
    
    //Konstruktor der den HerdenManager übernimmt um das Rind darauf zu bewegen
    public GestureListener(HerdenManager manager) {
        herdenManager = manager;
    }

    //Hier wird das Wisch Event behandelt
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        
        //Mithilfe der Abstände wird die Richtung des Wischens ermittelt
        float abstandY = e2.getY() - e1.getY();
        float abstandX = e2.getX() - e1.getX();
        
        //Wenn die Distanz x größer als die von y ist wurde horizontal gewischt ansonsten vertikal
        if (Math.abs(abstandX) > Math.abs(abstandY)) {
            //Wenn x größer als 0 ist wurde von links nach rechts gewischt ansonsten von rechts nach links
            if (abstandX > 0) {
                herdenManager.getRind().bewegeRind(Rindvieh.RichtungsTyp.OST);
            } else {
                herdenManager.getRind().bewegeRind(Rindvieh.RichtungsTyp.WEST);
            }
        } else {
            //Wenn y größer als 0 ist wurde von oben nach unten gewischt ansonsten von unten nach oben
            if (abstandY > 0) {
                herdenManager.getRind().bewegeRind(Rindvieh.RichtungsTyp.SUED);
            } else {
                herdenManager.getRind().bewegeRind(Rindvieh.RichtungsTyp.NORD);
            }
            //Die Bewegungsbefehle wurden entsprechend ausgeführt
        }
        //etwas machen
        //Toast.makeText(MainActivity.this, "Hallo",Toast.LENGTH_SHORT).show();
        return super.onFling(e1, e2, velocityX, velocityY);
    }
}
