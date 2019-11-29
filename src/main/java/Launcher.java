
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

public class Launcher extends Application {



    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass()
                .getResource("/view/Application.fxml"));
        stage.setTitle("JavaFX Maven Spring");
        stage.setScene(new Scene(root));
        stage.show();
    }
}