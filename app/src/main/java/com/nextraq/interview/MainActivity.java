package com.nextraq.interview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 1. Set the layout in `MainActivity.java` to use `main_activity.xml`
 * 2. When the submit button is clicked, update the greeting to be personalized based on input. Example: "Hello, Brad!"
 * <p/>
 * 3. If time allows, Have the input field and personalized greeting maintain their state through screen rotations
 * 4. If time allows, Validate, only greet the user if valid input was provided
 * 5. If time allows, Show a validation error on the input field when it doesn't contain a name, but the submit button is pressed
 */
public class MainActivity extends AppCompatActivity {

    private static final String ARG_NAME = "key_name";
    String mInputName;
    TextView mGreetingTextView;
    EditText mInputTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        Button submitButton = (Button) findViewById(R.id.submit_button);
        mGreetingTextView = (TextView) findViewById(R.id.personalized_greeting);
        mInputTextView = (EditText) findViewById(R.id.name_input);

        //See if we have saved the name on rotation. If so, reset the greeting
        if (savedInstanceState != null) {

            mInputName = savedInstanceState.getString(ARG_NAME);
            setGreeting();
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get name from input text view
                mInputName = mInputTextView.getText().toString();

                //If setting the greeting fails, display error on input text view
                if (!(setGreeting()))
                    mInputTextView.setError(getString(R.string.input_error));


            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        //Save the name on rotation
        outState.putString(ARG_NAME, mInputName);
        super.onSaveInstanceState(outState);
    }

    private boolean setGreeting() {

        //Check for a valid name before setting the greeting. Otherwise, it's an error
        if(mInputName != null && mInputName.length() > 0) {
            mGreetingTextView.setText(getString(R.string.hello, mInputName));
            return true;
        } else {
            //Clear out greeting if there is an error
            mGreetingTextView.setText(null);
            return false;
        }
    }
}
