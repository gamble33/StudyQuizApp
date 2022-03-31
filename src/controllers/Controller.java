package controllers;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import interfaces.IQuestion;
import models.MultipleChoiceQuestion;
import utils.QuestionDatabaseReader;
import utils.QuestionFileReader;
import views.MultipleChoiceView;

import java.util.List;

public class Controller {

    private int questionIndex = 0;
    private final List<IQuestion> questions;
    private final Scene scene;
    private final Parent root;
    private final int availableScore;
    private final Label scoreLabel;

    private int score;


    public Controller(Scene scene, Parent root) throws Exception {
        this.root = root;

        questions = new QuestionFileReader().getQuestions();
        this.scene = scene;
        this.availableScore = questions.size();
        this.scoreLabel = ((Label) scene.lookup("#labelScore"));
        updateScoreBoard();
    }

    private void updateScoreBoard() {
        scoreLabel.setText(score + "/" + availableScore);
    }

    private void showEndScreen() {
        ((Label) scene.lookup("#labelQuestion")).setText("Game Over");
    }

    public void execute() {
        if (questionIndex == questions.size()){
            showEndScreen();
            return;
        }

        //questions.get(questionIndex).getView().show(sc)
        questions.get(questionIndex).getView().show(scene, (addPoint) -> {
            if (addPoint) score += 1;
            updateScoreBoard();
            questionIndex++;
            execute();
        });
    }

}


