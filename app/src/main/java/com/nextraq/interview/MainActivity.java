package com.nextraq.interview;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

/**
 * 1. Set the layout in `MainActivity.java` to use `main_activity.xml`
 * 2. When the submit button is clicked, update the greeting to be personalized based on input. Example: "Hello, Brad!"
 * <p/>
 * 3. If time allows, Have the input field and personalized greeting maintain their state through screen rotations
 * 4. If time allows, Validate, only greet the user if valid input was provided
 * 5. If time allows, Show a validation error on the input field when it doesn't contain a name, but the submit button is pressed
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button submitButton;
    EditText nameInput;
    TextView personalizedGreeting;
    Pattern pattern = Pattern.compile("[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz]*");
    String nameRetrieved;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        submitButton = (Button) findViewById(R.id.submit_button);
        nameInput = (EditText) findViewById(R.id.name_input);
        personalizedGreeting = (TextView) findViewById(R.id.personalized_greeting);

        submitButton.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("nameRetrieved", nameRetrieved);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getString("nameRetrieved");
    }

    @Override
    public void onClick(View v) {
        nameRetrieved = String.valueOf(nameInput.getText());  //value retrieved
        boolean validationMatches = pattern.matcher(nameRetrieved).matches();  //Checking for validation
        nameInput.setHint(getApplicationContext().getResources().getString(R.string.input_hint)); //resetting the hint

        /*
        setting the personalized greeting based on valid value
        and dealing with validation error
        */
        if (nameRetrieved.length() != 0 && validationMatches) {
            Resources res = getResources();
            String greetingFormat = String.format(res.getString(R.string.hello), nameRetrieved);
            personalizedGreeting.setText(greetingFormat);
        } else nameInput.setHint(getApplicationContext().getResources().getString(R.string.input_error));

        nameInput.getText().clear();
    }
}
