package views;

import javafx.scene.layout.VBox;

public abstract class View {

    protected VBox vbox;

    protected void rollbackShow() {
        vbox.getChildren().remove(0, vbox.getChildren().size());
    }

}
