package com.joel.project_todo_list;

import java.util.ArrayList;
import java.util.List;

public class TodoLists{

    ArrayList<TodoModel> allTodos;

    public TodoLists(ArrayList<TodoModel> allTodos){
        this.allTodos = allTodos;
    }

    public static class TodoModel {
        private String date;
        private String noteTitle;
        private String noteBody;


        public TodoModel(String date, String noteTitle, String noteBody){
            this.date = date;
            this.noteTitle = noteTitle;
            this.noteBody = noteBody;
        }


        public String getDate() {
            return date;
        }

        public String getNoteTitle() {
            return noteTitle;
        }

        public String getNoteBody() {
            return noteBody;
        }
    }

}