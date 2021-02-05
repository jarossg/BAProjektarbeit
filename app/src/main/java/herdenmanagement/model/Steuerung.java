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
                herdenManager.rind.bewegeRind(Rindvieh.RichtungsTyp.NORD);
            }
        });
        runter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                herdenManager.rind.bewegeRind(Rindvieh.RichtungsTyp.SUED);
            }
        });
        rechts.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                herdenManager.rind.bewegeRind(Rindvieh.RichtungsTyp.OST);
            }
        });
        links.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                herdenManager.rind.bewegeRind(Rindvieh.RichtungsTyp.WEST);
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

                if(herdenManager.rind.sichtbar)
                {
                    herdenManager.acker.macheRindUnsichtbar(herdenManager.rind);
                    herdenManager.rind.sichtbar = false;
                }
                else
                {
                    herdenManager.acker.macheRindSichtbar(herdenManager.rind);
                    herdenManager.rind.sichtbar = true;
                }

            }
        });
    }
}
