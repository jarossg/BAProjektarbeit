package herdenmanagement.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import de.ba.herdenmanagement.R;
import herdenmanagement.model.Eimer;
import herdenmanagement.model.Kot;

@SuppressLint("ViewConstructor")
public class KotView extends PositionElementView {


    public KotView(Context context, Animator animator, Kot kot) {
        super(context, animator, kot);
    } //Konstruktor


    protected Bitmap getAktuellesBild() {
        return BitmapFactory.decodeResource(getContext().getResources(), R.drawable.poo_schatten); //gebe das Icon zurück
    }
}
