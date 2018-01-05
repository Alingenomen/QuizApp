package com.example.android.quizapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Questions4of5Activity extends AppCompatActivity {

    private int score;
    private String firstName, lastName;
    private EditText question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions4of5);

        question = findViewById(R.id.cape);

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
            Intent intent = new Intent(getApplicationContext(),Questions5of5Activity.class);
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
        String answer = question.getText().toString();

        // If no option selected, the question hasn't been answered
        if (answer.length() < 1) {
            return false;
        }
        // If an option is selected, the question as been answered
        else{
            return true;
        }
    }

    // Method to validate the question & update the score if applicable
    private void validateQuestion(){
        String capeAnswer = question.getText().toString();
        String correctAnswer = "convective available potential energy";

        // If the correct answer has been given, update the score & display a toast message
        if (capeAnswer.equalsIgnoreCase(correctAnswer)) {
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
