package com.example.android.quizapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Questions2of5Activity extends AppCompatActivity {

    private int score;
    private String firstName, lastName;
    private CheckBox option1, option2, option3, option4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions2of5);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        firstName = getIntent().getStringExtra("firstName");
        lastName = getIntent().getStringExtra("lastName");

        score = getIntent().getIntExtra("passedScore",0);
    }

    // Move on the the next question
    public void nextQuestion(View view){
        // If the current question has been answered, validate & move on to the next question
        if (checkAnswered()){
            // Check for right or wrong answer
            validateQuestion();

            // Move on to the new question
            Intent intent = new Intent(getApplicationContext(),Questions3of5Activity.class);
            if (intent.resolveActivity(getPackageManager()) != null) {
                intent.putExtra("passedScore",score);
                intent.putExtra("firstName",firstName);
                intent.putExtra("lastName",lastName);
                startActivity(intent);

                // Destroy activity to prevent user from going back
                finish();
            }
        }
        else {
                displayToast("You need to answer the question before moving on to the next");
        }
    }

    // Check if the question has been answered
    private boolean checkAnswered(){
        // If at least one option is selected, the question as been answered
        if (option1.isChecked() || option2.isChecked() || option3.isChecked() || option4.isChecked()) {
            return true;
        }
        // If no option selected, the question hasn't been answered
        else{
            return false;
        }
    }

    // Method to validate the question & update the score if applicable
    private void validateQuestion(){
        int questionScore = 0;

        // If the correct question has been chosen, update the score
        if (option2.isChecked()) {
            questionScore = questionScore + 1;
        }

        if (option4.isChecked()) {
            questionScore = questionScore + 1;
        }

        // If the incorrect option has been chosen, update the score
        if (option3.isChecked()) {
            questionScore = questionScore + -1;
        }

        if (option1.isChecked()) {
            questionScore = questionScore + -1;
        }

        if (questionScore < 0) {
            score = score + 0;
        }
        else {
            score = score + questionScore;
        }

    }

    // Method to display a message to the user
    private void displayToast(CharSequence text){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
