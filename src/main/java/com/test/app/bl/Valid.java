package com.test.app.bl;

import java.util.regex.Pattern;

public class Valid {
    private Pattern pattern; 
    
    public Valid() { 
        pattern = Pattern.compile("^[0-9._-]{6}$"); 
    } 
    
    public boolean validate(final String password) { 
        return pattern.matcher(password).matches(); 
    } 

}