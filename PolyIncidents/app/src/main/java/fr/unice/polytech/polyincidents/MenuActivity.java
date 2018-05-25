package fr.unice.polytech.polyincidents;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class MenuActivity extends AppCompatActivity {
    Fragment selectedFragment;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_menu);
        bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);
        //Test Login Twitter
        Toast.makeText(this, getIntent().getStringExtra("username"), Toast.LENGTH_SHORT).show();
        selectedFragment = FragmentHome.newInstance();
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = FragmentHome.newInstance();
                                break;
                            case R.id.action_item2:
                                selectedFragment = FragmentSearch.newInstance();
                                break;
                            case R.id.action_item3:
                                selectedFragment = FragmentTracking.newInstance();
                                break;
                            case R.id.action_item4:
                                selectedFragment = FragmentDeclaration.newInstance();
                                break;
                            case R.id.action_item5:
                                selectedFragment = FragmentProfile.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });



        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //  transaction.replace(R.id.frame_layout, ItemOneFragment.newInstance());

        transaction.replace(R.id.frame_layout,selectedFragment);
        transaction.commit();

        //Used to select an item programmatically
        bottomNavigationView.setSelectedItemId(0);
    }


    public BottomNavigationView getBottomNavigationView() {
        return bottomNavigationView;
    }


    //<editor-fold desc="FragmentDeclarationMethods">
    public void sendDeclaration(View view) {
        ((FragmentDeclaration)selectedFragment).sendDeclaration(view);
    }

    public void takePicture(View view){
        ((FragmentDeclaration)selectedFragment).takePicture(view);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void showMap(View view){
        ((FragmentDeclaration)selectedFragment).showMap(view);
    }

    public void tweet(View view){
        ((FragmentDeclaration)selectedFragment).tweet(view);
    }

    public void sendSMS (View view) {
        ((FragmentDeclaration)selectedFragment).sendSMS();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    //</editor-fold>



}