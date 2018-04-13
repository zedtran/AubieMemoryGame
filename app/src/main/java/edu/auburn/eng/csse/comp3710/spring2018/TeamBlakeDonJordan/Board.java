package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jsosn on 4/12/2018.
 */

public class Board {

    private Button mBlueButton = new Button("blue");
    private Button mOrangeButton = new Button("orange");
    private Button mGreenButton = new Button("green");
    private Button mYellowButton = new Button("yellow");

    private ArrayList<String> mSequence;
    private int mInputNumber = 0;

    private int mScore = 0;

    /* getScore()
     * returns player score
     */
    public int getScore() {
        return mScore;
    }

    /* setScore(score)
     * returns player score
     */
    public void setScore(int score) {
        mScore = score;
    }

    /* getButton(color)
     * returns gets button object
     */
    public Button getButton(String color) {
        switch (color) {
            case "blue":
                return mBlueButton;
            case "orange":
                return mOrangeButton;
            case "green":
                return mGreenButton;
            case "yellow":
                return mYellowButton;
            default:
                return null;    //shouldnt happen
        }
    }

    /* setLit(color)
     * set button to light up
     * may be replaced in the future, not sure if
     * a function is worth it
     */
    public void setLit(String color){
        getButton(color).setLit(true);
    }

    /* inputSequence()
     * creates the input sequence that needs to
     * be replicated by the player
     */
    public void inputSequence(){
        String choices[] = {"blue", "orange", "green", "yellow"};
        mSequence = new ArrayList(mScore+1);
        mInputNumber = 0;
        Random rand = new Random();
        int mChoice;
        do{     //using a do here so when the score is initially zero, beginning of game, it will still loop
            mChoice = rand.nextInt(3);  //randomly generates sequence
            mSequence.add(choices[mChoice]);
            //add code to light up button
        }while(mScore > 0);
        mSequence.trimToSize(); //just in case mSequence gets to big
    }

    /* checkInput(color)
     * checks player input with the sequence that is expected to be inputted
     * if correct, the input number is increased
     * if the input number is equal to the length of the sequence list then a new sequence needs to be generated
     * if the sequence is not correct, the game is over
     */
    public boolean checkInput(String color){
        String correctColor = mSequence.get(mInputNumber);
        mInputNumber++;
        if(correctColor == color){//correct input
            if(mInputNumber == mSequence.size()){//new sequence needs to be generated
                mScore++;
            }
            else {}//waits for next button press
            return true;
        }
        else {  //incorrect input... game over
            return false;
        }
    }
}
