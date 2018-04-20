package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.widget.Button;

/**
 * Created by Jordan, Don, and Blake on 4/12/2018.
 */

public class Light {
    private Button mButton;


    public Button getButton(){
        return mButton;
    }
    public Animator flashButton() {
        ObjectAnimator anim = ObjectAnimator.ofFloat(mButton, "alpha", 1,0);
        anim.setDuration(500);
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.setRepeatCount(1);
        return anim;
    }

    public void setButton(Button tieIn){
        mButton = tieIn;
    }


}
