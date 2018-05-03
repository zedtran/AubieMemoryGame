package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

/* Options Activity
 * uses the preferences persistence type to
 * save player options across the app /
 *
 * Created by Jordan, Don, and Blake on 4/23/2018.
 */
public class OptionsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aubie_main);
        if (savedInstanceState == null)
        {
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new SettingsFragment())
                    .commit();
        }
    }

    protected void onPause()
    {
        super.onPause();

        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(this);

        int modNumber = sharedPref.getInt("modified",0);
        modNumber += 1;

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("modified", modNumber);
        editor.apply();
    }

    public static class SettingsFragment extends PreferenceFragment
    {

        public SettingsFragment()
        {
        }

        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

        }
    }
}
