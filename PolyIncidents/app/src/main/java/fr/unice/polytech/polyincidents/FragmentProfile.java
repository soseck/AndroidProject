package fr.unice.polytech.polyincidents;

/**
 * Created by user on 07/05/2018.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentProfile extends Fragment {
    public static final String SCRIPT_FILE = "/getIncidentsByUser.php";
    public static final Integer VIEW_ID = R.id.profileListView;

    public static FragmentProfile newInstance() {
        FragmentProfile fragment = new FragmentProfile();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences(LoginActivity.USER_PREF_NAME, Context.MODE_PRIVATE);
        ((TextView)rootView.findViewById(R.id.surname)).setText(preferences.getString(LoginActivity.SURNAME_PREF_KEY, ""));
        ((TextView)rootView.findViewById(R.id.name)).setText(preferences.getString(LoginActivity.NAME_PREF_KEY, ""));
        ((TextView)rootView.findViewById(R.id.username)).setText(preferences.getString(LoginActivity.USERNAME_PREF_KEY, ""));
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new BackgroundNewsFeedManager(this.getContext(), SCRIPT_FILE, VIEW_ID).execute(NewsGroup.BY_USER);
    }

}