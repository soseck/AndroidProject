package fr.unice.polytech.polyincidents;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MenuActivity extends AppCompatActivity {
    Fragment selectedFragment;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_menu);
        bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);
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
                                selectedFragment = FragmentPhoto.newInstance();
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
        transaction.commit();

        //Used to select an item programmatically
        //bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }

    public void sendDeclaration(View view) {
        ((FragmentDeclaration)selectedFragment).sendDeclaration(view);
    }

    public void takePicture(View view){
        ((FragmentDeclaration)selectedFragment).takePicture(view);
    }

    public BottomNavigationView getBottomNavigationView() {
        return bottomNavigationView;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       return new FragmentSearch().onCreateOptionsMenu(menu);
    }
}