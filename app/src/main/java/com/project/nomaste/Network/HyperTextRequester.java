package com.project.nomaste.Network;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;


public class HyperTextRequester {
    final static String FIREBASE_REALTIME_DATABASE_URL =
            "https://nomaste-app.firebaseio.com/";

    /**
     * Builds the URL used to query Firebase.
     *
     * @param searchQuery The keyword that will be queried for.
     * @return The URL to use to query the Firebase server.
     */
    public static URL buildUrl(String searchQuery) {
        Uri builtUri = Uri.parse(FIREBASE_REALTIME_DATABASE_URL).buildUpon()
                .appendPath(searchQuery)
                .build();
        Log.i("URL", "buildUrl: "+builtUri.toString());
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static void postDataFromHttpURL(URL url, String data) throws IOException{
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        try {
        String content = "body=" + URLEncoder.encode(data,"UTF-8");
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        OutputStream out = new BufferedOutputStream(conn.getOutputStream());

        out.write(content.getBytes());
        out.flush();
        out.close();

        } finally {
            if(conn!=null)
                conn.disconnect();
        }
    }

    public static void patchDataFromHttpURL(URL url, String data) throws IOException{
        Log.i("Patch", "patchDataFromHttpURL: "+url.toString());
        Log.i("Patch", "patchDataFromHttpURL: "+data);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        try {
            //String content = "body=" + URLEncoder.encode(data,"UTF-8");
            conn.setRequestMethod("PATCH");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            OutputStream out = conn.getOutputStream();
            byte[] input = data.getBytes("utf-8");
            out.write(input, 0, input.length);
            //out.write(data.getBytes());
            //out.flush();
            System.out.println(conn.getResponseCode() + " " + conn.getResponseMessage());
            out.close();

        } finally {
            if(conn!=null)
                conn.disconnect();
        }
    }
}
