package com.example.clinicaapp.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.clinicaapp.activities.LoginActivity;

public class SessionManager {
    private static final String PREF_NAME = "MisPreferencias";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_USER_TYPE = "userType";

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void saveSession(int userId, String userType) {
        editor.putInt(KEY_USER_ID, userId);
        editor.putString(KEY_USER_TYPE, userType);
        editor.apply();
    }

    public int getUserId() {
        return prefs.getInt(KEY_USER_ID, -1);
    }

    public String getUserType() {
        return prefs.getString(KEY_USER_TYPE, null);
    }

    public void clearSession() {
        editor.clear();
        editor.apply();
    }

    public boolean isLoggedIn() {
        return getUserId() != -1;
    }
}