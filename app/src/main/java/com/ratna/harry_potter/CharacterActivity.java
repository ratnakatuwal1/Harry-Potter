package com.ratna.harry_potter;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ratna.harry_potter.adapter.CharacterAdapter;
import com.ratna.harry_potter.models.HarryPotterCharacter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CharacterActivity extends AppCompatActivity {
    private ArrayList<HarryPotterCharacter> characterList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_character);
        //ToDo

        requestToServer();
    }
    void requestToServer(){
        RequestQueue queue = Volley.newRequestQueue(CharacterActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ALL_CHARACTERS, (Response.Listener<String>) response -> {
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

                //todo

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, (Response.ErrorListener) error -> {
            Log.e("ServerResponse", "Error: " + error.getMessage());
        });

        queue.add(stringRequest);
    }

    private ArrayList<String> convertJsonArrayToArrayList(JSONArray jsonArray) throws JSONException{
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            arrayList.add(jsonArray.getString(i));
        }
        return arrayList;
    }
}