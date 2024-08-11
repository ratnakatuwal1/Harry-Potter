package com.ratna.harry_potter;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ratna.harry_potter.adapter.QuizAdapter;
import com.ratna.harry_potter.datas.HarryPotterQuiz;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private QuizAdapter quizAdapter;
    private ArrayList<HarryPotterQuiz> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        recyclerView = findViewById(R.id.Quizrecycler_view);


        // write an algorthim to generte random harrypotter question
        // with given paramenter of total questions in
        // HarryPotterQuestionBank class

        // HarryPotterQuestionBank harryPotterQuiz = new HarryPotterQuestionBank();
        //
        //        questions =  get your questions from questionbanks.
        quizAdapter = new QuizAdapter(this, questions);
        recyclerView.setAdapter(quizAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}