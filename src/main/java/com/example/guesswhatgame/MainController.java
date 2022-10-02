package com.example.guesswhatgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.util.Random;

public class MainController {
    Random r = new Random();

    int k = 0;
    int l = 0;

    @FXML
    private int conditions() {
        if (switcherHundred.isSelected()) {
            return 1;
        } else if (switcherThousand.isSelected()) {
            return 2;
        } else if (switcherTenThousand.isSelected()) {
            return 3;
        } else return 0;
    }

    @FXML
    private int difficulty() {
        if (switcherEasy.isSelected()) {
            return 2;
        } else if (switcherNormal.isSelected()) {
            return 0;
        } else if (switcherHard.isSelected()) {
            return -2;
        } else return -1;
    }

    @FXML
    private RadioButton switcherHundred;
    @FXML
    private RadioButton switcherThousand;
    @FXML
    private RadioButton switcherTenThousand;

    @FXML
    private RadioButton switcherEasy;
    @FXML
    private RadioButton switcherNormal;
    @FXML
    private RadioButton switcherHard;

    @FXML
    private Button launch;

    @FXML
    private Label welcomeText;
    @FXML
    private Label wrongInput;

    @FXML
    protected void onStartButtonClick() {
        if (validate()) {
            random();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("playingWindow.fxml"));
                Parent anotherRoot = loader.load();
                GameWindowController newGameController = loader.getController();
                newGameController.setNumbOfAtt(this.k);
                newGameController.setRandomNum(this.l);
                Stage gameStage = new Stage();
                gameStage.setTitle("gameWindow");
                gameStage.setScene(new Scene(anotherRoot, 300, 300));
                gameStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    protected void random() {
        int i = conditions();
        int j = difficulty();
        if (i == 1) {
            this.l = r.nextInt(1, 101);
            this.k = 7 + j;
        } else if (i == 2) {
            this.l = r.nextInt(1, 1001);
            this.k = 10 + j;
        } else if (i == 3) {
            this.l = r.nextInt(1, 10001);
            this.k = 14 + j;
        }
    }

    protected boolean validate() {
        if (conditions() == 0 && difficulty() == -1) {
            wrongInput.setText("Chose difficulty and range!");
            return false;
        } else if (difficulty() == -1) {
            wrongInput.setText("Chose difficulty!");
            return false;
        } else if (conditions() == 0) {
            wrongInput.setText("Chose range!");
            return false;
        }
        return true;
    }
}