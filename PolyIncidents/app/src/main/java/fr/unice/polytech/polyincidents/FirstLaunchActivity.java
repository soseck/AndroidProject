package fr.unice.polytech.polyincidents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by Sokhna on 30/04/2018.
 */

public class FirstLaunchActivity extends Activity {
    public static final String FIRST_APP_LAUNCH = "fr.unice.polytech.polyincidents.firstLaunch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isFirstAppLaunch()) {
            setFirstAppLaunch(false);
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }

        finish();
    }

    //TODO Always set at default value (true). Look for another way to store the boolean.
    private boolean isFirstAppLaunch() {
        SharedPreferences preferences = this.getPreferences(Context.MODE_PRIVATE);
        return preferences.getBoolean(FIRST_APP_LAUNCH, true);
    }

    private void setFirstAppLaunch(boolean value) {
        SharedPreferences preferences = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(FIRST_APP_LAUNCH, value);
        editor.apply();
    }
}
