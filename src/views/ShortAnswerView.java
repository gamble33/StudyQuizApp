package views;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import models.ShortAnswerQuestion;
import interfaces.AnswerCallback;
import interfaces.QuestionView;
import javafx.scene.Scene;
import utils.TimeUtils;

public class ShortAnswerView extends View implements QuestionView {

    private final ShortAnswerQuestion question;
    private AnswerCallback callback;

    public ShortAnswerView(ShortAnswerQuestion question) {
        this.question = question;
    }

    private void onAnswer(boolean correctAnswer) {
        TimeUtils.setTimeout(() -> {
            rollbackShow();
            callback.run(correctAnswer);
        }, 200);
    }

    @Override
    public void show(Scene scene, AnswerCallback callback) {
        this.callback = callback;
        this.vbox = (VBox) scene.lookup("#vboxAnswers");
        ((Label) scene.lookup("#labelQuestion")).setText(question.getQuestion());
        TextField textField = new TextField();
        textField.setMaxSize(200, 200);
        Button submitButton = new Button();
        submitButton.setText("Submit Answer");
        submitButton.setOnMouseClicked(e -> {
            boolean correctAnswer = question.checkAnswer(textField.getText());
            if (correctAnswer) {
                submitButton.getStyleClass().add("buttonCorrect");
            } else {
                submitButton.getStyleClass().add("buttonIncorrect");
            }
            onAnswer(correctAnswer);
        });
        vbox.getChildren().addAll(textField, submitButton);
    }

}
