package com.example.anroid_networking.Lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.anroid_networking.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Lab3b4Activity extends AppCompatActivity {
    private ListView listView;
    private View parentView;
    private ArrayList<Fashionn> fashionList;
    private MyFashionAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3b4);
        fashionList = new ArrayList<>();
        parentView = findViewById(R.id.parentLayout);
        listView = (ListView) findViewById(R.id.lv);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(parentView, fashionList.get(position).getName() + " => " +
                        fashionList.get(position).getMystyle().getModern(), Snackbar.LENGTH_LONG).show();
            } });
        Toast toast = Toast.makeText(getApplicationContext(), R.string.app_name, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0); toast.show();
    }
}