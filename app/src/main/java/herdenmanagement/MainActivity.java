package herdenmanagement;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.MotionEventCompat;

import de.ba.herdenmanagement.R;
import herdenmanagement.model.Acker;
import herdenmanagement.model.Rindvieh;
import herdenmanagement.model.Steuerung;
import herdenmanagement.view.AckerView;
import herdenmanagement.view.Animator;


public class MainActivity extends AppCompatActivity
{

    private static final String DEBUG_TAG = "Debug";
    public HerdenManager herdenManager;

    private ImageButton hoch;
    private ImageButton runter;
    private ImageButton rechts;
    private ImageButton links;

    private ImageButton rauchen;
    private ImageButton melken;
    private ImageButton fressen;
    private ImageButton spawn;

    private GestureDetectorCompat mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mGestureDetector = new GestureDetectorCompat(this, new GestureListener());


    }


    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    protected void onStart()
    {
        super.onStart();

        // erzeugt einen HerdenManager
        herdenManager = new HerdenManager();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                // Während manageHerde möchten wir alle Aktionen sehen
                AckerView ackerView = findViewById(R.id.acker_view);

                // Acker einrichten, dies soll in einem "Rutsch" passieren,
                // die einzelnen Aktionen werden nicht animiert
                ackerView.setThreading(Animator.Threading.ASYNCHRONOUS_NO_WAIT);
                herdenManager.richteAckerEin(MainActivity.this);

                // Während manageHerde möchten wir alle Aktionen einzeln nachvollziehen
                ackerView.setThreading(Animator.Threading.SYNCHRONOUS);

                // bewegt ein Rind oder mehrer auf dem Acker
                herdenManager.manageHerde(MainActivity.this);

                // Alle Aktionen auf dem Acker, die jetzt folgen, werden direkt asynchron
                // ausgeführt. Betroffen sind vor allem Button-Clicks
                ackerView.setThreading(Animator.Threading.ASYNCHRONOUS);
            }
        }).start();
        initializeButtons();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        // Den Acker der aktuellen AckerView ermitteln
        AckerView ackerView = findViewById(R.id.acker_view);
        Acker acker = ackerView.getAcker();

        Animator.Threading currentThreading = ackerView.getThreading();
        ackerView.setThreading(Animator.Threading.ASYNCHRONOUS_NO_WAIT);

        ackerView.setAcker(null);

        // Das neue Layout setzen
        // Damit wird auch ein eventuell für das Querformat definiertes Layout verwendet
        setContentView(R.layout.activity_main);

        // Die AckerView dürfte sich durch die vorhergehende Anweisung geändert haben
        // Diese wird zukünftig vom vorhandenen Acker genutzt
        ackerView = findViewById(R.id.acker_view);
        ackerView.setThreading(Animator.Threading.ASYNCHRONOUS_NO_WAIT);
        ackerView.setAcker(acker);

        // Den Zustand des Threadings wieder herstellen
        ackerView.setThreading(currentThreading);
    }
/*
    public boolean onTouchEvent(MotionEvent event)
    {

        int action = MotionEventCompat.getActionMasked(event);

        switch (action)
        {
            case (MotionEvent.ACTION_DOWN):
                Log.d(DEBUG_TAG, "Action was DOWN");
                return true;
            case (MotionEvent.ACTION_MOVE):
                Log.d(DEBUG_TAG, "Action was MOVE");
                return true;
            case (MotionEvent.ACTION_UP):
                Log.d(DEBUG_TAG, "Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL):
                Log.d(DEBUG_TAG, "Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE):
                Log.d(DEBUG_TAG, "Movement occurred outside bounds " +
                        "of current screen element");
                return true;
            default:
                return super.onTouchEvent(event);
        }


    }
*/

    void initializeButtons()
    {
        hoch = findViewById(R.id.hoch);
        runter = findViewById(R.id.runter);
        rechts = findViewById(R.id.rechts);
        links = findViewById(R.id.links);

        rauchen = findViewById(R.id.rauchen);
        melken = findViewById(R.id.melken);
        fressen = findViewById(R.id.fressen);
        spawn = findViewById(R.id.spawn);

        Steuerung.Bewegung(hoch,runter,rechts,links,herdenManager);
        Steuerung.Management(rauchen,melken,fressen,spawn,herdenManager);
    }
}

