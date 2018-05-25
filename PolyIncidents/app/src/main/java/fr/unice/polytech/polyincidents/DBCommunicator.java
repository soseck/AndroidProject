package fr.unice.polytech.polyincidents;

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
import java.util.Map;

/**
 * Created by Sokhna on 12/05/2018.
 */

public class DBCommunicator {

    //public static final String SERVER_URL = "http://polyincidents.gearhostpreview.com";
    public static final String SERVER_URL = "http://192.168.43.92";

    private Map<String, String> postDataMap;
    private String scriptfile, requestMethod;

    public DBCommunicator(String scriptFile, String requestMethod) {
        this.scriptfile = scriptFile;
        this.requestMethod = requestMethod;
        this.postDataMap = null;
    }
    
    public String sendRequest(Map<String, String>... postDataMaps){
            try {
                this.postDataMap = (postDataMaps == null ) ? null : postDataMaps[0];
                URL url = new URL(SERVER_URL + scriptfile);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod(requestMethod);
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                OutputStream outputStream = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("", "UTF-8");
                if(postDataMap != null){
                    for(Map.Entry<String, String > entry : postDataMap.entrySet()) {
                        if(entry.getValue()==null) continue;
                        if(!post_data.equals("")){
                            post_data += "&" ;
                        }
                        post_data += URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                                + URLEncoder.encode(entry.getValue(), "UTF-8");
                    }
                }
                writer.write(post_data);
                writer.flush();
                writer.close();

                //Response to the post
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while( (line = reader.readLine()) != null){
                    result += line;
                }
                reader.close();
                inputStream.close();
                urlConnection.disconnect();
                System.out.println("DB comm "+ scriptfile + result);
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
            return "fail";
        }
}
