package fr.unice.polytech.polyincidents;

/**
 * Created by Donelia on 07/05/2018.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FragmentDeclaration extends Fragment {

    ImageButton takePicButton;
    Button sendButton;
    EditText title, content, location;


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
        View rootView = inflater.inflate(R.layout.fragment_declaration, container, false);
        takePicButton = (ImageButton) rootView.findViewById(R.id.takePictureButton);
        sendButton = (Button)rootView.findViewById(R.id.sendButton);
        title = (EditText) rootView.findViewById(R.id.title);
        content = (EditText) rootView.findViewById(R.id.details);
        location = (EditText) rootView.findViewById(R.id.location);
        return rootView;
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

    public void takePicture(View view){
        ((MenuActivity)view.getContext()).getBottomNavigationView().setSelectedItemId(R.id.action_item3);
    }
}
