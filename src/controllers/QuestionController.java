package controllers;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import interfaces.IQuestion;
import utils.QuestionFileReader;
import views.MainMenuView;

import java.util.List;

public class QuestionController {

    private int questionIndex = 0;
    private final List<IQuestion> questions;
    private final Scene scene;
    private final Parent root;
    private final int availableScore;
    private final Label scoreLabel;
    private final MainMenuView mainMenuView;

    private int score;


    public QuestionController(Scene scene, Parent root) throws Exception {
        this.root = root;

        questions = new QuestionFileReader().getQuestions();
        this.scene = scene;
        this.availableScore = questions.size();
        this.scoreLabel = ((Label) scene.lookup("#labelScore"));
        updateScoreBoard();

        mainMenuView = new MainMenuView();
        showMainMenu();
    }

    public void showMainMenu() {
        mainMenuView.show(scene, (addPoint) -> {
            execute();
        });
    }

    private void updateScoreBoard() {
        scoreLabel.setText(score + "/" + availableScore);
    }

    private void showEndScreen() {
        ((Label) scene.lookup("#labelQuestion")).setText("Game Over");
    }

    public void execute() {
        if (questionIndex == questions.size() - 1){
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


