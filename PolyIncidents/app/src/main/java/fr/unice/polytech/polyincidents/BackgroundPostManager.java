package fr.unice.polytech.polyincidents;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
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
    public static final String FAILURE_POST_MESSAGE = "fail";
    public static final String SCRIPT_1_FILE = "/simplePost.php";
    public static final String SCRIPT_2_FILE = "/postImage.php";
    public static final String REQUEST_METHOD = "POST";

    public BackgroundPostManager(Context context) {
        this.context = context;
        this.declaration = null;
        this.post_data = new HashMap<>();
        this.communicator = new DBCommunicator(SCRIPT_1_FILE, REQUEST_METHOD);

    }

    @Override
    protected String doInBackground(Declaration... params) {
        declaration = params[0];
        post_data = declaration.getMapPostData();
        Log.i("image", "Title = "+post_data.get("title"));
        Log.i("image", "Content = "+post_data.get("content"));
        Log.i("image", "Location = "+post_data.get("location"));
        Log.i("image", "urgence = "+post_data.get("urgence"));
        Log.i("image", "importance = "+ post_data.get("importance"));
        Log.i("image", "tag = "+post_data.get("tag"));

        SharedPreferences preferences = context.getSharedPreferences(LoginActivity.USER_PREF_NAME, Context.MODE_PRIVATE);
        post_data.put(LoginActivity.USERNAME_PREF_KEY, preferences.getString(LoginActivity.USERNAME_PREF_KEY, ""));
        String result = communicator.doInBackground(post_data);

        if(!result.equals(FAILURE_POST_MESSAGE) && declaration.getImage()!=null){
            //postImage
            communicator = new DBCommunicator(SCRIPT_2_FILE, REQUEST_METHOD);
            Bitmap bitmap = declaration.getImage();
            Log.i("image", "on DoInBack");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            bitmap.recycle();

            byte[] array = stream.toByteArray();
            String encoded_string = Base64.encodeToString(array, 0);
            String image_name = "image.jpg";
            Map<String, String> postDataMap = new HashMap<>();
            postDataMap.put("incident_ID", result);
            postDataMap.put("encoded_string",encoded_string);
            postDataMap.put("image_name",image_name);

            result = communicator.doInBackground(postDataMap);

            Log.i("image", "request post image  sent - result : "+result);
        }else if (!result.equals(FAILURE_POST_MESSAGE) && declaration.getImage()==null){
            return SUCCESS_POST_MESSAGE;
        }


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
