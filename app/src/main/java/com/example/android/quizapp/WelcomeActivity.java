package com.example.android.quizapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    private String firstName, lastName;
    private EditText firstNameView, lastNameView;
    private CheckBox checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        firstNameView = findViewById(R.id.firstName);
        lastNameView = findViewById(R.id.lastName);
        checked = findViewById(R.id.agree);

        if (savedInstanceState != null){
            // load the variables from the saved state
            // using the "get" command followed by the
            // variable type
            firstNameView.setText(savedInstanceState.getString("firstName"));
            lastNameView.setText(savedInstanceState.getString("lastName"));
            checked.setChecked(savedInstanceState.getBoolean("checked"));
        }
    }

    // When the screen rotates, the function
    // onSaveInstanceState is called

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // load the current variables in a "saved state"
        // using the "put" command followed by the
        // variable type
        checked = findViewById(R.id.agree);
        firstName = firstNameView.getText().toString();
        lastName= lastNameView.getText().toString();
        outState.putString("firstName",firstName);
        outState.putString("lastName",lastName);
        outState.putBoolean("checked",checked.isChecked());
    }

    public void startQuiz(View view){
        if (checkAnswered()){
            if (checkAgree()){
                Intent intent = new Intent(getApplicationContext(),Questions1of5Activity.class);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    intent.putExtra("firstName",firstName);
                    intent.putExtra("lastName",lastName);
                    startActivity(intent);
                }
            }
            else{
                displayToast("You need to declare the user information as correct");
            }

        }
        else{
            displayToast("You need to enter a valid First Name & Last Name");
        }
    }

    // Check if the EditTexts are filled
    private boolean checkAnswered(){
        firstName = firstNameView.getText().toString();
        lastName = lastNameView.getText().toString();

        // If no option selected, at least one of the EditTexts have not been filled
        if (firstName.length() < 1 || lastName.length() < 1) {
            return false;
        }
        // If an option is selected, the question as been answered
        else{
            return true;
        }
    }

    // Checks if the user has declared the information correct
    private boolean checkAgree(){
        return checked.isChecked();
    }

    // Method to display a message to the user
    private void displayToast(CharSequence text){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
