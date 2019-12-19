package com.vladimir.gamesapp.Utils;

import android.app.Activity;
import android.content.Intent;

public class FlowController {

    public static void loginRedirect(Activity targetActivity,Class<?> destinationActivity) {
        Intent login_intent = new Intent(targetActivity, destinationActivity);
        login_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        targetActivity.startActivity(login_intent);
        targetActivity.finish();
    }

    public static void registerRedirect(Activity targetActivity,Class<?> destinationActivity) {
        Intent registration_intent = new Intent(targetActivity, destinationActivity);
        targetActivity.startActivity(registration_intent);
    }

}
