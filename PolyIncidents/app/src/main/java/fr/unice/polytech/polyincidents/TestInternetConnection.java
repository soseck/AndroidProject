package fr.unice.polytech.polyincidents;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.widget.Toast;

import fr.unice.polytech.polyincidents.FirstLaunchActivity;
import fr.unice.polytech.polyincidents.MainActivity;

/**
 * Created by Sokhna on 07/05/2018.
 */

public class TestInternetConnection {

    public static boolean isConnectedInternet(Activity activity)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null)
        {
            State networkState = networkInfo.getState();
            if (networkState.compareTo(State.CONNECTED) == 0)
            {
                return true;
            }
            else return false;
        }
        else {
            Toast.makeText(activity.getApplicationContext(), "Vous n'êtes pas connecté à Internet.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
