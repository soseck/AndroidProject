package fr.unice.polytech.polyincidents;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Created by user on 07/05/2018.
 */

    public class FragmentPhoto extends Fragment {


        FrameLayout cameraLayout;
        Button captureButton, galleryButton;

        public static FragmentPhoto newInstance() {
            FragmentPhoto fragment = new FragmentPhoto();
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_photo, container, false);
            cameraLayout = (FrameLayout) rootView.findViewById(R.id.cameraLayout);
            captureButton = (Button) rootView.findViewById(R.id.capture);
            galleryButton = (Button) rootView.findViewById(R.id.gallery);

            return rootView;
        }

    public void takePicture(View view){

    }

}
