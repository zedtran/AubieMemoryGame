package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

/**
 * Created by jsosn on 4/12/2018.
 */

public class Button {
    private String mColor = "";
    private boolean mIsLit = false;

    Button(String mColor){
        this.mColor = mColor;
    }
    public String getColor(String color) {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
    }

    public boolean isLit() {
        return mIsLit;
    }

    public void setLit(boolean lit) {
        mIsLit = lit;
    }
}
