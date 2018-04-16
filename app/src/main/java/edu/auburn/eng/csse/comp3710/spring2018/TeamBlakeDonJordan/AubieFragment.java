package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AubieFragment extends Fragment {

    private android.widget.Button mGreenButton;
    private android.widget.Button mOrangeButton;
    private android.widget.Button mYellowButton;
    private android.widget.Button mRedButton;
    private android.widget.Button mBlueButton;
    private Board mBoard;
    private static final String GREEN = "green";
    private static final String ORANGE = "orange";
    private static final String YELLOW = "yellow";
    private static final String RED = "red";
    private static final String BLUE = "blue";
    private boolean gameOver = false;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_aubieboard, container, false);

        TextView mTextView = (TextView) v.findViewById(R.id.test);
        mBoard = new Board();
        mTextView.setText(mBoard.getSequence());
        //creates all buttons
        mGreenButton = (android.widget.Button) v.findViewById(R.id.green);
        mOrangeButton = (android.widget.Button) v.findViewById(R.id.orange);
        mRedButton = (android.widget.Button) v.findViewById(R.id.red);
        mYellowButton = (android.widget.Button) v.findViewById(R.id.yellow);
        mBlueButton = (android.widget.Button) v.findViewById(R.id.blue);

        //sets up listeners for buttons
        mGreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                //Green
                gameOver = mBoard.checkInput(GREEN);                         //change later to button text
            }
        });
        mOrangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                //Orange
                gameOver = mBoard.checkInput(ORANGE);
            }
        });
        mYellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                //Yellow
                gameOver = mBoard.checkInput(YELLOW);
            }
        });
        mRedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                //Red
                gameOver = mBoard.checkInput(RED);
            }
        });
        mBlueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                //Blue
                gameOver = mBoard.checkInput(RED);
            }
        });

        return v;
    }
}
