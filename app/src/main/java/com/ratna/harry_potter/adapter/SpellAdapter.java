package com.ratna.harry_potter.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ratna.harry_potter.R;
import com.ratna.harry_potter.models.Spell;

import java.util.ArrayList;

public class SpellAdapter extends RecyclerView.Adapter<SpellAdapter.SpellViewHolder> {
    Activity activity;
    ArrayList<Spell> spells;

    public SpellAdapter(Activity activity, ArrayList<Spell> spells){
        this.activity = activity;
        this.spells = spells;

    }


    @NonNull
    @Override
    public SpellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.item_spell, parent, false);
        SpellViewHolder spellViewHolder = new SpellViewHolder(view);
        return spellViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SpellAdapter.SpellViewHolder holder, int position) {
        Spell spell = spells.get(position);
        holder.spellName.setText(spell.getName());
        holder.spellDescription.setText(spell.getDescription());

    }

    @Override
    public int getItemCount() {
        return spells.size();
    }

    public static class SpellViewHolder extends RecyclerView.ViewHolder {
        TextView spellName, spellDescription;

        public SpellViewHolder(View itemView) {

            super(itemView);
            spellName = itemView.findViewById(R.id.spell_name);
            spellDescription = itemView.findViewById(R.id.spell_description);


        }
    }
}
