package ua.kiev.netmaster.mysalarycalc.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.kiev.netmaster.mysalarycalc.R;

/**
 * Created by ПК on 29.10.2015.
 */
public class Tab1  extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.poststab,container,false);
        return v;
    }
}