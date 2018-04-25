package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.media.SoundPool;
import android.view.View;
import android.widget.Button;

public class Light {
    Button mButton;
    SoundPool mSound = new SoundPool.Builder().build();
    String mColor;
    Animator mAnimation;
    int mDifficultyModifier;

    Light(Button mButton, int mSound, String mColor, Context context, int mDifficultyModifier){
        this.mButton = mButton;
        this.mColor = mColor;
        this.mSound.load(context, mSound, 1);
        this.mDifficultyModifier = mDifficultyModifier;

        ObjectAnimator anim = ObjectAnimator.ofFloat(mButton, "alpha", 1, 0);   //sets so the opacity of the object goes from 100% to 0%
        anim.setDuration(500 / mDifficultyModifier);                          //time it takes to run the animator, so 500 milliseconds / difficulty modifier
        anim.setRepeatMode(ValueAnimator.REVERSE);      //runs the animator and then reverses it and runs it again
        anim.setRepeatCount(1);                         //repeats only once
        mAnimation = anim;

    }
    public Button getButton() {
        return mButton;
    }

    public SoundPool getSoundPool() {
        return mSound;
    }

    public String getColor() {
        return mColor;
    }

    public Animator getAnimation() {
        return mAnimation;
    }

    public void updateBoard(View v)
    {
        switch (mColor){
            case("red"):
                mButton = v.findViewById(R.id.red);
                break;
            case("blue"):
                mButton = v.findViewById(R.id.blue);
                break;
            case("yellow"):
                mButton = v.findViewById(R.id.yellow);
                break;
            case("green"):
                mButton = v.findViewById(R.id.green);
                break;
            case("orange"):
                mButton = v.findViewById(R.id.orange);
                break;
            default:
                break;
        }
    }
}
