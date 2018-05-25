package fr.unice.polytech.polyincidents;

import android.app.Activity;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class MenuActivity extends AppCompatActivity {
    Fragment selectedFragment;
    BottomNavigationView bottomNavigationView;
    Point p;

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

        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();

        //Used to select an item programmatically
        bottomNavigationView.setSelectedItemId(0);
    }


    public BottomNavigationView getBottomNavigationView() {
        return bottomNavigationView;
    }


    //<editor-fold desc="FragmentDeclarationMethods">
    public void sendDeclaration(View view) {
        ((FragmentDeclaration) selectedFragment).sendDeclaration(view);
    }

    public void takePicture(View view) {
        ((FragmentDeclaration) selectedFragment).takePicture(view);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void showMap(View view) {
        ((FragmentDeclaration) selectedFragment).showMap(view);
    }

    public void tweet(View view) {
        ((FragmentDeclaration) selectedFragment).tweet(view);
    }

    public void sendSMS(View view) {
        ((FragmentDeclaration) selectedFragment).sendSMS();

    }

    public void sendMail(View view) {
        ((FragmentDeclaration) selectedFragment).sendMail();
    }

    public void call(View view) {
        ((FragmentDeclaration) selectedFragment).call();
    }

    public void share(View view) {
        ((FragmentDeclaration) selectedFragment).sendDeclaration(view);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    //</editor-fold>

    public void createPop(final View view) {
        final Dialog dialog = new Dialog(MenuActivity.this);
        Button btn_show = (Button) findViewById(R.id.share);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                final Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.popup_share);
                dialog.setTitle("Hello");

                dialog.show();

            }
        });

        /*
        Button bouton = (Button)  dialog.findViewById(R.id.close);
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            dialog.dismiss();            }
        });*/
    }

    // Get the x and y position after the button is draw on screen
// (It's important to note that we can't get the position in the onCreate(),
// because at that stage most probably the view isn't drawn yet, so it will return (0, 0))




    private void showPopup() {
        int popupWidth = 200;
        int popupHeight = 150;

        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout) findViewById(R.id.popup);
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_share, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(this);
        popup.setContentView(layout);
        /*
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);*/

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 30;
        int OFFSET_Y = 30;

        // Clear the default translucent background
      //  popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
       // popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);

        // Getting a reference to Close button, and close the popup when clicked.
        /*
        Button close = (Button) layout.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
    }*/


    }

}