package com.nextraq.interview.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tjw127 on 1/12/18.
 */

public class Validation {

    public boolean validateName(String text){

        Pattern pattern = Pattern.compile("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$");
        Matcher matcher = pattern.matcher(text);

        return matcher.matches();
    }
}
