package com.joel.project_todo_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddNoteActivity extends AppCompatActivity {

    EditText todoTitleInput, todoBodyInput, todoDateInput;
    Button todoSubmitButton, todoDismissButton;
    ImageView goBack;
    TodoLists.TodoModel model;
    String modifyTodoTitle, modifyTodoBody, modifyTodoDate;
    int modifyTodoIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        todoTitleInput = findViewById(R.id.inputTodoTitle);
        todoBodyInput = findViewById(R.id.inputTodoBody);
        todoDateInput = findViewById(R.id.inputTodoTime);
        todoSubmitButton = findViewById(R.id.buttonAddNewTodo);
        todoDismissButton =  findViewById(R.id.buttonDismissTodo);
        goBack = findViewById(R.id.goBack);

        if (getIntent().getStringExtra("submissionType").equals("Edit")){
            modifyTodoTitle = getIntent().getStringExtra("modifyTodoTitle");
            modifyTodoBody = getIntent().getStringExtra("modifyTodoBody");
            modifyTodoDate = getIntent().getStringExtra("modifyTodoDate");
            modifyTodoIndex = getIntent().getIntExtra("modifyTodoIndex",0);

            todoTitleInput.setText(modifyTodoTitle);
            todoBodyInput.setText(modifyTodoBody);
            todoDateInput.setText(modifyTodoDate);
        }


        todoDismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoTitleInput.setText("");
                todoDateInput.setText("");
                todoBodyInput.setText("");
            }
        });


        todoSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    submitHandler(getIntent().getStringExtra("submissionType"));
            }

        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNoteActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void submitHandler(String submissionType){
            if (checkValues()){
                Intent submittingValues = new Intent(AddNoteActivity.this, MainActivity.class);
                submittingValues.putExtra("submittedValueTitle",todoTitleInput.getText().toString());
                submittingValues.putExtra("submittedValueBody",todoBodyInput.getText().toString());
                submittingValues.putExtra("submittedValueDate",todoDateInput.getText().toString());
                submittingValues.putExtra("submissionType",submissionType);
                submittingValues.putExtra("editedTodoIndex",getIntent().getIntExtra("modifyTodoIndex",0));
                submittingValues.putExtra("newTodo",true);
                startActivity(submittingValues);
            }else{
                Toast.makeText(AddNoteActivity.this, "Please provide full information", Toast.LENGTH_SHORT).show();
            }
    }

    public boolean checkValues(){
        if (!todoTitleInput.getText().toString().equals("") &&
                !todoDateInput.getText().toString().equals("") &&
                !todoBodyInput.getText().toString().equals("")
        ){
            return true;
        }else{
            return false;
        }
    }

    public void createNewTodo(){
        String todoTitle,todoBody, todoDate;

    }
}