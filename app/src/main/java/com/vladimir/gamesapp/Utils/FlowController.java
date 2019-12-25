package com.vladimir.gamesapp.Utils;

import android.app.Activity;
import android.content.Intent;

import com.google.gson.Gson;
import com.vladimir.gamesapp.Activity.GameDetailActivity;
import com.vladimir.gamesapp.Activity.HomeActivity;
import com.vladimir.gamesapp.Activity.MainActivity;
import com.vladimir.gamesapp.Api.Model.GameModel;

public class FlowController {

    public static void loginRedirect(Activity targetActivity,Class<?> destinationActivity) {
        Intent login_intent = new Intent(targetActivity, destinationActivity);
        login_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        targetActivity.startActivity(login_intent);
        targetActivity.finish();
    }

    public static void showRegister(Activity targetActivity, Class<?> destinationActivity) {
        Intent registration_intent = new Intent(targetActivity, destinationActivity);
        targetActivity.startActivity(registration_intent);
    }

    public static void logout(Activity targetActivity) {
        SharedPreferencesUtils.logoutUser(targetActivity);
        Intent logout_intent = new Intent(targetActivity, MainActivity.class);
        logout_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        targetActivity.startActivity(logout_intent);
        targetActivity.finish();
    }

    public static void showLogin(Activity targetActivity) {
        Intent intent = new Intent(targetActivity, MainActivity.class);
        targetActivity.startActivity(intent);
    }

    public static void showGameDetailActivity(Activity targetActivity, GameModel gameModel) {
        Intent intent = new Intent(targetActivity, GameDetailActivity.class);
        Gson gson = new Gson();
        intent.putExtra("game_model",gson.toJson(gameModel));
        targetActivity.startActivity(intent);
    }

    public static void showHome(Activity targetActivity) {
        Intent intent = new Intent(targetActivity, HomeActivity.class);
        targetActivity.startActivity(intent);
    }

}
