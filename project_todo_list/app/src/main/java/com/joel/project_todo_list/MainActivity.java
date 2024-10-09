package com.joel.project_todo_list;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements ButtonDeleteListener,
        ButtonEditListener,
        TodoViewListener{

    ArrayList<TodoLists.TodoModel> todoList;
    RecyclerView mainView;
    ImageView buttonCreateNewNote, options, buttonDeleteTodo, buttonEditTodo;
    TodoAdapter todoAdapter;

    SharedPreferences sharedPreferences;
    Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gson = new Gson();
        sharedPreferences = getSharedPreferences("saveData", Context.MODE_PRIVATE);
        todoList = new ArrayList<>();

        mainView = findViewById(R.id.recyclerTodoList);
        buttonCreateNewNote = findViewById(R.id.iconAddNewTodo);


        todoAdapter = new TodoAdapter(this,todoList);
        todoAdapter.setEditListener(this);
        todoAdapter.setDeleteListener(this);
        todoAdapter.setViewTodoListener(this);

        buttonCreateNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCreateNewTodo = new Intent(MainActivity.this,AddNoteActivity.class);
                intentCreateNewTodo.putExtra("submissionType","Add");
                startActivity(intentCreateNewTodo);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
       String allSavedTodoData = sharedPreferences.getString("allTodoData","null");

       if (!allSavedTodoData.equals("null")){
           TodoLists todoLists = gson.fromJson(allSavedTodoData,TodoLists.class);
           todoList = todoLists.allTodos;
       }

       if(getIntent().getBooleanExtra("newTodo",false)){
            todoHandler();
       }

        todoAdapter = new TodoAdapter(this,todoList);
        todoAdapter.setEditListener(this);
        todoAdapter.setDeleteListener(this);
        todoAdapter.setViewTodoListener(this);

        mainView.setAdapter(todoAdapter);
        mainView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void todoHandler(){
        int index = getIntent().getIntExtra("editedTodoIndex",0);

        TodoLists.TodoModel model = new TodoLists.TodoModel(
                getIntent().getStringExtra("submittedValueDate"),
                getIntent().getStringExtra("submittedValueTitle"),
                getIntent().getStringExtra("submittedValueBody")
        );

        if (getIntent().getStringExtra("submissionType").equals("Edit")){
            todoList.set(index,model);
            saveAllData();
        }else if(getIntent().getStringExtra("submissionType").equals("Add")){
            todoList.add(model);
            saveAllData();
        }
        getIntent().putExtra("newTodo",false);
    }

    public void saveAllData(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        TodoLists todoLists = new TodoLists(todoList);
        String todoListsString = gson.toJson(todoLists);
        editor.putString("allTodoData",todoListsString);
        editor.apply();
    }

    public void deleteTodoHandler(int index){
        AlertDialog deleteTodoAlert;

        deleteTodoAlert = new AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage("Are you sure?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        todoList.remove(index);
                        mainView.setAdapter(todoAdapter);
                        mainView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        saveAllData();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create();
        deleteTodoAlert.show();
    }

    @Override
    public void todoDeleteClicked(int index) {
        deleteTodoHandler(index);
    }

    @Override
    public void todoEditClicked(int index) {
        Intent intentEditTodo = new Intent(MainActivity.this,AddNoteActivity.class);
        intentEditTodo.putExtra("submissionType","Edit");
        intentEditTodo.putExtra("modifyTodoTitle",todoList.get(index).getNoteTitle());
        intentEditTodo.putExtra("modifyTodoBody",todoList.get(index).getNoteBody());
        intentEditTodo.putExtra("modifyTodoDate",todoList.get(index).getDate());
        intentEditTodo.putExtra("modifyTodoIndex",index);
        startActivity(intentEditTodo);
    }

    @Override
    public void viewTodo(int index) {
        Intent intent = new Intent(MainActivity.this,ActivityNoteView.class);
        intent.putExtra("todoTitle",todoList.get(index).getNoteTitle());
        intent.putExtra("todoBody",todoList.get(index).getNoteBody());
        intent.putExtra("todoDate",todoList.get(index).getDate());
        startActivity(intent);
    }
}