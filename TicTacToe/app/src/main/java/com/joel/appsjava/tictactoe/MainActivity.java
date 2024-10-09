package com.joel.appsjava.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    boolean win;
    char[] squares;
    char playerRole,CPURole;
    List<Integer> usableSquares;
    Button square0, square1, square2, square3, square4, square5, square6, square7, square8,
           resetButton, playerRoleX, playerRoleO;
    List<Button> listOfButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        win = false;
        char winner;
        squares = new char[9];
        usableSquares = new ArrayList<>();
        listOfButtons = new ArrayList<>();

        getAllElements();
        disableAll();
        addAllButtonsToList();

    }

    public void choice0Clicked(View view){
        square0.setText(String.valueOf(playerRole));
        squares[0] = square0.getText().charAt(0);
        square0.setEnabled(false);
        updateIndexes();
        cpuPlay();
        checkWin();
    }

    public void choice1Clicked(View view){
        square1.setText(String.valueOf(playerRole));
        squares[1] = square0.getText().charAt(0);
        square1.setEnabled(false);
        updateIndexes();
        cpuPlay();
        checkWin();
    }

    public void choice2Clicked(View view){
        square2.setText(String.valueOf(playerRole));
        squares[2] = square0.getText().charAt(0);
        square2.setEnabled(false);
        updateIndexes();
        cpuPlay();
        checkWin();
    }

    public void choice3Clicked(View view){
        square3.setText(String.valueOf(playerRole));
        squares[3] = square0.getText().charAt(0);
        square3.setEnabled(false);
        updateIndexes();
        cpuPlay();
        checkWin();
    }

    public void choice4Clicked(View view){
        square4.setText(String.valueOf(playerRole));
        squares[4] = square0.getText().charAt(0);
        square4.setEnabled(false);
        updateIndexes();
        cpuPlay();
        checkWin();
    }

    public void choice5Clicked(View view){
        square5.setText(String.valueOf(playerRole));
        squares[5] = square0.getText().charAt(0);
        square5.setEnabled(false);
        updateIndexes();
        cpuPlay();
        checkWin();
    }

    public void choice6Clicked(View view){
        square6.setText(String.valueOf(playerRole));
        squares[6] = square0.getText().charAt(0);
        square6.setEnabled(false);
        updateIndexes();
        cpuPlay();
        checkWin();
    }

    public void choice7Clicked(View view){
        square7.setText(String.valueOf(playerRole));
        squares[7] = square0.getText().charAt(0);
        square7.setEnabled(false);
        updateIndexes();
        cpuPlay();
        checkWin();
    }

    public void choice8Clicked(View view){
        square8.setText(String.valueOf(playerRole));
        squares[8] = square0.getText().charAt(8);
        square8.setEnabled(false);
        updateIndexes();
        cpuPlay();
        checkWin();
    }

    public void checkWin(){

    }

    public void enableAll(){
        square0.setEnabled(true);
        square1.setEnabled(true);
        square2.setEnabled(true);
        square3.setEnabled(true);
        square4.setEnabled(true);
        square5.setEnabled(true);
        square6.setEnabled(true);
        square7.setEnabled(true);
        square8.setEnabled(true);
    }

    public void disableAll(){
        square0.setEnabled(false);
        square1.setEnabled(false);
        square2.setEnabled(false);
        square3.setEnabled(false);
        square4.setEnabled(false);
        square5.setEnabled(false);
        square6.setEnabled(false);
        square7.setEnabled(false);
        square8.setEnabled(false);
    }

    public void updateIndexes(){
        usableSquares.clear();

        for (int i = 0; i < squares.length; i++) {
            if (squares[i] == 0){
                usableSquares.add(i);
            }
        }
        if (usableSquares.size() == 0){
            enableAll();
            for (Button b: listOfButtons) {
                b.setText("");
            }
            disableAll();
            enableChoosing();
            squares = new char[9];
            usableSquares = new ArrayList<>();
            listOfButtons = new ArrayList<>();
        }
    }

    public void playerXSelected(View view){
        this.playerRole = 'X';
        this.CPURole = 'O';
        enableAll();
        disableChoosing();
    }

    public void playerOSelected(View view){
        this.playerRole = 'O';
        this.CPURole = 'X';
        enableAll();
        disableChoosing();
    }

    public void cpuPlay(){
        Random rand = new Random();

        if (usableSquares.size() != 0){
            int cpuChoice = rand.nextInt(usableSquares.size());

//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                System.err.println(e.getMessage());
//            }

            listOfButtons.get(usableSquares.get(cpuChoice)).setText(CPURole);
            squares[usableSquares.get(cpuChoice)] = listOfButtons.get(usableSquares.get(cpuChoice)).getText().charAt(8);
            enableAll();
        }

//        TODO

    }

    public void addAllButtonsToList(){
        listOfButtons.add(0,square0);
        listOfButtons.add(0,square0);
        listOfButtons.add(0,square0);
        listOfButtons.add(0,square0);
        listOfButtons.add(0,square0);
        listOfButtons.add(0,square0);
        listOfButtons.add(0,square0);
        listOfButtons.add(0,square0);
        listOfButtons.add(0,square0);
    }

    public void getAllElements(){
        square0 = findViewById(R.id.choiceButton0);
        square1 = findViewById(R.id.choiceButton1);
        square2 = findViewById(R.id.choiceButton2);
        square3 = findViewById(R.id.choiceButton3);
        square4 = findViewById(R.id.choiceButton4);
        square5 = findViewById(R.id.choiceButton5);
        square6 = findViewById(R.id.choiceButton6);
        square7 = findViewById(R.id.choiceButton7);
        square8 = findViewById(R.id.choiceButton8);
        resetButton = findViewById(R.id.resetButton);
        playerRoleX = findViewById(R.id.roleOptionX);
        playerRoleO = findViewById(R.id.roleOptionO);
    }

    public void resetButtonClicked(View view){
        enableAll();
        for (Button b: listOfButtons) {
            b.setText("");
        }
        disableAll();
        enableChoosing();
        squares = new char[9];
        usableSquares = new ArrayList<>();
        listOfButtons = new ArrayList<>();
    }

    public void enableChoosing(){
        playerRoleO.setEnabled(true);
        playerRoleX.setEnabled(true);
    }

    public void disableChoosing(){
        playerRoleO.setEnabled(false);
        playerRoleX.setEnabled(false);
    }
}