package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class HolderActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aubie_main);

        Fragment nextFrag= new AubieFragment();
        boolean PLAY_ORIGINAL = getIntent().getExtras().getBoolean("PLAY_ORIGINAL");
        Bundle bundle = new Bundle();
        bundle.putBoolean("PLAY_ORIGINAL", PLAY_ORIGINAL);
        nextFrag.setArguments(bundle);
        final FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .add(R.id.fragment_container, nextFrag,"findThisFragment")
                .commit();
    }
}
