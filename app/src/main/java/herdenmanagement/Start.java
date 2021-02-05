package herdenmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Switch;

import de.ba.herdenmanagement.R;

public class Start extends AppCompatActivity
{
    private ImageButton layoutChanger;
    private Switch Schalter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        layoutChanger = findViewById(R.id.changeLayoutButton);
        Schalter = findViewById(R.id.switch1);

        layoutChanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start.this, MainActivity.class);
                intent.putExtra("Steuerkreuz", Schalter.isChecked());
                startActivity(intent);
            }
        });

    }
}