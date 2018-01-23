package com.nextraq.interview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * 1. Set the layout in `MainActivity.java` to use `main_activity.xml`
 * 2. When the submit button is clicked, update the greeting to be personalized based on input. Example: "Hello, Brad!"
 *
 * 3. If time allows, Have the input field and personalized greeting maintain their state through screen rotations
 * 4. If time allows, Validate, only greet the user if valid input was provided
 * 5. If time allows, Show a validation error on the input field when it doesn't contain a name, but the submit button is pressed
 */
public class MainActivity extends AppCompatActivity {
    Button submit;
    EditText editText;
    TextView textView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // connect to xml file
        setContentView(R.layout.main_activity);
        // set fixed screen orientation
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // initialize components
        submit = (Button)findViewById(R.id.submit_button);
        textView = (TextView)findViewById(R.id.personalized_greeting);
        editText = (EditText)findViewById(R.id.name_input);
        // override clickListener
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String inputName = editText.getText().toString().trim();
                // if string format invalid, toast text and return
                if(inputName == null || inputName.length() <= 0){
                    Toast.makeText(MainActivity.this, "Please enter a valid name...", Toast.LENGTH_SHORT).show();
                }
                // if valid is input
                else{
                    String output = "Hello, " + inputName + "!";
                    textView.setText(output);
                }
            }
        });
    }
}
