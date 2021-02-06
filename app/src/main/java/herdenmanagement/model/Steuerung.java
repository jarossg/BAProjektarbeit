package herdenmanagement.model;

import android.view.View;
import android.widget.ImageButton;

import herdenmanagement.HerdenManager;
import herdenmanagement.MainActivity;

public class Steuerung {
    public static void Bewegung(ImageButton hoch, ImageButton runter, ImageButton rechts, ImageButton links, final HerdenManager herdenManager) {
        //Setze OnClickListener für das Steuerkreuz und definiere das Verhalten
        hoch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                herdenManager.getRind().bewegeRind(Rindvieh.RichtungsTyp.NORD);
            }
        });
        runter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                herdenManager.getRind().bewegeRind(Rindvieh.RichtungsTyp.SUED);
            }
        });
        rechts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                herdenManager.getRind().bewegeRind(Rindvieh.RichtungsTyp.OST);
            }
        });
        links.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                herdenManager.getRind().bewegeRind(Rindvieh.RichtungsTyp.WEST);
            }
        });
    }

    public static void Management(ImageButton rauchen, ImageButton melken, ImageButton fressen, ImageButton spawn, final HerdenManager herdenManager) {
        //Setze OnClickListener für die restlichen Buttons und definiere das Verhalten
        rauchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lasse das Rind Gras rauchen
                herdenManager.getRind().raucheGras();
            }
        });

        melken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lass das Rind Milch geben
                herdenManager.getRind().gibMilch();
            }
        });

        fressen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lass das Rind Gras fressen
                herdenManager.getRind().frissGras();
            }
        });

        spawn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Wenn das Rind sichtbar ist, setze es unsichtbar und umgekehrt
                if (herdenManager.getRind().getSichtbarkeit()) {
                    herdenManager.acker.macheRindUnsichtbar(herdenManager.getRind());
                    herdenManager.getRind().setSichtbarkeit(false);
                } else {
                    herdenManager.acker.macheRindSichtbar(herdenManager.getRind());
                    herdenManager.getRind().setSichtbarkeit(true);
                }

            }
        });
    }
}
