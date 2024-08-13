package com.ratna.harry_potter;

import static com.ratna.harry_potter.endpoint.ALL_STUDENTS;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ratna.harry_potter.adapter.CharacterAdapter;
import com.ratna.harry_potter.models.HarryPotterCharacter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {
    ArrayList<HarryPotterCharacter> characterList = new ArrayList<>();
    RecyclerView recyclerView;
    CharacterAdapter characterAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student);
        recyclerView = findViewById(R.id.allStudentsRecyclerView);
        layoutManager = new LinearLayoutManager(StudentActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        //TODO
        // SET LAYOUT FOR RECYCLER VIEW I.E LINEAR LAYOUT
        requestTOServer();
    }


    void requestTOServer(){


        RequestQueue queue = Volley.newRequestQueue(StudentActivity.this);
        Log.d("urls", ALL_STUDENTS);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                ALL_STUDENTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
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
                                    character.setYearOfBirth(0); // or some default value or handling
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
                                    wand.setLength(0); // or some default value or handling
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
                            characterAdapter = new CharacterAdapter(StudentActivity.this, characterList);
                            recyclerView.setAdapter(characterAdapter);

                            //TODO
                            //SET ADAPATE HERE I.E CHARACTER ADAPTER
                            // set adapter to recycler view


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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