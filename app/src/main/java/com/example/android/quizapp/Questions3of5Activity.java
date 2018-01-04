package com.example.android.quizapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Questions3of5Activity extends AppCompatActivity {

    private int score;
    private String firstName, lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions3of5);

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
            Intent intent = new Intent(getApplicationContext(),Questions4of5Activity.class);
            if (intent.resolveActivity(getPackageManager()) != null) {
                intent.putExtra("passedScore",score);
                intent.putExtra("firstName",firstName);
                intent.putExtra("lastName",lastName);
                startActivity(intent);

                // Destroy activity to prevent user from going back
                finish();
            }
        }

        // If the question has not been answered, provide a toast message
        else {
            displayToast("You need to answer the question before moving on to the next");
        }
    }

    // Check if the question has been answered
    private boolean checkAnswered(){
        RadioGroup Question = findViewById(R.id.question3);
        int checkedRadioButtonId = Question.getCheckedRadioButtonId();

        // If no option selected, the question hasn't been answered
        if (checkedRadioButtonId == -1) {
            return false;
        }
        // If an option is selected, the question as been answered
        else{
            return true;
        }
    }

    // Method to validate the question & update the score if applicable
    private void validateQuestion(){
        RadioGroup Question = findViewById(R.id.question3);
        int checkedRadioButtonId = Question.getCheckedRadioButtonId();

        // If the correct question has been chosen, update the score & display a toast message
        if (checkedRadioButtonId == R.id.radio4_question3) {
            score = score + 2;
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
