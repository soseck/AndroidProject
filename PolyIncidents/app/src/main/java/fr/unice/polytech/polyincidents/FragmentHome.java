package fr.unice.polytech.polyincidents;

/**
 * Created by user on 07/05/2018.
 */


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentHome extends Fragment {
    public static final String SCRIPT_FILE = "/getAll.php";
    public static final Integer VIEW_ID = R.id.gridListView;

    public static FragmentHome newInstance() {
        FragmentHome fragment = new FragmentHome();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new BackgroundNewsFeedManager(this.getContext(), SCRIPT_FILE, VIEW_ID ).execute(NewsGroup.ALL);

    }
}