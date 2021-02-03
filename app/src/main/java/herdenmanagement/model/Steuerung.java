package herdenmanagement.model;

import android.view.View;
import android.widget.ImageButton;

import herdenmanagement.HerdenManager;
import herdenmanagement.MainActivity;

public class Steuerung
{
    public static void Bewegung(ImageButton hoch, ImageButton runter, ImageButton rechts, ImageButton links, final HerdenManager herdenManager)
    {
        hoch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                herdenManager.rind.geheHoch();
            }
        });
        runter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                herdenManager.rind.geheRunter();
            }
        });
        rechts.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                herdenManager.rind.geheRechts();
            }
        });
        links.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                herdenManager.rind.geheLinks();
            }
        });
    }

    public static void Management(ImageButton rauchen, ImageButton melken, ImageButton fressen, ImageButton spawn, final HerdenManager herdenManager)
    {
        rauchen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                herdenManager.rind.raucheGras();
            }
        });

        melken.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                herdenManager.rind.gibMilch();
            }
        });

        fressen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                herdenManager.rind.frissGras();
            }
        });

        spawn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Hier wird das Rind unsichtbar
            }
        });
    }
}
