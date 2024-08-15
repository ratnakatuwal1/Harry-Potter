package com.ratna.harry_potter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.ratna.harry_potter.models.HarryPotterCharacter;

public class CharacterDetailActivity extends AppCompatActivity {
    ImageView characterImage;
    TextView characterName, actorName, dateOfBirth, gender, house, species, ancestry, eyeColour, hairColour, patronus, wand, alive;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_character_detail);
        characterImage = findViewById(R.id.detail_character_image);
        characterName = findViewById(R.id.detail_character_name);
        actorName = findViewById(R.id.detail_actor_name);
        dateOfBirth = findViewById(R.id.detail_date_of_birth);
        gender = findViewById(R.id.detail_gender);
        house = findViewById(R.id.detail_house);
        species = findViewById(R.id.detail_species);
        ancestry = findViewById(R.id.detail_ancestry);
        eyeColour = findViewById(R.id.detail_eye_colour);
        hairColour = findViewById(R.id.detail_hair_colour);
        patronus = findViewById(R.id.detail_patronus);
        wand = findViewById(R.id.detail_wand);
        alive = findViewById(R.id.detail_alive);

        HarryPotterCharacter character = (HarryPotterCharacter) getIntent().getSerializableExtra("character");
        if (character != null) {
            Glide.with(this).load(character.getImage()).into(characterImage);

            characterName.setText(character.getName());
            actorName.setText("Played by: " + character.getActor());
            dateOfBirth.setText("DOB: " + character.getDateOfBirth());
            gender.setText("Gender: " + character.getGender());
            house.setText("House: " + character.getHouse());
            species.setText("Species: " + character.getSpecies());
            ancestry.setText("Ancestry: " + character.getAncestry());
            eyeColour.setText("Eye Colour: " + character.getEyeColour());
            hairColour.setText("Hair Colour: " + character.getHairColour());
            patronus.setText("Patronus: " + character.getPatronus());

            HarryPotterCharacter.Wand characterWand = character.getWand();
            if (characterWand != null) {
                wand.setText("Wand: " + characterWand.getWood() + " wood, core: " + characterWand.getCore() + ", length: " + characterWand.getLength() + " inches");
            } else {
                wand.setText("Wand: No wand information");
            }
            alive.setText("Alive: " + (character.isAlive() ? "Yes" : "No"));
        }
    }
}