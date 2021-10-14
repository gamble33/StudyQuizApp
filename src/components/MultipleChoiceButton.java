package components;

import javafx.scene.control.Button;

public class MultipleChoiceButton extends Button {

    private final int index;

    public MultipleChoiceButton(String title, int index) {
        super(title);
        this.index = index;
    }

    public int getIndex(){
        return index;
    }
}
