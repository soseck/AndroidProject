package fr.unice.polytech.polyincidents;

/**
 * Created by Donelia on 07/05/2018.
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class FragmentDeclaration extends Fragment {

    static final String TAG_KEY = "default_tag";
    static final String URGENCE_KEY = "urgence";
    static final String IMPORTANCE_KEY = "importance";

    EditText title, content, location;
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
        imgTaken = (ImageView)rootView.findViewById(R.id.imageView);

        Toast.makeText(getContext(), "About to fill spinners", Toast.LENGTH_LONG);
        // TODO afficher la liste pour les types dincident, urgence et importance
        fillSpinners();


        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("photo", " enter activity result method");
        if(requestCode == CAM_REQUEST){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgTaken.setImageBitmap(bitmap);
        }else {
            Toast.makeText(getContext(), "request code error ", Toast.LENGTH_LONG);
        }
    }


    public void sendDeclaration(View view) {
        Declaration declaration = new Declaration(title.getText().toString(),
                                                    content.getText().toString(),
                                                    location.getText().toString());
        BackgroundPostManager postManager = new BackgroundPostManager(view.getContext());
        postManager.execute(declaration);
    }

    public void takePicture(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAM_REQUEST);
    }

    public void showMap(View view){
        // TODO show Map
    }

    private void fillSpinners() {
        BackgroundFieldsValueManager fieldsValueManager = new BackgroundFieldsValueManager(getContext(), R.id.tag);
        fieldsValueManager.execute(TAG_KEY);

        fieldsValueManager = new BackgroundFieldsValueManager(getContext(), R.id.urgence);
        fieldsValueManager.execute(URGENCE_KEY);

        fieldsValueManager = new BackgroundFieldsValueManager(getContext(), R.id.importance);
        fieldsValueManager.execute(IMPORTANCE_KEY);
    }
}
