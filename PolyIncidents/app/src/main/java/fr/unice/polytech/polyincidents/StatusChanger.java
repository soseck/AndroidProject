package fr.unice.polytech.polyincidents;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by user on 25/05/2018.
 */

public class StatusChanger extends AsyncTask<Integer, Void, String> {

    private  Context context;
    StatusChanger(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(Integer... params) {
        Integer incident_ID = params[0], changeStatus = params[1];
        Log.i("In Status Changer : ", changeStatus.toString());
        HashMap<String , String > postDataMap = new HashMap<>();
        postDataMap.put("incident_ID", incident_ID.toString());
        postDataMap.put("status_ID", changeStatus.toString());

        String result = new DBCommunicator("/changeStatus.php", "POST").sendRequest(postDataMap);
        return  result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s.equals("success")){
            Toast.makeText(context, "Le changement a été bien pris en compte.", Toast.LENGTH_SHORT );
        } else {
            Toast.makeText(context, "Veuillez réessayer plutard.", Toast.LENGTH_SHORT );
        }
    }
}
