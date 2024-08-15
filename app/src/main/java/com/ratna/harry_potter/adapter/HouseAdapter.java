package com.ratna.harry_potter.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ratna.harry_potter.R;
import com.ratna.harry_potter.models.House;

import java.util.ArrayList;

public class HouseAdapter extends ArrayAdapter<House> {
    ArrayList<House> houseList;
    Activity activity;


    public HouseAdapter(@NonNull Activity activity, ArrayList<House> houseList) {
        super(activity, R.layout.house_item);
        this.activity = activity;
        this.houseList = houseList;
    }

    @Override
    public int getCount() {
        return houseList.size();
    }

    @NonNull
    @Override
    public View getView (int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.house_item, null, true);
        House house = houseList.get(position);

        TextView houseName = view.findViewById(R.id.house_name);
        ImageView imageView = view.findViewById(R.id.house_image);

        houseName.setText(house.getName());
        imageView.setImageResource(house.getImage());
        return view;
    }
}
