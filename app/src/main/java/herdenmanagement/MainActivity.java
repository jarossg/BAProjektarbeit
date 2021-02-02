package herdenmanagement;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;

import de.ba.herdenmanagement.R;
import herdenmanagement.model.Acker;
import herdenmanagement.view.AckerView;
import herdenmanagement.view.Animator;


public class MainActivity extends AppCompatActivity
{

    private static final String DEBUG_TAG = "Debug";
    private HerdenManager herdenManager;

    private Button hoch;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hoch = findViewById(R.id.button);
        hoch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                herdenManager.rind.geheVor();
            }
        });
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
}

