package fr.unice.polytech.polyincidents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterException;

/**
 * Created by user on 07/05/2018.
 */

    public class FragmentTracking extends Fragment {


        FrameLayout cameraLayout;
        Button captureButton, galleryButton;

        public static FragmentTracking newInstance() {
            FragmentTracking fragment = new FragmentTracking();
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tracking, container, false);
            cameraLayout = (FrameLayout) rootView.findViewById(R.id.cameraLayout);
            captureButton = (Button) rootView.findViewById(R.id.capture);
            galleryButton = (Button) rootView.findViewById(R.id.gallery);

            return rootView;
        }

    public void takePicture(View view){

    }
}
