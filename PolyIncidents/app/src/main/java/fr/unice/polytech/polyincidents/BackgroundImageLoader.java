package fr.unice.polytech.polyincidents;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 21/05/2018.
 */

public class BackgroundImageLoader extends AsyncTask<Void, Void, Void> {

    Integer incident_ID;
    DBCommunicator communicator;
    Map<String, String> postDataMap;
    Declaration declaration;

    public static final String scriptfile = "/getImageURL.php";
    public static final String REQUEST_METHOD = "POST";

    public BackgroundImageLoader(Integer incident_ID, Declaration declaration) {
        this.incident_ID = incident_ID;
        this.communicator = new DBCommunicator(scriptfile, REQUEST_METHOD );
        this.postDataMap = new HashMap<>();
        this.declaration = declaration;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Bitmap bitmap = null;

        postDataMap.put("incident_ID", String.valueOf(incident_ID));


        String result = communicator.sendRequest(postDataMap);
        if(result.equals("No Image")){
            return null;
        }
        String filepath ="";
        try {
            filepath = ((JSONObject)(new JSONArray(result.toString()).get(0))).getString("filepath");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String image_url = DBCommunicator.SERVER_URL + "/" + filepath;
        InputStream stream = null;
        try {
            stream = new java.net.URL(image_url).openStream();
            bitmap = BitmapFactory.decodeStream(stream);
            declaration.setImage(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }
}
