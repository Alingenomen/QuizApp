package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        int score = getIntent().getIntExtra("passedScore", 0);
        String firstName = getIntent().getStringExtra("firstName");
        String lastName = getIntent().getStringExtra("lastName");

        TextView applicant = findViewById(R.id.userInformation);
        applicant.setText(firstName + " " + lastName);
        TextView scoreText = findViewById(R.id.scoreInformation);
        scoreText.setText(Integer.toString(score) + "/" + "10");
        TextView footer = findViewById(R.id.textFooter);
        if (score < 6){
            footer.setText(R.string.betterLuck);
        } else {
            footer.setText(R.string.congratz);
        }

    }
}
