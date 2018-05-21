package fr.unice.polytech.polyincidents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Sokhna on 30/04/2018.
 */

public class BackgroundLoginManager extends AsyncTask<User, Void, String> {

    public static final String SCRIPT_FILE = "/login.php";
    public static final String REQUEST_METHOD = "POST";

    private Context context;
    private User connectedUser;
    Map<String, String> post_data;
    DBCommunicator communicator;

    public static final String SUCCESS_LOGIN_MESSAGE = "success";
    public static final String FAILURE_LOGIN_MESSAGE = "fail";


    public BackgroundLoginManager(Context context) {
        this.context = context;
        this.connectedUser = null;
        this.post_data = new HashMap<>();
        this.communicator = new DBCommunicator(SCRIPT_FILE, REQUEST_METHOD);
    }

    @Override
    protected String doInBackground(User... params) {

        connectedUser = params[0];
        post_data.put("username", connectedUser.getUserID());
        post_data.put("password", connectedUser.getPassword());
        String result = communicator.sendRequest(post_data);
        if(result.equals(SUCCESS_LOGIN_MESSAGE)){
            DBCommunicator communicator = new DBCommunicator("/connectedUser.php", "POST");
            String connectedUserAttributes = communicator.sendRequest( post_data);
            connectedUser.setUserAttributes(connectedUserAttributes);
            ((LoginActivity)this.context).setConnectedUser(connectedUser);
        }
        return result;

    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(String result) {
        if(TestInternetConnection.isConnectedInternet((Activity) this.context)) {
            if (result.equals(SUCCESS_LOGIN_MESSAGE)) {
                SharedPreferences preferences = this.context.getSharedPreferences(LoginActivity.USER_PREF_NAME,MODE_PRIVATE);
                Toast.makeText(this.context.getApplicationContext(), "Bonjour " + preferences.getString(LoginActivity.SURNAME_PREF_KEY, ""), Toast.LENGTH_SHORT).show();

                this.context.startActivity(new Intent(this.context.getApplicationContext(), MenuActivity.class));
            }else if(result.equals(FAILURE_LOGIN_MESSAGE)){
                Toast.makeText(this.context.getApplicationContext(), "Identifiant et/ou mot de passe incorrect(s)", Toast.LENGTH_SHORT).show();
            }else{
                    Toast.makeText(this.context.getApplicationContext(), "Serveur non disponible - Réessayez plus tard.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}

