package com.belafon.world.mobileClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public abstract class AbstractActivity extends AppCompatActivity {
    private static final String TAG = "AbstractActivity";
    private static Activity actualActivity = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActualActivity(this);
        Screen.hideBars(this);
    }

    public void openFragment(Fragment fragment, int oldFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(oldFragment, fragment);
        fragmentTransaction.commit();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (Screen.currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    public static Activity getActualActivity() {
        if(AbstractActivity.actualActivity == null)
            return null;

        synchronized (AbstractActivity.actualActivity) {
            return AbstractActivity.actualActivity;
        }
    }

    public static void setActualActivity(Activity actualActivity) {
        AbstractActivity.actualActivity = actualActivity;
    }
}