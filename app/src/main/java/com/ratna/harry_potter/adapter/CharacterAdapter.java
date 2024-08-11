package com.ratna.harry_potter.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ratna.harry_potter.CharacterDetailActivity;
import com.ratna.harry_potter.R;
import com.ratna.harry_potter.models.HarryPotterCharacter;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private Context context;
    private ArrayList<HarryPotterCharacter> characterList;

    public CharacterAdapter(Context context, ArrayList<HarryPotterCharacter> characterList) {
        this.context = context;
        this.characterList = characterList;
    }

    @NonNull
    @Override
    public CharacterAdapter.CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_character, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapter.CharacterViewHolder holder, int position) {
HarryPotterCharacter character = characterList.get(position);

holder.characterName.setText(character.getName());
holder.actorName.setText("Played by: " + character.getActor());
holder.dateOfBirth.setText("DOB: " + character.getDateOfBirth());
holder.gender.setText("Gender: " +character.getGender());
holder.house.setText("House: " + character.getHouse());

StringBuilder akaBuilder = new StringBuilder("A.K.A: ");
for (String name : character.getAlternate_names()) {
    akaBuilder.append(name).append(", ");
}
if (akaBuilder.length() > 0) {
    akaBuilder.setLength(akaBuilder.length() - 2);
}
holder.alternateNames.setText(akaBuilder.toString());

Glide.with(context)
.load(character.getImage())
.into(holder.characterImage);

holder.itemView.setOnClickListener(view -> {
    Intent intent = new Intent(context, CharacterDetailActivity.class);
    intent.putExtra("character", character);
    context.startActivity(intent);
});
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public class CharacterViewHolder extends RecyclerView.ViewHolder {
        ImageView characterImage;
        TextView characterName;
        TextView actorName;
        TextView dateOfBirth;
        TextView gender;
        TextView house;
        TextView alternateNames;

        public CharacterViewHolder(View itemView) {
            super(itemView);
            characterImage = itemView.findViewById(R.id.character_image);
            characterName = itemView.findViewById(R.id.character_name);
            actorName = itemView.findViewById(R.id.actor_name);
            dateOfBirth = itemView.findViewById(R.id.date_of_birth);
            gender = itemView.findViewById(R.id.gender);
            house = itemView.findViewById(R.id.house);
            alternateNames = itemView.findViewById(R.id.alternate_names);
        }
    }
}
