package fr.unice.polytech.polyincidents;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by user on 30/04/2018.
 */

public class BackgroundLoginManager extends AsyncTask<String, Void, String> {

    private Context context;
    private User connectedUser;
    private AlertDialog alertDialog;

    public static final String SUCCESS_MESSAGE = "success";
    public static final String FAILURE_MESSAGE = "fail";


    public BackgroundLoginManager(Context context) {
        this.context = context;
        this.connectedUser = null;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://polyincidents.gearhostpreview.com/login.php";
        if(type.equals("login")){
            try {
                connectedUser = new User(params[1], params[2]);

                URL url = new URL(login_url);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                OutputStream outputStream = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username","UTF-8") + "=" + URLEncoder.encode(connectedUser.getUserID(), "UTF-8")
                                    + "&" + URLEncoder.encode("password","UTF-8") + "=" + URLEncoder.encode(connectedUser.getPassword(), "UTF-8");
                writer.write(post_data);
                writer.flush();
                writer.close();

                //Response to the post
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = " ";
                String line = "";
                while( (line = reader.readLine()) != null){
                    result += line;
                }
                reader.close();
                inputStream.close();
                urlConnection.disconnect();
                return  result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Etat connexion");

    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}

