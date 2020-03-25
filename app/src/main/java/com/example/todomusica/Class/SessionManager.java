package com.example.todomusica.Class;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SessionManager (Context context) {
        sharedPreferences = context.getSharedPreferences("AppKey", 0);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public void setLogin (Boolean login) {
        editor.putBoolean("KEY_LOGIN", login);
        editor.commit();
    }

    public boolean isLogged () {
        return sharedPreferences.getBoolean("KEY_LOGIN", false);
    }

    public void setData (String name, String surname, String username) {
        editor.putString("KEY_NAME", name);
        editor.putString("KEY_SURNAME", surname);
        editor.putString("KEY_USERNAME", username);
        editor.commit();
    }

    public String getName () {
        return sharedPreferences.getString("KEY_NAME", "");
    }

    public String getSurname () {
        return sharedPreferences.getString("KEY_SURNAME", "");
    }

    public String getUsername () {
        return sharedPreferences.getString("KEY_USERNAME", "");
    }

    public void logout () {
        editor.clear();
        editor.commit();
    }
}
