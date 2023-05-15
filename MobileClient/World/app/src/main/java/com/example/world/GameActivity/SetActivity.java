package com.example.world.GameActivity;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.world.Screen;

public class SetActivity {
    public static void setGameActivity(GameActivity gameActivity){
        int navWidth = Screen.screenWidth * 10 / 100;
        int fragWidth = Screen.screenWidth * 70 / 100;
        int notificWidth = Screen.screenWidth - navWidth - fragWidth;
        gameActivity.navigation.setLayoutParams(new LinearLayout.LayoutParams( navWidth, ViewGroup.LayoutParams.MATCH_PARENT));
        gameActivity.notifications.setLayoutParams(new LinearLayout.LayoutParams( notificWidth, ViewGroup.LayoutParams.MATCH_PARENT));
        gameActivity.fragment.getView().setLayoutParams(new LinearLayout.LayoutParams( fragWidth, ViewGroup.LayoutParams.MATCH_PARENT));
    }
}
