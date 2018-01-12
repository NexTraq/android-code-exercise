package com.nextraq.interview;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nextraq.interview.validation.Validation;


/**
 * 1. Set the layout in `MainActivity.java` to use `main_activity.xml`
 * 2. When the submit button is clicked, update the greeting to be personalized based on input. Example: "Hello, Brad!"
 *
 * 3. If time allows, Have the input field and personalized greeting maintain their state through screen rotations
 * 4. If time allows, Validate, only greet the user if valid input was provided
 * 5. If time allows, Show a validation error on the input field when it doesn't contain a name, but the submit button is pressed
 */
public class MainActivity extends AppCompatActivity {

    private Button submit = null;
    private EditText name_input = null;
    private TextView personalized_greeting = null;
    private final static String personalized_greeting_shared_preferences = "Personalized_Greeting_Shared_Preferences";
    private final static String name_input_shared_preferences = "Name_Input_Shared_Preferences";
    private SharedPreferences sharedPreferences = null;
    private AlertDialog alertDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        createSharedPreferences();
        createAlertDialog();
        createNameInput();
        createPersonalized_Greeting();
        createSubmit();

    }

    @Override
    protected void onStart() {
        super.onStart();

        resumeSession();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumeSession();

    }

    @Override
    protected void onPause() {
        super.onPause();
        saveCurrentSessionData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onStop() {
        super.onStop();

        saveCurrentSessionData();
    }

    /*
        Important Note:
        I'm not done with the interview code because I have some personal errands to run.
     */

    //-------------------UI Components Methods---------------

    private void createSubmit()
    {
        if (submit == null)
        {
            submit = (Button)findViewById(R.id.submit_button);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (name_input != null) {

                        if (name_input.getText().toString().length() > 0) {

                            Validation vaildation = new Validation();

                            boolean bradBoolean = vaildation.validateName(name_input.getText().toString());

                            String message = "Hello, ";

                            saveNameInputData(name_input.getText().toString());


                            if (alertDialog != null) {
                                if (bradBoolean) {

                                    message += name_input.getText().toString();

                                    savePersonalized_Greeting(message);

                                    if (personalized_greeting != null) {


                                        personalized_greeting.setText(message);
                                    } else {

                                        createPersonalized_Greeting();
                                        personalized_greeting.setText(message);
                                    }

                                } else {

                                    alertDialog.show();
                                }
                            }

                        }else {

                            alertDialog.show();
                        }

                    }

                }
            });
        }
    }

    private void createPersonalized_Greeting()
    {
        if (personalized_greeting == null){

            personalized_greeting = (TextView) findViewById(R.id.personalized_greeting);
        }

    }

    private void createNameInput()
    {
        if (name_input == null)
        {
            name_input = (EditText)findViewById(R.id.name_input);

            name_input.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {


                }
            });
        }
    }
    private void createAlertDialog()
    {
        if (alertDialog == null)
        {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
            alertBuilder.setTitle("Invaild Input");
            alertBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.cancel();
                }
            });

            alertDialog = alertBuilder.create();
        }
    }

    //--------------------------End UI Components Methods-----------------------------

    //---------------------SharedPreferences---------------
    private void createSharedPreferences()
    {
        if (sharedPreferences == null)
        {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        }
    }

    private void saveCurrentSessionData()
    {
        if (!isSharedPreferencesNull()){
            if (name_input != null) {

                saveNameInputData(name_input.getText().toString());

                if (personalized_greeting != null) {

                    if (personalized_greeting.getText().toString().length() > 0) {

                        savePersonalized_Greeting(personalized_greeting.getText().toString());

                    }

                }
            }else {

                //throw new NullPointerException();
            }
        }
    }

    private void resumeSession()
    {
        if (isTextSaved())
        {
            String data = getNameInputSharedPreferences();

            name_input.setText(data);
        }

        if (isPersonalizedGreetingSaved())
        {
            String personalizedGreeting = getPersonalizedGreetingSharedPreferences();

            personalized_greeting.setText(personalizedGreeting);
        }


    }


    private boolean isNameInputStringSaved()
    {
        String name_input_string = getNameInputSharedPreferences();

        if (name_input_string.length() > 0){

            return true;
        }else {

            return false;
        }
    }

    private boolean isPersonalizedGreetingSaved()
    {
        String name_input_string = getPersonalizedGreetingSharedPreferences();

        if (name_input_string.length() > 0){

            return true;
        }else {

            return false;
        }
    }

    private String getNameInputSharedPreferences()
    {
        if (sharedPreferences == null)
        {
            createSharedPreferences();
        }

        return sharedPreferences.getString(name_input_shared_preferences, "");
    }

    private String getPersonalizedGreetingSharedPreferences()
    {
        if (sharedPreferences == null)
        {
            createSharedPreferences();
        }

        return sharedPreferences.getString(personalized_greeting_shared_preferences, "");
    }

    private boolean isSharedPreferencesNull(){

        if (sharedPreferences != null)
        {
            return false;

        }else {

            return true;
        }
    }

    private void removeObjectsFromMemory()
    {
        if (!isSharedPreferencesNull())
        {
            SharedPreferences.Editor removeSharedPreferencesData = sharedPreferences.edit();
            removeSharedPreferencesData.clear();
            removeSharedPreferencesData.apply();
            sharedPreferences = null;
        }

        if (name_input != null)
        {
            name_input = null;
        }

        if (personalized_greeting != null)
        {
            personalized_greeting = null;
        }
    }


    private boolean isTextSaved()
    {

        /*
        isTextSaved is a method that will check if the text is saved to the SharedPreferences
         */
        if (isSharedPreferencesNull())
        {
            createSharedPreferences();

            if (!isNameInputStringSaved())
            {
                return true;

            }

        }else {

            if (!isNameInputStringSaved()){

                return true;

            }

        }

        return false;
    }


    private void saveNameInputData(String value)
    {
        /*
        SaveNameInputData method saves data to SharePreferences
         */
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name_input_shared_preferences, value);
        editor.apply();
    }

    private void savePersonalized_Greeting(String value)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(personalized_greeting_shared_preferences, value);
        editor.apply();
    }

    //---------------------End SharedPreferences----------



    @Override
    protected void onDestroy() {
        super.onDestroy();

        //removeObjectsFromMemory();

    }


}
