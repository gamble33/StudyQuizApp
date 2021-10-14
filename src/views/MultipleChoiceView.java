package views;

import components.MultipleChoiceButton;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import models.MultipleChoiceQuestion;

public class MultipleChoiceView {

    private final MultipleChoiceQuestion multipleChoiceQuestion;
    private final MultipleChoiceButton[] buttons;

    public MultipleChoiceView(MultipleChoiceQuestion multipleChoiceQuestion) {
        this.multipleChoiceQuestion = multipleChoiceQuestion;

        String[] possibleAnswers = this.multipleChoiceQuestion.getPossibleAnswers();
        int possibleAnswersLength = possibleAnswers.length;
        buttons = new MultipleChoiceButton[possibleAnswersLength];
        for (int i = 0; i < possibleAnswersLength; i++) {
            MultipleChoiceButton btn = new MultipleChoiceButton(possibleAnswers[i], i);
            buttons[i] = btn;
            buttons[i].setOnMouseClicked(e -> {
                if(multipleChoiceQuestion.checkAnswer(btn.getIndex())){
                    btn.getStyleClass().add("buttonCorrect");
                } else btn.getStyleClass().add("buttonIncorrect");
                test();
            });
        }

    }

    private void test(){
        System.out.println("YES");
    }

    public void show(Scene scene) {
        VBox vbox = (VBox) scene.lookup("#vboxAnswers");
        ((Label) scene.lookup("#labelQuestion")).setText(multipleChoiceQuestion.getQuestion());
        for(Button btn : buttons){
            vbox.getChildren().add(btn);
        }
    }

}
