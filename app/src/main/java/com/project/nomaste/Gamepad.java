package com.project.nomaste;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.project.nomaste.Model.Entity.Robot;
import com.project.nomaste.Network.FirebaseConnector;
import com.project.nomaste.Network.HyperTextRequester;
import com.project.nomaste.utils.JSONDataModeler;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class Gamepad extends Activity {

    AutoCompleteTextView autoCTVRobots;
    AutoCompleteTextView autoCTVSpeeds;
    public String pressedButton;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamepad);

        autoCTVRobots = findViewById(R.id.robotsGamepadList);
        SharedPreferences prefe = getApplicationContext().getSharedPreferences("datos", Context.MODE_PRIVATE);
        LinkedList<Robot> robots = JSONDataModeler.getRobots(prefe.getString("robot",""));


        ArrayAdapter arrayAdapterRobots = new ArrayAdapter(this,R.layout.list_robots,getRobotIds(robots));
        // to make default value
        autoCTVRobots.setText(arrayAdapterRobots.getItem(0).toString(),false);

        autoCTVRobots.setAdapter(arrayAdapterRobots);

        autoCTVRobots = findViewById(R.id.speedsGamepadList);
        String []speeds = {"None","Slow","High"};
        ArrayAdapter arrayAdapterSpeeds = new ArrayAdapter(this,R.layout.list_robots,speeds);
        // to make default value
        autoCTVRobots.setText(arrayAdapterSpeeds.getItem(0).toString(),false);

        autoCTVRobots.setAdapter(arrayAdapterSpeeds);
        final Button down = findViewById(R.id.down_button);
        down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pressedButton="Down";
                Toast.makeText(getApplicationContext(),
                        "pressed button", Toast.LENGTH_SHORT);
                //ToDo: get selected robot id
                URL patchUrl = HyperTextRequester.buildUrl("Manual/1.json");
                new sendManualFirebaseTask().execute(patchUrl);
            }
        });
        final Button up = findViewById(R.id.up_button);
        up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pressedButton="Up";
                System.out.println(HyperTextRequester.buildUrl("Manual/1.json").toString());
                System.out.println(JSONDataModeler.createBodyManualRequest(1,pressedButton));
                Toast.makeText(getApplicationContext(),
                        "pressed button", Toast.LENGTH_SHORT);
                //ToDo: get selected robot id
                URL patchUrl = HyperTextRequester.buildUrl("Manual/1.json");
                new sendManualFirebaseTask().execute(patchUrl);
            }
        });
        final Button right = findViewById(R.id.right_button);
        right.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pressedButton="Right";
                Toast.makeText(getApplicationContext(),
                        "pressed button", Toast.LENGTH_SHORT);
                //ToDo: get selected robot id
                URL patchUrl = HyperTextRequester.buildUrl("Manual/1.json");
                new sendManualFirebaseTask().execute(patchUrl);
            }
        });
        final Button left = findViewById(R.id.left_button);
        left.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pressedButton="Left";
                //ToDo: get selected robot id
                URL patchUrl = HyperTextRequester.buildUrl("Manual/1.json");
                new sendManualFirebaseTask().execute(patchUrl);
            }
        });
    }
    private String [] getRobotIds(LinkedList<Robot> robots){
        if(robots.size()>0){
            String []robotsIds = new String[robots.size()];
            for (int i=0; i< robots.size();i++) {
                robotsIds[i] = "Robot id "+robots.get(i).id;
            }
            return robotsIds;
        }
        return new String[]{"No Robot"};
    }
    public class sendManualFirebaseTask extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //mLoadingIndicator.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(URL... params) {
            URL patchUrl = params[0];
            //ToDo: get selected id
            String body = JSONDataModeler.createBodyManualRequest(1,pressedButton);
            try {
                HyperTextRequester.patchDataFromHttpURL(patchUrl, body);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return body;
        }
    }
}
