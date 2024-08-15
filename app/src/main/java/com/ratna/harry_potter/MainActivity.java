package com.ratna.harry_potter;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.harry_potter_theme);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        gridView = findViewById(R.id.homeGridView);


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



        gridView.setOnItemClickListener((adapterView, view, position, id) -> {
            Intent intent;
            switch (position) {
                case 0:
                    intent = new Intent(MainActivity.this, CharacterActivity.class);
                    startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(MainActivity.this, StudentActivity.class);
                    startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(MainActivity.this, StaffActivity.class);
                    startActivity(intent);
                    break;
                case 3:
                    intent = new Intent(MainActivity.this, HouseActivity.class);
                    startActivity(intent);
                    break;
                case 4:
                    intent = new Intent(MainActivity.this, SpellActivity.class);
                    startActivity(intent);
                    break;
                case 5:
                    intent = new Intent(MainActivity.this, QuizActivity.class);
                    startActivity(intent);
                    break;
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
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