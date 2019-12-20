package com.vladimir.gamesapp.Utils;

import android.app.Activity;
import android.content.Intent;

import com.vladimir.gamesapp.Activity.MainActivity;

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

    public static void logout(Activity activity) {
        SharedPreferencesUtils.logoutUser(activity);
        Intent logout_intent = new Intent(activity, MainActivity.class);
        logout_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(logout_intent);
        activity.finish();
    }

    public static void showLogin(Activity targetActivity) {
        Intent intent = new Intent(targetActivity, MainActivity.class);
        targetActivity.startActivity(intent);
    }

}
