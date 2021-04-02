package com.project.nomaste.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.nomaste.R;

public class SmartCleaningFragment extends Fragment {
    public SmartCleaningFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_smartcleaning, container, false);
        // Get a reference to the ImageView in the fragment Layout

        return rootView;
    }
}
