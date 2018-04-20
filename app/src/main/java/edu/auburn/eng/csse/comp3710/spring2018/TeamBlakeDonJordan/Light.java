package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.widget.Button;

/**
 * Created by jsosn on 4/12/2018.
 */

public class Light {
    private String mColor = "";
    private boolean mIsLit = false;
    private Button mButton;

    Light(String mColor){
        this.mColor = mColor;
    }
    public String getColor(String color) {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
    }

    public Button getButton(){
        return mButton;
    }

    public void flashButton() {

        android.view.animation.Animation mAnimation = new android.view.animation.AlphaAnimation(1, 0);
        mAnimation.setDuration(200);
        mAnimation.setInterpolator(new android.view.animation.LinearInterpolator());
        mAnimation.setRepeatCount(1);
        mAnimation.setRepeatMode(android.view.animation.Animation.REVERSE);
        mButton.startAnimation(mAnimation);


    }

    public void setButton(Button tieIn){
        mButton = tieIn;
    }


}
