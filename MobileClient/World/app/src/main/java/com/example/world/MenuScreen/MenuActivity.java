package com.example.world.MenuScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.world.AbstractActivity;
import com.example.world.Client.AllMessages;
import com.example.world.Client.Client;
import com.example.world.Client.SendMessage;
import com.example.world.GameActivity.WeitingScreenFragment;
import com.example.world.MenuScreen.WelcomingFragments.WelcomingActivity;
import com.example.world.R;
import com.example.world.Screen;
import com.example.world.Sound.Pool;

public class MenuActivity extends AbstractActivity {
    private static final String TAG = "MenuActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Client client = new Client();
        Client.connectToLastIp();
        showConnectToServerFragment();
    }

    public void startTutorial(View view){
        tutorial();
    }
    private void tutorial() {
        Intent menuIntent = new Intent(MenuActivity.this , WelcomingActivity.class);
        startActivity(menuIntent);
        finish();
    }

    public void showConnectToServerFragment(){
        openFragment(new ConnectToServerFragment(), R.id.menu_fragment);
    }
    public void showMenuFragment(){
        openFragment(new MenuFragment(), R.id.menu_fragment);
    }

    // called, when the connect button is clicked, this will try to connect to server
    // with ip set in EditText with id edit_ip
    public void connect(View view) {
        String ip = ((EditText)findViewById(R.id.edit_ip)).getText().toString();
        if(ip.length() < 11 || ip.length() > 14){
            Screen.info("Too short ip", 0);
            return;
        }
        Client.ip = ip;

        int port = 0;
        try{
            if(((EditText)findViewById(R.id.port)).getText().toString().length() > 0)
                port = Integer.parseInt(((EditText)findViewById(R.id.port)).getText().toString());
            else Screen.info("Port can not be negative number", 0);
        }catch (Exception e){
            Screen.info("Wrong port number", 0);
        }
        Client.port = port;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Client.connect();
            }
        }).start();
    }

    // FRAGMENT MENU ----------------------------------------------------------

    public void NewGame(View view){
        AllMessages.findMatch();
        openFragment(new WeitingScreenFragment(), R.id.menu_fragment);

    }
    public void Character(View view){
        float volume = 1;
        Pool.playMenuSound(1, volume);
    }
    public void Stats(View view){
        float volume = 1;
        Pool.playMenuSound(2, volume);
    }
    public void StartSettings(View view){
        float volume = 1;
        Pool.playMenuSound(3, volume);
    }
    public void About(View view){
        float volume = 1;
        Pool.playMenuSound(4, volume);
    }
}