package fr.unice.polytech.polyincidents;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;


import com.miguelcatalan.materialsearchview.MaterialSearchView;

/**
 * Created by user on 07/05/2018.
 */

public class FragmentSearch extends Fragment {

    MaterialSearchView searchView;

    public static FragmentSearch newInstance() {
        FragmentSearch fragment = new FragmentSearch();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        ((MenuActivity)getActivity()).setSupportActionBar(toolbar);
        ((MenuActivity)getActivity()).getSupportActionBar().setTitle("Recherche Incidents");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        searchView = (MaterialSearchView)rootView.findViewById((R.id.search_view));

        return rootView;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        ((MenuActivity)getActivity()).getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

}
