package fr.unice.polytech.polyincidents;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static fr.unice.polytech.polyincidents.NewsGroup.ALL;

/**
 * Created by user on 13/05/2018.
 */

public class BackgroundNewsFeedManager extends AsyncTask<NewsGroup, Void, List<Declaration>> {

    private Context context;
    private DBCommunicator communicator;
    private NewsGroup newsGroup;
    private List<Declaration> declarationList;


    public static final String FAILURE_POST_MESSAGE = "failed";
    public static final String SCRIPT_FILE = "/get.php";
    public static final String REQUEST_METHOD = "POST";

    public BackgroundNewsFeedManager(Context context) {
        this.context = context;
        this.communicator = new DBCommunicator(SCRIPT_FILE, REQUEST_METHOD);
        this.newsGroup = ALL;
        this.declarationList = new ArrayList<>();
    }

    @Override
    protected List<Declaration> doInBackground(NewsGroup... params) {
        newsGroup = params[0];
        String result = "";
        switch (newsGroup){
            case ALL:
                 result = communicator.doInBackground( null);
                break;
            default: break;
        }
        System.out.println(result);
        if(!result.equals(FAILURE_POST_MESSAGE)){
            try {
                setDeclarationList(new JSONArray(result.toString()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return declarationList;

    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(List<Declaration> declarationList) {
        if(TestInternetConnection.isConnectedInternet((Activity) this.context)){
            if(!declarationList.isEmpty()){
                Toast.makeText(this.context.getApplicationContext(), "News Feed loading succeded ! ", Toast.LENGTH_SHORT).show();
                ListAdapter newsFeedAdapter = new NewsFeedAdapter(context, declarationList);
                GridView grid = ((MenuActivity)context).findViewById(R.id.gridListView);
                grid.setAdapter(newsFeedAdapter);
            }else{
                Toast.makeText(this.context.getApplicationContext(), "News Feed loading failed.. ", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    private List<Declaration> setDeclarationList(JSONArray jsonArray){
        try {
            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Declaration declaration = new Declaration();
                    User author = new User();
                    author.setSurname(jsonObject.getString("surname"));
                    author.setName(jsonObject.getString("name"));
                declaration.setAuthor(author);
                declaration.setTitle(jsonObject.getString("title"));
                declaration.setContent(jsonObject.getString("content"));
                declaration.setLocation(jsonObject.getString("location"));
                declaration.setDateValue(jsonObject.getString("date"));
                declaration.setImportance(jsonObject.getString("importance"));
                declaration.setUrgence(jsonObject.getString("urgence"));
                declaration.setTag(jsonObject.getString("tag"));

                declarationList.add(declaration);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return declarationList;
    }
}
