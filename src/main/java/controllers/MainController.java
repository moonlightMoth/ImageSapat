package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.TouchEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.ImageParams;
import models.sapators.*;

import java.io.File;

public class MainController
{
    private static Stage stage = null;
    private Sapator sapator;

    @FXML
    private Button save;

    @FXML
    private Slider brightnessSlider;

    @FXML
    private Slider redSlider;

    @FXML
    private Slider greenSlider;

    @FXML
    private Slider blueSlider;

    @FXML
    private CheckBox inversionCheckbox;

    @FXML
    private Image image;

    @FXML
    private ImageView imageView;

    public static Stage getStage()
    {
        return stage;
    }

    public static void setStage(Stage stage)
    {
        MainController.stage = stage;
    }

    @FXML
    private void OnClickSave(ActionEvent actionEvent)
    {
        sapator = SaveSapator.getInstance();

        sapator.doSapat();
    }


    private void onChangeBright(int newBright)
    {
        ImageParams.getInstance().setBright(newBright);

        System.out.println(ImageParams.getInstance().getBright());

        BrightSapator.getInstance().doSapat();
    }

    public void onClickDownload(ActionEvent actionEvent)
    {
        DownloadSapator.getInstance().doSapat();

        System.out.println(ImageParams.getInstance().getPicStart());
        image = new Image(
            new File(ImageParams.getInstance().getPicStart()).toURI().toString());
        imageView.setImage(image);
    }

    public void onClickInversion(ActionEvent actionEvent)
    {
        ImageParams.getInstance().setInversion(inversionCheckbox.isSelected());

        InversionSapator.getInstance().doSapat();
    }

    private enum Color{
        RED, GREEN, BLUE
    }

    private void onChangeColor(int newColor, Color color)
    {
        switch (color)
        {
            case RED:
                ImageParams.getInstance().setRcolor(newColor);
                break;

            case GREEN:
                ImageParams.getInstance().setGcolor(newColor);
                break;

            case BLUE:
                ImageParams.getInstance().setBcolor(newColor);
                break;
        }


        System.out.println(newColor + " " + color.toString());

        sapator = ColorSapator.getInstance();
        sapator.doSapat();

        imageView.setImage(ImageParams.getInstance().getImage());

    }

    @FXML
    private void initialize()
    {
        ImageParams.getInstance().setImage(new Image("file:src/main/resources/shabaka.jpg"));
        imageView.setImage(ImageParams.getInstance().getImage());

        brightnessSlider.valueProperty().addListener(
                (observableValue, number, newNumber) ->
                        onChangeBright(newNumber.intValue()));

        redSlider.valueProperty().addListener(
                (observableValue, number, newNumber) ->
                        onChangeColor(newNumber.intValue(), Color.RED));

        greenSlider.valueProperty().addListener(
                (observableValue, number, newNumber) ->
                        onChangeColor(newNumber.intValue(), Color.GREEN));

        blueSlider.valueProperty().addListener(
                (observableValue, number, newNumber) ->
                        onChangeColor(newNumber.intValue(), Color.BLUE));

    }
}
