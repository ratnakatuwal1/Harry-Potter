package com.ratna.harry_potter;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    // gridview variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//DO THIS ONLY IF YOU WANT TO PLAY BACKGROUND MUSIC and when app is completely built;
//        mediaPlayer = MediaPlayer.create(this, R.raw.harry_potter_theme);
//        mediaPlayer.setLooping(true); // Optional: to loop the music
//        mediaPlayer.start();


//TODO CREATE A GRIDVIEW IN LAYOUT

        //TODO create a basic griDviiew with 6 items i.e
        // All Characters, Hogwarts Students, Hogwarts Staff, Houses, Spells, Quiz
        // String[] items = {
        //            "All Characters",
        //            "Hogwarts Students",
        //            "Hogwarts Staff",
        //            "Houses",
        //            "Spells",
        //            "Quiz"
        //    };
        // at gridview.SETONITEMLISTENER will go to 6 different activiteies i.e

        // CharacterActivity.class
        //StudentActivity.class
        //StaffActivity.class
        //HousesActivity.class
        //SpellActivity.class
        //QuizActivity


        //  gridView = findViewById(R.id.homeGridView);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}