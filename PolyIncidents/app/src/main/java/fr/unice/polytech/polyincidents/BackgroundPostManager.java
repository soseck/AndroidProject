package fr.unice.polytech.polyincidents;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sokhna on 12/05/2018.
 */

public class BackgroundPostManager extends AsyncTask<Declaration, Void, String> {

    private Context context;
    private Declaration declaration;
    Map<String, String> post_data;
    DBCommunicator communicator;

    public static final String SUCCESS_POST_MESSAGE = "success";
    public static final String SCRIPT_FILE = "/simplePost.php";
    public static final String REQUEST_METHOD = "POST";

    public BackgroundPostManager(Context context) {
        this.context = context;
        this.declaration = null;
        this.post_data = new HashMap<>();
        this.communicator = new DBCommunicator(SCRIPT_FILE, REQUEST_METHOD);

    }

    @Override
    protected String doInBackground(Declaration... params) {
        declaration = params[0];
        post_data = declaration.getMapPostData();
        SharedPreferences preferences = context.getSharedPreferences(LoginActivity.USER_PREF_NAME, Context.MODE_PRIVATE);
        post_data.put(LoginActivity.USERNAME_PREF_KEY, preferences.getString(LoginActivity.USERNAME_PREF_KEY, ""));
        String result = communicator.sendRequest(post_data);
        return result;

    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(String result) {
        if(TestInternetConnection.isConnectedInternet((Activity) this.context)){
            if(result.equals(SUCCESS_POST_MESSAGE)){
                Toast.makeText(this.context.getApplicationContext(), " Votre post a été enregistré ! ", Toast.LENGTH_SHORT).show();
                ((MenuActivity)context).getBottomNavigationView().setSelectedItemId(R.id.action_item1);
            }else{
                Toast.makeText(this.context.getApplicationContext(), "Operation failed ", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
