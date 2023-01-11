package com.example.autocomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<String> dataSet = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        MyThread myThread = new MyThread();

        SearchBar adapter = new SearchBar(this);

        TextView textView = findViewById(R.id.searchBar);
        myThread.start();
    }
}
class MyThread extends Thread {
    public void run() {
        // perform background task here
    }
}