package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/* Game Type Fragment
 * Players can choose to play Aubie's Rendition of Simon or the original Simon
 *
 * Created by Jordan, Don, and Blake on 4/20/2018.
 */
public class GameTypeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // if (savedInstanceState != null) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game_type, container, false);
        Button mSimonButton = v.findViewById(R.id.simon);
        Button mAubieButton = v.findViewById(R.id.aubie);
        //sets up listeners for buttons
        mSimonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                               //Simon
                Fragment nextFrag= new AubieFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("PLAY_ORIGINAL", true);  //sets bundle to be sent to next fragment
                nextFrag.setArguments(bundle);
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag,"findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
        mAubieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                               //Aubie
                Fragment nextFrag= new AubieFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("PLAY_ORIGINAL", false);  //sets bundle to be sent to next fragment
                nextFrag.setArguments(bundle);
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag,"findThisFragment")
                        .addToBackStack(null)
                        .commit();

            }
        });

        return v;
    }

    /*@Override
    public void onStop() {
        super.onStop();

    }*/
}
