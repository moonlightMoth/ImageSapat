import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MainApp extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(
                MainApp.class.getResource("/MainView.fxml"));
        AnchorPane page = loader.load();
        Scene scene = new Scene(page);

        MainController.setStage(stage);

        stage.setTitle("suus");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
