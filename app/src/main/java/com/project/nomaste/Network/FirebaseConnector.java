package com.project.nomaste.Network;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.project.nomaste.Model.Entity.Robot;

import java.util.LinkedList;

public class FirebaseConnector {
    final static String FIREBASE_REALTIME_DATABASE_URL =
            "https://nomaste-app.firebaseio.com/";
    FirebaseDatabase database;
    public FirebaseConnector(){
        database = FirebaseDatabase.getInstance(FIREBASE_REALTIME_DATABASE_URL);
        Log.i("ConnectionFirebase","Connected");
        DatabaseReference myRef = database.getReference("user");
        myRef.setValue("logged");
    }
    public LinkedList<Robot> getRobots(){
        LinkedList<Robot> robots = new LinkedList<>();
        DatabaseReference myRef = database.getReference("Robots");
        String content = myRef.getDatabase().toString();
        Log.i("ConnectionFirebase",content);
        Gson g = new GsonBuilder().create();
        try{
            JsonObject data = g.fromJson(content, JsonObject.class);
            JsonArray robotsData = new JsonArray();
            for(int i = 0; i < robotsData.size();i++){
                robots.add(new Robot(robotsData.get(i).getAsJsonObject().get("Speed").getAsInt(),(i+1)));
            }
        }catch (JsonSyntaxException | JsonIOException | NullPointerException  e){
            e.printStackTrace();
        }
        return  robots;
    }
}
