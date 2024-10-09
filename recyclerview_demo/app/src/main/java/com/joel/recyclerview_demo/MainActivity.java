package com.joel.recyclerview_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity{

    private RecyclerView contactListView;
    private String[] contactNames, contactNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactListView = findViewById(R.id.recyclerViewContacts);
        contactListView.setLayoutManager(new LinearLayoutManager(this));
        contactNames = getResources().getStringArray(R.array.contactNames);
        contactNumbers = getResources().getStringArray(R.array.contactNumbers);

        ContactAdapter contactAdapter = new ContactAdapter(this,contactNames,contactNumbers);
        contactListView.setAdapter(contactAdapter);
    }
}