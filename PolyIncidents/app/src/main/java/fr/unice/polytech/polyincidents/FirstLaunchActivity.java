package fr.unice.polytech.polyincidents;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by Sokhna on 30/04/2018.
 */

public class FirstLaunchActivity extends Activity {
    public static final String FIRST_APP_LAUNCH = "firstLaunchCase";
    public static final String LOGGED_IN_KEY = "alreadyLoggedIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isFirstAppLaunch()) {
            setFirstAppLaunch(false);
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            startActivity(new Intent(this, MenuActivity.class));
        }

        finish();
    }

    private boolean isFirstAppLaunch() {
        SharedPreferences preferences = getSharedPreferences(FIRST_APP_LAUNCH,Context.MODE_PRIVATE);
        SharedPreferences loginPreferences = getSharedPreferences(LoginActivity.USER_PREF_NAME, MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(LOGGED_IN_KEY, loginPreferences.contains(LoginActivity.USERNAME_PREF_KEY));
        editor.apply();

        return !preferences.getBoolean(LOGGED_IN_KEY, false);
    }

    //Set to false when we deconnect.
    public void setFirstAppLaunch(boolean value) {
        SharedPreferences preferences = getSharedPreferences(FIRST_APP_LAUNCH, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(LOGGED_IN_KEY, value);
        editor.apply();
    }
}
