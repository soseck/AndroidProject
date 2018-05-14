package fr.unice.polytech.polyincidents;

/**
 * Created by Donelia on 07/05/2018.
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

public class FragmentDeclaration extends Fragment {

    ImageButton takePicButton;
    Button sendButton;
    EditText title, content, location;

    String [] TYPELIST={"Matériel","Environnement","Indéfini"};

    Button buttonpic;
    ImageView imgTaken;

    private static final int CAM_REQUEST=1313;


    public static FragmentDeclaration newInstance() {
        FragmentDeclaration fragment = new FragmentDeclaration();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_declaration, container, false);
        title = (EditText) rootView.findViewById(R.id.title);
        content = (EditText) rootView.findViewById(R.id.content);
        location = (EditText) rootView.findViewById(R.id.location);

        //afficher la list pour les types dincident
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_dropdown_item_1line,TYPELIST);
        //MaterialBetterSpinner betterSpinner=(MaterialBetterSpinner)rootView.findViewById(R.id.type_incident);
        //betterSpinner.setAdapter(arrayAdapter);
        //une fois click sur le bouton, afficher la cam, prendre une photo et la sauvguarder
        buttonpic = (Button)rootView.findViewById(R.id.button_picture);
        imgTaken = (ImageView)rootView.findViewById(R.id.take_picture);
        buttonpic.setOnClickListener(new btnTakePhotoClicker());
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAM_REQUEST){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgTaken.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    public void sendDeclaration(View view) {
        Declaration declaration = new Declaration(title.getText().toString(),
                                                    content.getText().toString(),
                                                    location.getText().toString());
        BackgroundPostManager postManager = new BackgroundPostManager(view.getContext());
        postManager.execute(declaration);
    }

    class btnTakePhotoClicker implements Button.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,CAM_REQUEST);
        }
    }
}
