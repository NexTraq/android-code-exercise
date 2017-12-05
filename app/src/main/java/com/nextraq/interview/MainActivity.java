package com.nextraq.interview;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 1. Set the layout in `MainActivity.java` to use `main_activity.xml`
 * 2. When the submit button is clicked, update the greeting to be personalized based on input. Example: "Hello, Brad!"
 *
 * 3. If time allows, Have the input field and personalized greeting maintain their state through screen rotations
 * 4. If time allows, Validate, only greet the user if valid input was provided
 * 5. If time allows, Show a validation error on the input field when it doesn't contain a name, but the submit button is pressed
 */

/**
 * @since 12/4/17 by David Hope
 * */

public class MainActivity extends AppCompatActivity {

    public static final String KEY_GREETING = "KEY_GREETING";

    Button submitButton;
    TextView userGreetingView;
    EditText userNameView;


    @Override
    public void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        submitButton = (Button) findViewById(R.id.submit_button);

        userGreetingView = (TextView) findViewById(R.id.personalized_greeting);

        userNameView = (EditText) findViewById(R.id.name_input);
        userNameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {

                if (s.length() > 0) {

                    Resources resources = getResources();
                    String userInput = String.format(resources.getString(R.string.hello), s);

                    // Save User Input String for config change restoration
                    Bundle bundle = new Bundle();
                    bundle.putString(KEY_GREETING, userInput);
                    bundle.putAll(bundle);

                    // Display Greeting message without button click only in a config change scenario
                    if (savedInstanceState != null) {
                        userGreetingView.setText(bundle.getString(KEY_GREETING));
                    }

                }

                // Display Greeting with User's name
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                            if (s.length() > 0) {

                                Resources resources = getResources();
                                String userInput = String.format(resources.getString(R.string.hello), s);
                                    userGreetingView.setText(userInput);

                            } else {
                                userGreetingView.setText(R.string.input_error);
                            }

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

}
