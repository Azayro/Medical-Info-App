package com.example.medicalinfo;

import com.example.medicalinfo.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
 
public class MainScreenFragment extends Fragment 
{
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) 
    {
 
        View rootView = inflater.inflate(R.layout.fragment_main_screen, container, false);
         
        return rootView;
    }
}