package com.ratna.harry_potter;

import static com.ratna.harry_potter.endpoint.GET_HOUSES;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ratna.harry_potter.adapter.CharacterAdapter;
import com.ratna.harry_potter.adapter.HouseAdapter;
import com.ratna.harry_potter.models.HarryPotterCharacter;
import com.ratna.harry_potter.models.House;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HouseActivity extends AppCompatActivity {
    GridView gridView;
    private ArrayList<House> houseList;
    TextView textView;
    private ArrayList<HarryPotterCharacter> characterList = new ArrayList<>();
    RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;
    private HouseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_house);

        gridView = findViewById(R.id.housesGridView);
        textView = findViewById(R.id.selectedHouse);
        recyclerView = findViewById(R.id.housesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        houseList = new ArrayList<>();
        houseList.add(new House("Gryffindor", R.drawable.ic_house_gryffindor));
        houseList.add(new House("Hufflepuff", R.drawable.ic_house_hufflepuff));
        houseList.add(new House("Ravenclaw", R.drawable.ic_house_ravenclaw));
        houseList.add(new House("Slytherin", R.drawable.ic_house_slytherin));

        adapter = new HouseAdapter(HouseActivity.this, houseList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((adapterView, view, position, id) -> {
           String selectedHouse = "";
            switch (position){
                case 0:
                    selectedHouse = "Gryffindor";
                    requestTOServer("Gryffindor");
                    break;
                case 1:
                    selectedHouse = "Hufflepuff";
                    requestTOServer("Hufflepuff");
                    break;
                case 2:
                    selectedHouse = "Ravenclaw";
                    requestTOServer("Ravenclaw");
                    break;
                case 3:
                    selectedHouse = "Slytherin";
                    requestTOServer("Slytherin");
                    break;
            }

            textView.setText("Selected House: " + selectedHouse);

        });

    }

    void requestTOServer(String house){

        characterList.clear();
        RequestQueue queue = Volley.newRequestQueue(HouseActivity.this);
        Log.d("HOUSEURL",GET_HOUSES+house);

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                GET_HOUSES+house,
                response -> {
                    Log.d("ServerResponse", response);

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            HarryPotterCharacter character = new HarryPotterCharacter();
                            character.setId(jsonObject.getString("id"));
                            character.setName(jsonObject.getString("name"));
                            character.setAlternate_names(convertJsonArrayToArrayList(jsonObject.getJSONArray("alternate_names")));
                            character.setSpecies(jsonObject.getString("species"));
                            character.setGender(jsonObject.getString("gender"));
                            character.setHouse(jsonObject.getString("house"));
                            character.setDateOfBirth(jsonObject.getString("dateOfBirth"));
                            if (jsonObject.has("yearOfBirth") && !jsonObject.isNull("yearOfBirth")) {
                                character.setYearOfBirth(jsonObject.getInt("yearOfBirth"));
                            } else {
                                character.setYearOfBirth(0);
                            }

                            character.setWizard(jsonObject.getBoolean("wizard"));
                            character.setAncestry(jsonObject.getString("ancestry"));
                            character.setEyeColour(jsonObject.getString("eyeColour"));
                            character.setHairColour(jsonObject.getString("hairColour"));

                            JSONObject wandObject = jsonObject.getJSONObject("wand");
                            HarryPotterCharacter.Wand wand = new HarryPotterCharacter.Wand();
                            wand.setWood(wandObject.getString("wood"));
                            wand.setCore(wandObject.getString("core"));
                            if (wandObject.has("length") && !wandObject.isNull("length")) {
                                wand.setLength(wandObject.getInt("length"));
                            } else {
                                wand.setLength(0);
                            }
                            character.setWand(wand);

                            character.setPatronus(jsonObject.getString("patronus"));
                            character.setHogwartsStudent(jsonObject.getBoolean("hogwartsStudent"));
                            character.setHogwartsStaff(jsonObject.getBoolean("hogwartsStaff"));
                            character.setActor(jsonObject.getString("actor"));
                            character.setAlternate_actors(convertJsonArrayToArrayList(jsonObject.getJSONArray("alternate_actors")));
                            character.setAlive(jsonObject.getBoolean("alive"));
                            character.setImage(jsonObject.getString("image"));

                            characterList.add(character);
                        }

                        characterAdapter = new CharacterAdapter(HouseActivity.this, characterList);
                        recyclerView.setAdapter(characterAdapter);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Log.e("ServerResponse", "Error: " + error.getMessage())
        );

        queue.add(stringRequest);

    }

    private ArrayList<String> convertJsonArrayToArrayList(JSONArray jsonArray) throws JSONException {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            arrayList.add(jsonArray.getString(i));
        }
        return arrayList;
    }
}