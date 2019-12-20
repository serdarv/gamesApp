package com.vladimir.gamesapp.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.vladimir.gamesapp.Model.UserModel;

public class SharedPreferencesUtils {

    private static Gson gson = new Gson();

    public static void saveUser(UserModel user, Context activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("gamesAppSP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("loggedUser",gson.toJson(user));
        editor.apply();
    }

    public static UserModel getUser(Context activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("gamesAppSP",Context.MODE_PRIVATE);
        return gson.fromJson(sharedPreferences.getString("loggedUser",null),UserModel.class);
    }

    public static void logoutUser(Context activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("gamesAppSP",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
