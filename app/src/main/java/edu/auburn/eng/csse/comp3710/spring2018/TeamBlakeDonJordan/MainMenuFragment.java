package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/* Main Menu Fragment
 *
 * Launch screen. User picks to either start the app, go to the leaderboards, change options,
 * or look at extras
 */
public class MainMenuFragment extends Fragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // if (savedInstanceState != null) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mainmenu, container, false);

        Button mPlayGameButton = v.findViewById(R.id.playGame);
        Button mLeaderBoardsButton = v.findViewById(R.id.leadboards);
        Button mOptionsButton = v.findViewById(R.id.options);
        Button mExtrasButton = v.findViewById(R.id.extra);

        //sets up listeners for buttons
        mPlayGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                               //Play
                Fragment nextFrag= new GameTypeFragment();
                Bundle bundle = new Bundle();
                nextFrag.setArguments(bundle);
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag,"findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
        mLeaderBoardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                               //Leaderboards
                Fragment nextFrag= new LeaderBoardsFragment();
                Bundle bundle = new Bundle();
                nextFrag.setArguments(bundle);
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag,"findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
        mExtrasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                               //ExtrasFragment
                Fragment nextFrag= new ExtrasFragment();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag,"findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
        mOptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                               //ExtrasFragment
                Fragment nextFrag= new OptionsFragment();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag,"findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        return v;
    }
}
