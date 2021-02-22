package com.project.nomaste;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.project.nomaste.Network.HyperTextRequester;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        //set content view AFTER ABOVE sequence (to avoid crash)
        this.setContentView(R.layout.content_main);
        // Create MenuNav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
        makeFirebaseSearchQuery();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Toast.makeText(getApplicationContext(),
                "Selected Item", Toast.LENGTH_SHORT);
        switch (menuItem.getItemId()){
            case R.id.setting:
                Toast.makeText(getApplicationContext(),
                        "Settings View", Toast.LENGTH_SHORT);
                break;
            case R.id.schedule:
                Toast.makeText(getApplicationContext(),
                        "Calendario View", Toast.LENGTH_SHORT);
                break;
            case R.id.smart_cleaning:
                Toast.makeText(getApplicationContext(),
                        "S-Cleaning", Toast.LENGTH_SHORT);
                break;
            case R.id.home:
                Toast.makeText(getApplicationContext(),
                        "Home View", Toast.LENGTH_SHORT);
                break;
            case R.id.gamepad:
                Intent mainIntent = new Intent(MainActivity.this,Gamepad.class);
                MainActivity.this.startActivity(mainIntent);
                Toast.makeText(getApplicationContext(),
                        "Gamepad View", Toast.LENGTH_SHORT);
                MainActivity.this.finish();
                break;
        }
        return true;
    }

    /**
     * This method retrieves the search text from the EditText, constructs the
     * URL (using {@link HyperTextRequester) for the github repository you'd like to find, displays
     * that URL in a TextView, and finally fires off an AsyncTask to perform the GET request using
     * our {@link RobotsFirebaseTask}
     */
    private void makeFirebaseSearchQuery() {
        //String githubQuery = mSearchBoxEditText.getText().toString();
        URL searchUrl = HyperTextRequester.buildUrl("Robots.json");
        SharedPreferences prefe =getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=prefe.edit();
        editor.putString("robot", "");
        editor.commit();
        //finish();
        new RobotsFirebaseTask().execute(searchUrl);
    }
    /**
     * This method will make the View for the JSON data visible and
     * hide the error message.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showJsonDataView(String data) {
        SharedPreferences prefe =getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=prefe.edit();
        editor.putString("robot", data);
        editor.commit();
        System.out.println(data);
        Log.i("Data", "showJsonDataView: "+ data);
    }
    public class RobotsFirebaseTask extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //mLoadingIndicator.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String streamOutput = null;
            try {
                streamOutput = HyperTextRequester.getResponseFromHttpUrl(searchUrl);
                Log.i("Stream-Data",""+streamOutput);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return streamOutput;
        }
        @Override
        protected void onPostExecute(String streamOutput) {
            //mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (streamOutput != null && !streamOutput.equals("")) {
                // Call showJsonDataView if we have valid, non-null results
                showJsonDataView(streamOutput);
                //mSearchResultsTextView.setText(githubSearchResults);
            } else {
                // Call showErrorMessage if the result is null in onPostExecute
                Log.e("ERROR-Data","Not Robots gotten");
            }
        }
    }

}
