package com.example.world.menuScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.world.AbstractActivity;
import com.example.world.client.Client;
import com.example.world.gameActivity.GameActivity;
import com.example.world.gameActivity.WaitingScreenFragment;
import com.example.world.menuScreen.welcomingFragments.WelcomingActivity;
import com.example.world.R;
import com.example.world.Screen;
import com.example.world.sound.Pool;

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

    public void startTutorial(View view) {
        startTutorial();
    }

    private void startTutorial() {
        Intent menuIntent = new Intent(MenuActivity.this, WelcomingActivity.class);
        startActivity(menuIntent);
        finish();
    }

    public void showConnectToServerFragment() {
        openFragment(new ConnectToServerFragment(), R.id.menu_fragment);
    }

    public void showMenuFragment() {
        openFragment(new MenuFragment(this), R.id.menu_fragment);
    }

    // called, when the connect button is clicked, this will try to connect to
    // server
    // with ip set in EditText with id edit_ip
    public void connect(View view) {
        if (!getIpAddress())
            return;

        if (!getPortNumber())
            return;

        new Thread(() -> Client.connect())
                .start();
    }

    private boolean getIpAddress() {
        String ip = ((EditText) findViewById(R.id.edit_ip)).getText().toString();
        if (!isValidIpAddress(ip)) {
            Screen.info("Too short ip.", 0);
            return false;
        }
        Client.ip = ip;
        return true;
    }

    private boolean getPortNumber() {
        int port = 0;
        try {
            String portString = ((EditText) findViewById(R.id.port)).getText().toString();
            port = Integer.parseInt(portString);
            if (port <= 1024) {
                Screen.info("Port must be bigger than 1024.", 0);
                return false;
            }
        } catch (Exception e) {
            Screen.info("Wrong port number format.", 0);
            return false;
        }
        Client.port = port;
        return true;
    }

    private boolean isValidIpAddress(String ip) {
        // Check for IPv4 format
        String ipv4Pattern = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    
        // Check for IPv6 format
        String ipv6Pattern = "^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$";
    
        return ip.matches(ipv4Pattern) || ip.matches(ipv6Pattern);
    }
    
    // FRAGMENT MENU ----------------------------------------------------------

    public void NewGame(View view) {
        newGame();
    }

    public void newGame() {
        Client.sender.serverMessages.findMatch();
        openFragment(new WaitingScreenFragment(), R.id.menu_fragment);
    }

    public void Character(View view) {
        float volume = 1;
        Pool.playMenuSound(1, volume);
    }

    public void Stats(View view) {
        float volume = 1;
        Pool.playMenuSound(2, volume);
    }

    public void StartSettings(View view) {
        float volume = 1;
        Pool.playMenuSound(3, volume);
    }

    public void About(View view) {
        float volume = 1;
        Pool.playMenuSound(4, volume);
    }

    public void startGame() {
        // Create game activity intent
        Intent menuIntent = new Intent(MenuActivity.this, GameActivity.class);

        // Wait for the activity to be created
        ViewTreeObserver viewTreeObserver = getWindow().getDecorView().getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Remove the listener to avoid multiple calls
                getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(this);

                // Start the game activity
                startActivity(menuIntent);
                finish();
            }
        });
    }


    public Fragment getMenuFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.menu_fragment);
    }
}