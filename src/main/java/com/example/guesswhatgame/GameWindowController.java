package com.example.guesswhatgame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GameWindowController {

    @FXML
    private Label numberOfAttempts;
    @FXML
    private Label answer;
    @FXML
    private Label moreThan;
    @FXML
    private Label lessThan;

    @FXML
    private Button check;

    @FXML
    private TextField guesses;

    private int min = 0;
    private int max = 10001;
    private int randomNum = 0;
    private int numOfAtt = 0;

    public void setNumbOfAtt(int value) {
        this.numOfAtt = value;
        numberOfAttempts.setText("You have " + value + " attempts left!");
    }

    public void changeNumbOfAtt() {
        numberOfAttempts.setText("You have " + numOfAtt + " attempts left!");
    }

    public void setRandomNum(int value) {
        this.randomNum = value;
        answer.setText("Number was: " + value);
    }

    public void check() {
        if (validate()) {
            if (Integer.parseInt(guesses.getText()) == randomNum) {
                numberOfAttempts.setText("YOU WON!!!");
            } else if (Integer.parseInt(guesses.getText()) < randomNum) {
                if (min < Integer.parseInt(guesses.getText())) {
                    min = Integer.parseInt(guesses.getText());
                    moreThan.setText("Number is more than: " + min+"  ");
                    numOfAtt--;
                    changeNumbOfAtt();
                } else {
                    moreThan.setText("Number is more than: " + min+"  ");
                    numOfAtt--;
                    changeNumbOfAtt();
                }
            } else if (Integer.parseInt(guesses.getText()) > randomNum) {
                if (max > Integer.parseInt(guesses.getText())) {
                    max = Integer.parseInt(guesses.getText());
                    lessThan.setText("Number is less than: " + max+"  ");
                    numOfAtt--;
                    changeNumbOfAtt();
                } else {
                    lessThan.setText("Number is less than: " + max+"  ");
                    numOfAtt--;
                    changeNumbOfAtt();
                }
            }
        } else {
            numberOfAttempts.setText("You lose!!!");
            answer.setVisible(true);
        }
    }

    public boolean validate() {
        if (numOfAtt != 1) {
            return true;
        } else return false;
    }
}
