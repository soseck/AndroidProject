package fr.unice.polytech.polyincidents;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ListView;


import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.zip.Inflater;

import static android.support.v4.media.session.MediaButtonReceiver.handleIntent;

/**
 * Created by user on 07/05/2018.
 */

public class FragmentSearch extends Fragment  {

    MaterialSearchView searchView;

    public static FragmentSearch newInstance() {
        FragmentSearch fragment = new FragmentSearch();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//Make sure you have this line of code.
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        /*
       Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar_help);
        ((MenuActivity)getActivity()).setSupportActionBar(toolbar);
        ((MenuActivity)getActivity()).getSupportActionBar().setTitle("Recherche Incidents");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));


        searchView = (MaterialSearchView)rootView.findViewById((R.id.list_view));*/
        return rootView;
    }

/*
    public boolean onCreateOptionsMenu(Menu menu) {
        ((MenuActivity) getActivity()).getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }*/

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu, inflater);

        this.getActivity().getMenuInflater().inflate(R.menu.menu_search,menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //votre code ici
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

       // return true;
    }




}
