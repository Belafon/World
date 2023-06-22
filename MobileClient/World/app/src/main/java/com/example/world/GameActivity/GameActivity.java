package com.example.world.gameActivity;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.world.AbstractActivity;
import com.example.world.R;

public class GameActivity extends AbstractActivity {
    public LinearLayout navigation;
    public Fragment fragment;
    public LinearLayout notifications;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        navigation = (LinearLayout) findViewById(R.id.navigation);
        fragment = (Fragment) getSupportFragmentManager().findFragmentById(R.id.game_fragment);
        notifications = (LinearLayout) findViewById(R.id.notifications);
        SetActivity.setGameActivity(this);
        openWaitingScreenFragment();
    }
    public void openWaitingScreenFragment(){
        openFragment(new WaitingScreenFragment(), R.id.menu_fragment);
    }
}