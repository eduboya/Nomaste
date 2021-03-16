package com.project.nomaste.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.project.nomaste.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    ImageView cleanCircle;
    Button cleanButton;
    //ToDo: Make change in button color
    //ToDO: Make circle change color
    public HomeFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        // Get a reference to the ImageView in the fragment Layout

        cleanButton = rootView.findViewById(R.id.auto_clean_button);
        cleanCircle = rootView.findViewById(R.id.cleanCircle);

        cleanButton.setPressed(false);
        cleanCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cleanCircle.setColorFilter(getContext().getResources().getColor(R.color.colorPrimary));
            }
        });
        return rootView;
    }
}
