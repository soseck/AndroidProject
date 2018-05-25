package fr.unice.polytech.polyincidents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by user on 24/05/2018.
 */

public class ShareActivity extends Activity implements Serializable {
    private Declaration declaration;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        String extrasTitre = extras.getString("title");
        String extrasContent = extras.getString("description");
        String extrasLocation = extras.getString("location");
        String extrasUrgence = extras.getString("urgence");
        String extrasImportance = extras.getString("importance");


        super.onCreate(extras);
        setContentView(R.layout.share_activity);
        TextView textView = (TextView)findViewById(R.id.titre);
        textView.setText(extrasTitre);

        TextView textView2 = (TextView)findViewById(R.id.Info);
        textView2.setText(extrasContent);

        TextView textView3 = (TextView)findViewById(R.id.lieu);
        textView3.setText(extrasLocation);

        TextView textView4 = (TextView)findViewById(R.id.urg);
        textView4.setText(extrasUrgence);

        TextView textView5 = (TextView)findViewById(R.id.imp);
        textView5.setText(extrasImportance);
    }

    public void setDeclaration( Declaration newDeclaration) {
        this.declaration.setTitle(newDeclaration.getTitle());
    }
    public Declaration getDeclaration() {
        return this.declaration;
    }

    public void tweet(View view){

        FragmentDeclaration.newInstance().tweet(view);
    }

    public void sendSMS (View view) {
        FragmentDeclaration.newInstance().sendSMS();

    }

    public void sendMail (View view)
    {
        FragmentDeclaration.newInstance().sendMail();
    }
}
