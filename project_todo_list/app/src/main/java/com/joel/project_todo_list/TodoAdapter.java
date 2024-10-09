package com.joel.project_todo_list;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoHolder> {
    private ArrayList<TodoLists.TodoModel> todoList;
    private Context context;
    ButtonDeleteListener deleteListener;
    ButtonEditListener editListener;
    TodoViewListener todoViewListener;

    public void setEditListener(ButtonEditListener editListener){
        this.editListener = editListener;
    }

    public void setDeleteListener(ButtonDeleteListener deleteListener){
        this.deleteListener = deleteListener;
    }

    public void setViewTodoListener(TodoViewListener todoViewListener){
        this.todoViewListener = todoViewListener;
    }

    public TodoAdapter(Context context, ArrayList<TodoLists.TodoModel> todoList){

        this.todoList = todoList;
        this.context = context;
    }

    @NonNull
    @Override
    public TodoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.todo_item,parent,false);
        return new TodoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoHolder holder, int position) {

        TextView dateView = holder.noteDateView;
        TextView noteTitleView = holder.noteTitleView;
        TextView noteBodyView = holder.noteBodyView;
        ImageView buttonDeleteNote = holder.buttonDeleteNote;
        ImageView buttonEditNote = holder.buttonEditNote;
        CardView singleTodo = holder.singleTodo;

        buttonDeleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteListener.todoDeleteClicked(holder.getAdapterPosition());
            }
        });

        buttonEditNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editListener.todoEditClicked(holder.getAdapterPosition());
            }
        });

        singleTodo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                buttonEditNote.setVisibility(View.VISIBLE);
                buttonDeleteNote.setVisibility(View.VISIBLE);
                return true;
            }
        });

        singleTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoViewListener.viewTodo(holder.getAdapterPosition());
            }
        });

        String todoTitle  = todoList.get(position).getNoteTitle();
        String todoDate = todoList.get(position).getDate();
        String todoBody = todoList.get(position).getNoteBody();

        if (todoTitle.split("").length > 10){
            todoTitle = todoTitle.substring(0,10);
            todoTitle += "...";
        }
        if(todoBody.split("").length > 20) {
            todoBody = todoBody.substring(0, 20);
            todoBody += "...";
        }


        dateView.setText(todoDate);
        noteTitleView.setText(todoTitle);
        noteBodyView.setText(todoBody);
    }

    @Override
    public int getItemCount() {
        return todoList.size() ;
    }

    public class TodoHolder extends RecyclerView.ViewHolder{
        TextView noteTitleView,  noteBodyView, noteDateView;
        ImageView buttonEditNote, buttonDeleteNote;
        CardView singleTodo;
        public TodoHolder(@NonNull View itemView) {
            super(itemView);

            noteTitleView = itemView.findViewById(R.id.noteTitle);
            noteBodyView = itemView.findViewById(R.id.noteBody);
            noteDateView = itemView.findViewById(R.id.noteDateView);
            buttonEditNote = itemView.findViewById(R.id.iconEditNote);
            buttonDeleteNote = itemView.findViewById(R.id.iconDeleteNote);
            singleTodo = itemView.findViewById(R.id.singleTodo);

        }
    }
}
