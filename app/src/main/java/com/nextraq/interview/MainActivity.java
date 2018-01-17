package com.nextraq.interview;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 1. Set the layout in `MainActivity.java` to use `main_activity.xml`
 * 2. When the submit button is clicked, update the greeting to be personalized based on input. Example: "Hello, Brad!"
 *
 * 3. If time allows, Have the input field and personalized greeting maintain their state through screen rotations
 * 4. If time allows, Validate, only greet the user if valid input was provided
 * 5. If time allows, Show a validation error on the input field when it doesn't contain a name, but the submit button is pressed
 */
public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //Search Button action
        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Hide the soft keyboard when search button is clicked
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null :
                        getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                EditText nameInput = findViewById(R.id.name_input);
                String nameField = nameInput.getText().toString();

                if (nameField.isEmpty()) {
                    RelativeLayout relativeLayout = findViewById(R.id.main_activity_layout);
                    Snackbar snackbar = Snackbar
                            .make(relativeLayout, getString(R.string.input_error), Snackbar.LENGTH_SHORT);
                    snackbar.show();

                } else {
                    TextView personalizedGreeting = findViewById(R.id.personalized_greeting);
                    personalizedGreeting.setText("Hello " + nameField + "!");
                }
            }
        });

        EditText nameInput = findViewById(R.id.name_input);
        final TextView personalizedGreeting = findViewById(R.id.personalized_greeting);
        nameInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                personalizedGreeting.setText(null);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}