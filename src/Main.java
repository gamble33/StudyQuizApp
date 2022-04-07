import controllers.QuestionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;


public class Main extends Application {

    private Scene scene;
    private QuestionController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("resources/application.fxml")));
        String styleSheet = Objects.requireNonNull(getClass().getResource("./resources/application.css")).toExternalForm();
        primaryStage.setTitle("Study Quiz");
        scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add(styleSheet);
        primaryStage.setScene(scene);
        primaryStage.show();

        controller = new QuestionController(scene, root);



    }

    public static void main(String[] args) {
        launch(args);
    }
}
