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
import com.ratna.harry_potter.datas.HarryPotterQuestionBank;
import com.ratna.harry_potter.datas.HarryPotterQuiz;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    QuizAdapter quizAdapter;
    ArrayList<HarryPotterQuiz> questions;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        recyclerView = findViewById(R.id.Quizrecycler_view);
        layoutManager = new LinearLayoutManager(QuizActivity.this);
        recyclerView.setLayoutManager(layoutManager);


        // write an algorthim to generte random harrypotter question
        // with given paramenter of total questions in
        // HarryPotterQuestionBank class

        // HarryPotterQuestionBank harryPotterQuiz = new HarryPotterQuestionBank();
        //
        //        questions =  get your questions from questionbanks.

        HarryPotterQuestionBank harryPotterQuestionBank = new HarryPotterQuestionBank();
       ArrayList<Integer> questionNumbers = harryPotterQuestionBank.questionNumbers(10);
       questions = harryPotterQuestionBank.getQuiz(questionNumbers);
       quizAdapter = new QuizAdapter(QuizActivity.this, questions);
       recyclerView.setAdapter(quizAdapter);


//            ArrayList<Integer> questionNumbers = new ArrayList<>();
//            harryPotterQuestionBank.questionNumbers(10);


        }
//        quizAdapter = new QuizAdapter(this, questions);
//        recyclerView.setAdapter(quizAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }