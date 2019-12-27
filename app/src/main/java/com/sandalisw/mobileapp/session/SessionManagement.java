package com.sandalisw.mobileapp.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.sandalisw.mobileapp.models.User;

public class SessionManagement {

    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    static String SESSION_KEY = "session_user";

    public SessionManagement(Context context){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public void saveSession(User user){
        //save session when user signup
        String username = user.getUsername();

        editor.putString(SESSION_KEY,username).commit();

    }

    public static String getSession(){
        //return session whose session is saved
        return sharedPreferences.getString(SESSION_KEY,"");

    }
}
