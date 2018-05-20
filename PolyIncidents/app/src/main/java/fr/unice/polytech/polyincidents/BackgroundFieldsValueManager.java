package fr.unice.polytech.polyincidents;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 20/05/2018.
 */

public class BackgroundFieldsValueManager extends AsyncTask<String, Void, List<String>> {

    private Context context;
    private DBCommunicator communicator;
    private List<String> fieldListValue;
    private Integer spinnerID;
    private String field;


    public static final String FAILURE_POST_MESSAGE = "fail";
    public static final String SCRIPT_FILE = "/getFieldList.php";
    public static final String REQUEST_METHOD = "POST";

    public BackgroundFieldsValueManager(Context context, Integer spinnerID) {
        this.context = context;
        this.communicator = new DBCommunicator(SCRIPT_FILE, REQUEST_METHOD);
        this.fieldListValue = new ArrayList<>();
        this.spinnerID = spinnerID;
        this.field = null;
    }

    @Override
    protected List<String > doInBackground(String... params) {
        field = params[0];
        Map<String, String> postDataMap = new HashMap<String, String>();
        postDataMap.put("field", field);
        String result = communicator.sendRequest(postDataMap);
        if(!result.equals(FAILURE_POST_MESSAGE)){
            result = new String(result.getBytes(Charset.forName("ISO-8859-1")),Charset.forName("UTF-8"));

            try {
                JSONArray jsonArray = new JSONArray(result.toString());
                for(int i = 0; i<jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    fieldListValue.add(jsonObject.getString("value"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println(result);

        return fieldListValue;

    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(List<String> fieldListValue) {
        if (TestInternetConnection.isConnectedInternet((Activity) this.context)) {
            if (!fieldListValue.isEmpty()) {
                Toast.makeText(this.context.getApplicationContext(), this.field + " loading succeeded", Toast.LENGTH_SHORT).show();
                ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(context,android.R.layout.simple_dropdown_item_1line,fieldListValue);
                MaterialBetterSpinner betterSpinner=((MenuActivity)context).findViewById(this.spinnerID);
                betterSpinner.setAdapter(arrayAdapter);
            } else {

                Toast.makeText(this.context.getApplicationContext(), this.field + " loading failed", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
