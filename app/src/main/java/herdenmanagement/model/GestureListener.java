package herdenmanagement.model;

import android.view.GestureDetector;
import android.view.MotionEvent;

import herdenmanagement.HerdenManager;

public class GestureListener extends GestureDetector.SimpleOnGestureListener
{
    private HerdenManager herdenManager;
    public GestureListener(HerdenManager manager)
    {
        herdenManager = manager;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        float abstandY = e2.getY() - e1.getY();
        float abstandX = e2.getX() - e1.getX();

        if(Math.abs(abstandX)>Math.abs(abstandY)){
            if(abstandX>0){
                herdenManager.rind.bewegeRind(Rindvieh.RichtungsTyp.OST);
            }else{
                herdenManager.rind.bewegeRind(Rindvieh.RichtungsTyp.WEST);
            }
        }else{
            if(abstandY>0){
                herdenManager.rind.bewegeRind(Rindvieh.RichtungsTyp.SUED);
            }else{
                herdenManager.rind.bewegeRind(Rindvieh.RichtungsTyp.NORD);
            }
        }
        //etwas machen
        //Toast.makeText(MainActivity.this, "Hallo",Toast.LENGTH_SHORT).show();
        return super.onFling(e1, e2, velocityX, velocityY);
    }
}
