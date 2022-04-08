package views;

import interfaces.AnswerCallback;
import interfaces.QuestionView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainMenuView extends View implements QuestionView {

    @Override
    public void show(Scene scene, AnswerCallback callback) {
        vbox = (VBox) scene.lookup("#vboxAnswers");
        Button btn = new Button("Play");
        btn.setOnMouseClicked(e -> {
            rollbackShow();
            callback.run(false);
        });
        vbox.getChildren().add(btn);
    }

}
