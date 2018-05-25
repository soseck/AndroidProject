package fr.unice.polytech.polyincidents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.io.Serializable;

/**
 * Created by user on 24/05/2018.
 */

public class ShareActivity extends Activity implements Serializable {
    private Declaration declaration;

    String extrasTitre;
    String extrasContent;
    String extrasLocation;
    String extrasUrgence;
    String extrasImportance;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        extrasTitre = extras.getString("title");
        extrasContent = extras.getString("description");
         extrasLocation = extras.getString("location");
         extrasUrgence = extras.getString("urgence");
         extrasImportance = extras.getString("importance");


        super.onCreate(extras);
        setContentView(R.layout.share_activity);
        TextView textView = (TextView)findViewById(R.id.titre);
        textView.setText(extrasTitre);

        TextView textView2 = (TextView)findViewById(R.id.Info);
        textView2.setText(extrasContent);

        TextView textView3 = (TextView)findViewById(R.id.lieu);
        textView3.setText(extrasLocation);

       // TextView textView4 = (TextView)findViewById(R.id.urg);
       // textView4.setText(extrasUrgence);

        TextView textView5 = (TextView)findViewById(R.id.imp);
        textView5.setText(extrasImportance);
    }

    public void setDeclaration( Declaration newDeclaration) {
        this.declaration.setTitle(newDeclaration.getTitle());
    }
    public Declaration getDeclaration() {
        return this.declaration;
    }

    

    public void sendMail (View view)
    {
        FragmentDeclaration.newInstance().sendMail();
    }

    public void tweet(View view){
        TweetComposer.Builder builder = new TweetComposer.Builder(this)
                .text("@PIncidents " + extrasContent);//any sharing text here
        //     .image(fileUri);//sharing image uri
        builder.show();
    }


    protected void sendSMS() {
        Log.i("Send SMS", "");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        String text = "Un nouvel incident nommé: " + extrasTitre + " vient d'être posté." + " Voici sa description: "+ extrasContent;

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", new String(""));
        smsIntent.putExtra("sms_body", text);

        try {
            startActivity(smsIntent);
            //finish();
            Log.i("Finished sending SMS...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this,
                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }

    }
}
