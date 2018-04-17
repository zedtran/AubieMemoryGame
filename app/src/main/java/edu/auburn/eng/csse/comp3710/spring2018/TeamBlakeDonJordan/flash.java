package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Handler;

public class flash {
    /*
    AnimationDrawable adBtn;

    private void applyFlashBackgroundAnimation(){
        float[] radii = new float[8];
        radii[0] = getResources().getDimension(R.dimen.spacing_10);
        radii[1] = getResources().getDimension(R.dimen.spacing_10);
        radii[2] = getResources().getDimension(R.dimen.spacing_10);
        radii[3] = getResources().getDimension(R.dimen.spacing_10);

        radii[4] = getResources().getDimension(R.dimen.spacing_10);
        radii[5] = getResources().getDimension(R.dimen.spacing_10);
        radii[6] = getResources().getDimension(R.dimen.spacing_10);
        radii[7] = getResources().getDimension(R.dimen.spacing_10);
        ShapeDrawable selShape = new ShapeDrawable();
        selShape.setShape(new RoundRectShape(radii, null, null));
        selShape.getPaint().setColor(Color.GREEN);

        ShapeDrawable defShape = new ShapeDrawable();
        defShape.setShape(new RoundRectShape(radii, null, null));
        defShape.getPaint().setColor(Color.GRAY);

        adBtn = new AnimationDrawable();
        adBtn.addFrame(selShape, DELAY);
        adBtn.addFrame(defShape, DELAY);
        adBtn.setOneShot(false);
        btnObj.setBackgroundDrawable(adPick);//btnObj is your Button object
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adBtn.start();
            }
        }, 100);
    }

    //To Stop Animation, simply use stop method of AnimationDrawable object as below.
    private void stopFlashAnimation(){
        adBtn.stop();
    }

    ObjectAnimator objAnim;
    private void pulseAnimation(){
        objAnim= ObjectAnimator.ofPropertyValuesHolder(btnObj, PropertyValuesHolder.ofFloat("scaleX", 1.5f), PropertyValuesHolder.ofFloat("scaleY", 1.5f));
        objAnim.setDuration(300);
        objAnim.setRepeatCount(ObjectAnimator.INFINITE);
        objAnim.setRepeatMode(ObjectAnimator.REVERSE);
        objAnim.start();
    }

    //To Stop Animation, simply use cancel method of ObjectAnimator object as below.

    private void stopPulseAnimation(){
        objAnim.cancel();
    }

    */
}
