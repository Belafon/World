package com.example.world.GameActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.world.R;

public class GameActivity extends AppCompatActivity {
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
        openWeitingScreenFragment();
    }
    public void openWeitingScreenFragment(){
        WaitingScreenFragment fragment = new WaitingScreenFragment();

        openFragment(fragment);
    }
    public void openFragment(Fragment fragment){
        this.fragment = fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.menu_fragment, fragment);
        fragmentTransaction.commit();
    }
}