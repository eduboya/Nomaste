package com.project.nomaste;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        setContentView(R.layout.content_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        /*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("button");
                myRef.setValue("pressed");
                Snackbar.make(view, "Funciones se iran añadiendo", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

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
}
