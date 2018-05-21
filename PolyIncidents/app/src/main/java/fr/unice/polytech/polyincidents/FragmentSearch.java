package fr.unice.polytech.polyincidents;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.annotation.Nullable;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import static android.support.v4.media.session.MediaButtonReceiver.handleIntent;

/**
 * Created by Donélia on 07/05/2018.
 */

public class FragmentSearch extends Fragment  {

    MaterialSearchView searchView;
    public static final String SCRIPT_FILE = "/getSearch.php";
    public static final String SCRIPT_FILE_TEST = "/getAll.php";
    public static final Integer VIEW_ID = R.id.SearchListView;

    public static FragmentSearch newInstance() {
        FragmentSearch fragment = new FragmentSearch();
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String searchtext ="title";
       // new BackgroundSearch(this.getContext(), SCRIPT_FILE, VIEW_ID, searchtext).execute(NewsGroup.ALL);


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
        SearchView simpleSearchView = (SearchView) rootView.findViewById(R.id.action_search); // inititate a search view

        // perform set on query text listener event
        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
            // do something on text submit
                doMySearch(query);
              //  EventBus.getDefault().post(new QueryEvent(query));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
            // do something when text changes
                doMySearch(newText);
                return false;
            }
        });

        return rootView;
    }


    public void doMySearch(String query){
        new BackgroundSearch(this.getContext(), SCRIPT_FILE, VIEW_ID, query).execute(NewsGroup.ALL);
    }


    /*
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
       super.onCreateOptionsMenu(menu, inflater);
       inflater.inflate(R.menu.menu_search,menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
       // final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        List<Declaration> liste = new ArrayList<>();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               // filterApps(app -> app.name.toLowerCase().contains(query.toLowerCase()) || app.packageName.toLowerCase().contains(query.toLowerCase()));
                return true;
                //votre code ici
            }

            @Override
            public boolean onQueryTextChange(String s) {
              //  filterApps(app -> app.name.toLowerCase().contains(newText.toLowerCase()) || app.packageName.toLowerCase().contains(newText.toLowerCase()));
                return true;
            }
        });

    }*/





}
