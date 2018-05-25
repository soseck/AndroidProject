package fr.unice.polytech.polyincidents;

/**
 * Created by Donélia on 24/05/2018.
 */

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SMSActivity extends Activity {

    private Button buttonSMS;
    /*

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration);

        buttonSMS = (Button) findViewById(R.id.ButtonSMS);

        buttonSMS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMS();
            }
        });
    }

    protected void sendSMS(){
        Log.i("Send SMS", "");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", new String("01234"));
        smsIntent.putExtra("sms_body", "Test ");

        try {
            startActivity(smsIntent);
            finish();
            Log.i("Finished sending SMS...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(SMSActivity.this,
                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }*/
}

