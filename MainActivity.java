package com.nextraq.interview;

import android.content.res.Resources;
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

/**
 * Modified: Sheila Owens
 * Date:     Aug 20, 2016
 * Desc:     Made the changes listed above.
 */
public class MainActivity extends AppCompatActivity {

    private Button mSubmitButton;
    private EditText mName;
    private String inputText;
    private TextView mGreeting;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mName = (EditText) findViewById(R.id.name_input);
        mGreeting = (TextView) findViewById(R.id.personalized_greeting);

        mSubmitButton = (Button) findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mName.getText().toString().isEmpty()) {
                    mName.setError("Name must be entered");
                } else {
                    inputText = mName.getText().toString();
                    Resources res = getResources();
                    String text = String.format(res.getString(R.string.hello), inputText);
                    mGreeting.setText(text);
                }
            }
        });

    }
}
