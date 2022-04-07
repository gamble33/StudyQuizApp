package views;

import interfaces.AnswerCallback;
import interfaces.QuestionView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import models.LabelDiagramQuestion;
import utils.TimeUtils;

import java.io.FileInputStream;

public class LabelDiagramView extends View implements QuestionView {

    private final LabelDiagramQuestion question;
    private AnswerCallback callback;

    public LabelDiagramView(LabelDiagramQuestion question) {
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
        final ImageView imageView = new ImageView();
        try {
            String path = "out/production/StudyQuizApp/resources/images/" + question.getQuestion();
            Image image = new Image(new FileInputStream(path));
            imageView.setImage(image);
        } catch (java.io.FileNotFoundException exception) {
            exception.printStackTrace();
            return;
        }
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
        vbox.getChildren().addAll(imageView, textField, submitButton);
    }
}
