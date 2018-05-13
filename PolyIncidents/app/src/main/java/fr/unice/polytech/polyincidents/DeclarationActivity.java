package fr.unice.polytech.polyincidents;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.service.voice.VoiceInteractionSession;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class DeclarationActivity extends AppCompatActivity {

    String [] TYPELIST={"Matériel","Environnement","Indéfini"};

    Button buttonpic;
    ImageView imgTaken;

    private static final int CAM_REQUEST=1313;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration);
        //afficher la list pour les types dincident
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,TYPELIST);
        MaterialBetterSpinner betterSpinner=(MaterialBetterSpinner)findViewById(R.id.type_incident);
        betterSpinner.setAdapter(arrayAdapter);
        //une fois click sur le bouton, afficher la cam, prendre une photo et la sauvguarder
        buttonpic = (Button)findViewById(R.id.button_picture);
        imgTaken = (ImageView)findViewById(R.id.take_picture);
        buttonpic.setOnClickListener(new btnTakePhotoClicker());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAM_REQUEST){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgTaken.setImageBitmap(bitmap);
        }
    }

    class btnTakePhotoClicker implements Button.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,CAM_REQUEST);
        }
    }






}
