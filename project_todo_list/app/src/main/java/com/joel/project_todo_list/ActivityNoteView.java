package com.joel.project_todo_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityNoteView extends AppCompatActivity {

    TextView todoTitleView, todoBodyView, todoDateView;
    ImageView goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view);
        todoBodyView = findViewById(R.id.todoBodyViewer);
        todoTitleView = findViewById(R.id.todoTitleViewer);
        todoDateView = findViewById(R.id.todoDateViewer);
        goBack = findViewById(R.id.goBackFromView);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityNoteView.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        todoTitleView.setText(getIntent().getStringExtra("todoTitle"));
        todoBodyView.setText(getIntent().getStringExtra("todoBody"));
        todoDateView.setText(getIntent().getStringExtra("todoDate"));

    }
}