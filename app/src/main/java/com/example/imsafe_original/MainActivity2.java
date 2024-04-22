package com.example.imsafe_original;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity2 extends AppCompatActivity
{
    TextView incidentTextView,urgentTextView;
    ImageView image;
    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        incidentTextView = (TextView) findViewById(R.id.one);
        urgentTextView = (TextView) findViewById(R.id.two);
        image = (ImageView) findViewById(R.id.img);
        video = (VideoView) findViewById(R.id.vid);

        MediaController mediaController = new MediaController(this);

        mediaController.setAnchorView(video);

        video.setMediaController(mediaController);

        Bundle extras = getIntent().getExtras();

        Bitmap capturedImage = getIntent().getParcelableExtra("capturedImage");

        Uri uri = Uri.parse(extras.getString("capturedVideo"));

        String incident = extras.getString("incident");
        String urgency = extras.getString("urgency");

        incidentTextView.setText(incident);
        urgentTextView.setText(urgency);

        image.setImageBitmap(capturedImage);

        video.setVideoURI(uri);

        video.start();

        // Retrieve location data from MainActivity's Intent
        double latitude = getIntent().getDoubleExtra("latitude", 0.0);
        double longitude = getIntent().getDoubleExtra("longitude", 0.0);

        // Pass the location data to the fragment in MainActivity2
        LocationFragment locationFragment = new LocationFragment();
        locationFragment.setLocation(latitude, longitude);

        // Display the fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, locationFragment)
                .commit();

    }
}