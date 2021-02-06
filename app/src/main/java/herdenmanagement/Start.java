package herdenmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Switch;

import de.ba.herdenmanagement.R;

public class Start extends AppCompatActivity {
    private ImageButton layoutChanger;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch Schalter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // App wird im Vollbild angezeigt

        layoutChanger = findViewById(R.id.changeLayoutButton); //Der Knopf zum Starten der MainActivity
        Schalter = findViewById(R.id.schalter); //Der Schalter zum Aktivieren des Steuerkreuzes

        layoutChanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start.this, MainActivity.class); //Intent zum Starten der MainActivity
                intent.putExtra("Steuerkreuz", Schalter.isChecked()); //Übergabe des Schalterzustands an die MainActivity
                startActivity(intent); //Start der MainActivity
            }
        });

    }
}