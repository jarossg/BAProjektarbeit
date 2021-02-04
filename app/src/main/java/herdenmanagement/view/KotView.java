package herdenmanagement.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import de.ba.herdenmanagement.R;
import herdenmanagement.model.Eimer;
import herdenmanagement.model.Kot;

public class KotView extends PositionElementView
{


    public KotView(Context context, Animator animator, Kot kot) {
        super(context, animator, kot);
    }


    protected Bitmap getAktuellesBild() {
        return BitmapFactory.decodeResource(getContext().getResources(), R.drawable.poo_schatten);
    }
}
