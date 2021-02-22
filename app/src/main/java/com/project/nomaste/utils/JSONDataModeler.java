package com.project.nomaste.utils;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.project.nomaste.Model.Entity.Robot;

import java.util.LinkedList;

public class JSONDataModeler {
    private Gson g;
    public static LinkedList<Robot> getRobots(String content){
        LinkedList<Robot> robots = new LinkedList<>();
        Gson g = new GsonBuilder().create();
        try{
            JsonArray data = g.fromJson(content, JsonArray.class);
            for(int i = 0; i < data.size();i++){
                robots.add(new Robot(data.get(i).getAsJsonObject().get("Speed").getAsInt(),
                        data.get(i).getAsJsonObject().get("id").getAsInt()));
            }
        }catch (JsonSyntaxException | JsonIOException | NullPointerException  e){
            e.printStackTrace();
        }
        return  robots;
    }

    public static String createBodyManualRequest(int id, String action){
        String data = "{\"Button\":\""+action+"\",\"Id\":"+id+"}";
        JsonObject obj = new JsonObject();
        obj.addProperty("Button", action);
        obj.addProperty("Id", id);
        return obj.toString();
    }
}
