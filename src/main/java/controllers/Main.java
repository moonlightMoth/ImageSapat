package controllers;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(
                Main.class.getResource("/MainView.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Scene scene = new Scene(page);

        stage.setTitle("suus");
        stage.setScene(scene);
        stage.show();
        /*String javaVer = System.getProperty("java.version");
        String javaFxVer = System.getProperty("javafx.version");
        Label label = new Label("out sas "+javaFxVer+ " " + javaVer);
        Scene scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();*/

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
