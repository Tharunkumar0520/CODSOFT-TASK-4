package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalquestion;
    TextView question;
    Button ansa, ansb, ansc, ansd;
    Button sub;

    int score = 0;
    int total = Questions.questions.length;
    int current = 0;
    String selectedans = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalquestion = findViewById(R.id.totalquestion);
        question = findViewById(R.id.question);
        ansa = findViewById(R.id.answerA);
        ansb = findViewById(R.id.answerB);
        ansc = findViewById(R.id.answerC);
        ansd = findViewById(R.id.answerD);
        sub = findViewById(R.id.submitbtn);

        ansa.setOnClickListener(this);
        ansb.setOnClickListener(this);
        ansc.setOnClickListener(this);
        ansd.setOnClickListener(this);
        sub.setOnClickListener(this);

        totalquestion.setText("Total Question :" + total);

        loadQuestion();

    }

    @Override
    public void onClick(View v) {

        ansa.setBackgroundColor(Color.WHITE);
        ansb.setBackgroundColor(Color.WHITE);
        ansc.setBackgroundColor(Color.WHITE);
        ansd.setBackgroundColor(Color.WHITE);

        Button clickedbutton = (Button) v;
        if (clickedbutton.getId() == R.id.submitbtn) {

            if (selectedans.equals(Questions.crtans[current])) {
                score++;
            }
            current++;
            loadQuestion();
        } else {
            selectedans = clickedbutton.getText().toString();
            clickedbutton.setBackgroundColor(Color.MAGENTA);
        }

    }

    void loadQuestion() {

        if (current == total) {
            stopQuiz();
            return;
        }

        question.setText(Questions.questions[current]);
        ansa.setText(Questions.choices[current][0]);
        ansb.setText(Questions.choices[current][1]);
        ansc.setText(Questions.choices[current][2]);
        ansd.setText(Questions.choices[current][3]);
    }

    void stopQuiz() {

        String pass = "";
        if (score > total * 0.60) {
            pass = "Passed";
        } else {
            pass = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(pass)
                .setMessage("Score For This Quiz is " + score + " Out Of " + total)
                .setPositiveButton("Restart", (dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }

    void restartQuiz() {
        score = 0;
        current = 0;
        loadQuestion();
    }
}