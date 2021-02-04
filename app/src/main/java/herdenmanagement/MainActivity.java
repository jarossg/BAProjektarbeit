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
import herdenmanagement.model.GestureListener;
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

        mGestureDetector = new GestureDetectorCompat(this, (GestureDetector.OnGestureListener) new GestureListener(herdenManager));
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

