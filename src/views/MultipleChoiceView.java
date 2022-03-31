package views;

import components.MultipleChoiceButton;
import interfaces.GeneralCallback;
import interfaces.QuestionView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import models.MultipleChoiceQuestion;
import interfaces.AnswerCallback;
import utils.TimeUtils;

import java.util.Arrays;
import java.util.Collections;

public class MultipleChoiceView implements QuestionView {

    private final MultipleChoiceQuestion multipleChoiceQuestion;
    private final MultipleChoiceButton[] buttons;
    private AnswerCallback callback;
    private VBox vbox;
    private int wrongGuesses;

    private final int answerDelay = 200;

    public MultipleChoiceView(MultipleChoiceQuestion multipleChoiceQuestion) {
        this.multipleChoiceQuestion = multipleChoiceQuestion;

        String[] possibleAnswers = this.multipleChoiceQuestion.getPossibleAnswers();
        Collections.shuffle(Arrays.asList(possibleAnswers));
        int possibleAnswersLength = possibleAnswers.length;
        buttons = new MultipleChoiceButton[possibleAnswersLength];
        for (int i = 0; i < possibleAnswersLength; i++) {
            MultipleChoiceButton btn = new MultipleChoiceButton(possibleAnswers[i], i);
            buttons[i] = btn;
            buttons[i].setOnMouseClicked(e -> {
                if (multipleChoiceQuestion.checkAnswer(btn.getText())) {
                    btn.getStyleClass().add("buttonCorrect");
                    onAnswer(true);
                } else {
                    wrongGuesses++;
                    btn.getStyleClass().add("buttonIncorrect");
                    if (wrongGuesses > 1) {
                        onAnswer(false);
                    }
                }

            });
        }

    }

    private void onAnswer(boolean correctAnswer) {
        TimeUtils.setTimeout(() -> {
            callback.run(correctAnswer);
            rollbackShow();
        }, answerDelay);
    }

    private void rollbackShow() {
        for (Button btn : buttons) {
            vbox.getChildren().remove(btn);
        }
    }

    @Override
    public void show(Scene scene, AnswerCallback callback) {
        this.callback = callback;
        VBox vbox = (VBox) scene.lookup("#vboxAnswers");
        ((Label) scene.lookup("#labelQuestion")).setText(multipleChoiceQuestion.getQuestion());
        for (Button btn : buttons) {
            vbox.getChildren().add(btn);
        }
        this.vbox = vbox;
    }


}
