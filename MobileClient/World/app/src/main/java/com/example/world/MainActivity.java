package com.example.world;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.world.dataSafe.DataLibrary;
import com.example.world.menuScreen.MenuActivity;
import com.example.world.menuScreen.WelcomingFragments.WelcomingActivity;

public class MainActivity extends AbstractActivity {
    private static final String TAG = "MainActivity";
    private static final int sizeOfTimeInFirstScreen = 500;
    public static final DataLibrary startData = new DataLibrary("startData");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Screen.setScreen(this);

        boolean isFirstAppStart = startData.LoadDataBool(this, "isFirstStart");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent menuIntent = null;
                if(isFirstAppStart) menuIntent = new Intent(MainActivity.this , WelcomingActivity.class);
                else menuIntent = new Intent(MainActivity.this , MenuActivity.class);
                startActivity(menuIntent);
                finish();
            }
        }, sizeOfTimeInFirstScreen);
    }
}