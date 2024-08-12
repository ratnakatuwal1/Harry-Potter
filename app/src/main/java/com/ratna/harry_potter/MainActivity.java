package com.ratna.harry_potter;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private GridView gridView;
    // gridview variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.homeGridView);
//DO THIS ONLY IF YOU WANT TO PLAY BACKGROUND MUSIC and when app is completely built;
//        mediaPlayer = MediaPlayer.create(this, R.raw.harry_potter_theme);
//        mediaPlayer.setLooping(true); // Optional: to loop the music
//        mediaPlayer.start();

        // All Characters, Hogwarts Students, Hogwarts Staff, Houses, Spells, Quiz
        String[] items = {
                "All Characters",
                "Hogwarts Students",
                "Hogwarts Staff",
                "Houses",
                "Spells",
                "Quiz"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.item_basic_view, R.id.itemGrid, items);
        gridView.setAdapter(adapter);

        // at gridview.SETONITEMLISTENER will go to 6 different activiteies i.e
// CharacterActivity.class
//StudentActivity.class
//StaffActivity.class
//HousesActivity.class
//SpellActivity.class
//QuizActivity
//  gridView = findViewById(R.id.homeGridView);
        gridView.setOnItemClickListener((adapterView, view, position, id) -> {
            Intent intent;
            switch (position) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    intent = new Intent(MainActivity.this, HouseActivity.class);
                    startActivity(intent);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        });

//        @Override
//        protected void onDestroy() {
//            super.onDestroy();
//            if (mediaPlayer != null) {
//                if (mediaPlayer.isPlaying()) {
//                    mediaPlayer.stop();
//                }
//                mediaPlayer.release();
//                mediaPlayer = null;
//            }
//        }
    }
}