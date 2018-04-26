package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.media.SoundPool;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Light {
    private Button mButton;
    private SoundPool mSoundPool = new SoundPool.Builder().build();
    private int mSound;
    private String mColor;
    private int mDifficultyModifier;

    Light(Button mButton, int mSound, String mColor, Context context, int mDifficultyModifier){
        this.mButton = mButton;
        this.mColor = mColor;
        this.mSound = mSoundPool.load(context, mSound, 1);
        this.mDifficultyModifier = mDifficultyModifier;
    }
    public Button getButton() {
        return mButton;
    }

    public int getSoundPool() {
        return mSound;
    }

    public String getColor() {
        return mColor;
    }


    public Animator addAnimation(){
        ObjectAnimator anim = ObjectAnimator.ofFloat(mButton, "alpha", 1, 0);   //sets so the opacity of the object goes from 100% to 0%
        anim.setDuration(500 / mDifficultyModifier);                          //time it takes to run the animator, so 500 milliseconds / difficulty modifier
        anim.setRepeatMode(ValueAnimator.REVERSE);      //runs the animator and then reverses it and runs it again
        anim.setRepeatCount(1);                         //repeats only once
        return anim;
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
