package com.joel.project_todo_list;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OptionButtonsImplementer extends AppCompatActivity implements ButtonEditListener,ButtonDeleteListener{
    ArrayList<TodoLists.TodoModel> todoList;
    Context context;

    public OptionButtonsImplementer(ArrayList<TodoLists.TodoModel> todoList, Context context){
        this.todoList = todoList;
        this.context = context;
    }

    @Override
    public void todoDeleteClicked(int index) {
        todoList.remove(index);
    }

    @Override
    public void todoEditClicked(int index) {
        Intent intentEditTodo = new Intent(context,AddNoteActivity.class);
        intentEditTodo.putExtra("submissionType","Edit");
        intentEditTodo.putExtra("editedTodoIndex",index);
        startActivity(intentEditTodo);
    }
}
