package fr.unice.polytech.polyincidents;

/**
 * Created by Donelia on 07/05/2018.
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.File;

import static android.app.Activity.RESULT_OK;

public class FragmentDeclaration extends Fragment {

    static final String TAG_KEY = "default_tag";
    static final String URGENCE_KEY = "urgence";
    static final String IMPORTANCE_KEY = "importance";

    private String tagValue, urgenceValue, importanceValue;

    private boolean imageSetted = false, imagePosted = false;

    private String image_name = "image", encoded_string;
    private File file;
    private Uri fileUri;
    private Bitmap bitmap;

    EditText title, content, location, date, hour;
    MaterialBetterSpinner importance, urgence, tag;
    Button captureButton;
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
        //afficher la liste pour les types dincident, urgence et importance
        fillSpinners();

        captureButton = (Button) rootView.findViewById(R.id.captureButton);

        //Mandatory Fields
        title = (EditText) rootView.findViewById(R.id.title);
        content = (EditText) rootView.findViewById(R.id.content);
        location = (EditText) rootView.findViewById(R.id.location);

        //Date & Hour
        date = (EditText) rootView.findViewById(R.id.date);
        hour = (EditText) rootView.findViewById(R.id.hour);

        //Spinners
        importance = (MaterialBetterSpinner) rootView.findViewById(R.id.importance);
        importance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                importanceValue = (String) importance.getText().toString();
                Log.i("image", " Set importanceValue = "+ importanceValue);

            }
        });
        //TODO set one by default - nullPointer

        urgence = (MaterialBetterSpinner) rootView.findViewById(R.id.urgence);
        urgence.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                urgenceValue = (String) urgence.getText().toString();
                Log.i("image", " Set urgenceValue = "+ urgenceValue);

            }
        });
        //urgence.setText(urgence.getAdapter().getItem(2).toString());

        tag = (MaterialBetterSpinner) rootView.findViewById(R.id.tag);
        tag.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tagValue = (String) tag.getText().toString();
                Log.i("image", " Set tagValue = "+ tagValue);

            }
        });
        //tag.setText(tag.getAdapter().getItem(7).toString());

        imgTaken = (ImageView)rootView.findViewById(R.id.imageView);

        return rootView;
    }

    public void sendDeclaration(View view) {
        Log.i("image", "In SendDeclaration");

        //Mandatory fields must be set.
        if (title.getText().toString().matches("")
                || content.getText().toString().matches("")
                || location.getText().toString().matches("")) {
            Toast.makeText(getContext(), "Remplir les champs obligatoires", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.i("image", "Mandatory fields not empty.");

        Declaration declaration = new Declaration(title.getText().toString(),
                                                    content.getText().toString(),
                                                    location.getText().toString());
        Log.i("image", "mandatory fields set in declaration");

        //TimeStamp by default
        declaration.setDateValue( (date.getText().toString().matches("")) ? null : date.getText().toString()); //TODO timestamp
        Log.i("image", "DateValue set");

        //Values from spinners
        if(tagValue == null) tag.setText(tag.getAdapter().getItem(7).toString());
        if(urgenceValue == null) urgence.setText(urgence.getAdapter().getItem(1).toString());
        if(importanceValue == null) importance.setText(importance.getAdapter().getItem(1).toString());
        declaration.setTag(tagValue);
        declaration.setUrgence(urgenceValue);
        declaration.setImportance(importanceValue);
        Log.i("image", "Values from spinners set.");


        if(imageSetted){
            declaration.setImage(bitmap);
            Log.i("image", "imageSet");

        }

        //sendDeclaration
        Log.i("image", "About to execute PostManager");
        BackgroundPostManager postManager = new BackgroundPostManager(view.getContext());
        postManager.execute(declaration);
    }

    public void takePicture(View view){
        Log.i("image", "Taking picture");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) +
                File.separator + image_name );
        fileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent,CAM_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("image", " enter activity result method");
        if(requestCode == CAM_REQUEST && resultCode == RESULT_OK){
            bitmap = BitmapFactory.decodeFile(fileUri.getPath());
            imgTaken.setImageBitmap(bitmap);
            imageSetted = true;
        }else {
            Toast.makeText(getContext(), "request code error ", Toast.LENGTH_LONG);
        }
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
/*
    private class Image_Encoder extends AsyncTask<Void, Void,String>{
        @Override
        protected String doInBackground(Void... voids) {

            bitmap = BitmapFactory.decodeFile(fileUri.getPath());
            Log.i("image", "on DoInBack");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            bitmap.recycle();

            byte[] array = stream.toByteArray();
            encoded_string = Base64.encodeToString(array, 0);
            image_name = "imageTaken.jpg";

            DBCommunicator communicator = new DBCommunicator("/postImage.php", "POST");
            Map<String, String> postDataMap = new HashMap<>();
            postDataMap.put("encoded_string",encoded_string);
            postDataMap.put("image_name",image_name);

            String result = communicator.sendRequest(postDataMap);

            Log.i("image", "request sent - result : "+result);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            if(result.equals("success")){
                Log.i("image", "Post execute - success");
                imagePosted = true;
            }else{
                Toast.makeText(getContext(), "image not stored in fileSystem", Toast.LENGTH_SHORT).show();
            }
        }
    }
    */
}
