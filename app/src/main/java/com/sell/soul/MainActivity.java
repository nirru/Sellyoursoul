package com.sell.soul;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = MainFragment.newInstance("","");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content, fragment);
//                fragmentTransaction.addToBackStack(null);/**Enable this in fragment call not in activity*/
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        playAudio();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopAudio();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopAudio();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopAudio();
    }

    public void playAudio() {
        Intent objIntent = new Intent(MainActivity.this, PlayAudio.class);
        startService(objIntent);
    }
    public void stopAudio() {
        Intent objIntent = new Intent(MainActivity.this, PlayAudio.class);
        stopService(objIntent);
    }


}
