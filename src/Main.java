import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.MultipleChoiceQuestion;
import views.MultipleChoiceView;


public class Main extends Application {

    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("resources/application.fxml"));
        String styleSheet = getClass().getResource("./resources/application.css").toExternalForm();
        primaryStage.setTitle("Study Quiz");
        scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add(styleSheet);
        primaryStage.setScene(scene);
        primaryStage.show();

        MultipleChoiceQuestion q = new MultipleChoiceQuestion("1+1?", new String[]{"1", "Goose", "Sudan", "South Asia", "2", "3", "5", "Fourteen", "China", "Yes", "Maybe", "No"}, new Integer[]{4});
        new MultipleChoiceView(q).show(scene);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
