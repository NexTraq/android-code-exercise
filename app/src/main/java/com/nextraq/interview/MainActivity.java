package com.nextraq.interview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 1. Set the layout in `MainActivity.java` to use `main_activity.xml`
 * 2. When the submit button is clicked, update the greeting to be personalized based on input. Example: "Hello, Brad!"
 *
 * 3. If time allows, Have the input field and personalized greeting maintain their state through screen rotations
 * 4. If time allows, Validate, only greet the user if valid input was provided
 * 5. If time allows, Show a validation error on the input field when it doesn't contain a name, but the submit button is pressed
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.name_input)
    EditText nameInput;

    @OnClick(R.id.submit_button)
    public void clickButton(Button button) {
        displayName(nameInput.getText().toString().trim());
    }

    @BindView(R.id.personalized_greeting)
    TextView personalizedGreeting;

    static final String STATE_GREETING = "STATE_GREETING";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save TextView text only, as EditText automatically saves state on rotate
        savedInstanceState.putString(STATE_GREETING, personalizedGreeting.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        personalizedGreeting.setText(savedInstanceState.getString(STATE_GREETING));
    }

    private void displayName(String input) {
        if (isValidInput(input)) {
            personalizedGreeting.setText(new StringBuilder()
                    .append(getString(R.string.greeting_start))
                    .append(nameInput.getText())
                    .append(getString(R.string.greeting_end))
                    .toString());
        } else {
            personalizedGreeting.setText("");
            nameInput.setError(getString(R.string.error_message));
        }
    }

    private boolean isValidInput(String input) {
        String NAME_PATTERN = getString(R.string.name_pattern);

        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
