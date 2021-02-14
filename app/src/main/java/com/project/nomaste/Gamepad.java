package com.project.nomaste;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.RequiresApi;

public class Gamepad extends Activity {
    AutoCompleteTextView autoCTVRobots;
    AutoCompleteTextView autoCTVSpeeds;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamepad);

        autoCTVRobots = findViewById(R.id.robotsGamepadList);
        String []robotsIds = {"Robot id 1","Robot id 2","Robot id 3","Robot id 4"};
        ArrayAdapter arrayAdapterRobots = new ArrayAdapter(this,R.layout.list_robots,robotsIds);
        // to make default value
        autoCTVRobots.setText(arrayAdapterRobots.getItem(0).toString(),false);

        autoCTVRobots.setAdapter(arrayAdapterRobots);

        autoCTVRobots = findViewById(R.id.speedsGamepadList);
        String []speeds = {"Slow","High"};
        ArrayAdapter arrayAdapterSpeeds = new ArrayAdapter(this,R.layout.list_robots,speeds);
        // to make default value
        autoCTVRobots.setText(arrayAdapterSpeeds.getItem(0).toString(),false);

        autoCTVRobots.setAdapter(arrayAdapterSpeeds);
    }
}
