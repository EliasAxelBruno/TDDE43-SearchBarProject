package com.example.autocomplete;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> names;


    public Adapter(@NonNull Context context, ArrayList<String> names) {
        super(context, 0, names);
        this.context = context;
        this.names = names;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {//Skapa view
        System.out.println("INSIDE");
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.activity_main ,parent,false);
        return new ListPopupWindow(context, (String) names.get(position));
    }
}
