package sapator.image.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sapator.image.models.ImageParams;
import sapator.image.models.ObservableBufferedImage;
import sapator.image.models.sapators.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class MainController
{
    private static Stage stage = null;

    @FXML
    private Button saveButton;

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
    private Button downloadButton;

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


    private void onClickSave()
    {
        try {
            FileSapator.doSapat(FileSapator.MOOD.SAVE);
        } catch (IOException e) {
            e.printStackTrace();//TODO
        }
    }


    private void onChangeBright(int newBright)
    {
        ImageParams.getInstance().setBright(newBright);

        System.out.println(ImageParams.getInstance().getBright());

        PixelSapator.brightSapator.doSapat();
    }

    private void onClickDownload()
    {
        try {
            setStartValues();

            FileSapator.doSapat(FileSapator.MOOD.OPEN);
        } catch (IOException e) {
            e.printStackTrace();//TODO
        }
    }

    private void setStartValues()
    {
        inversionCheckbox.setSelected(false);

        brightnessSlider.setValue(brightnessSlider.getMin());

        redSlider  .setValue(redSlider  .getMin());
        blueSlider .setValue(blueSlider .getMin());
        greenSlider.setValue(greenSlider.getMin());
    }

    public void onClickInversion()
    {
        ImageParams.getInstance().setInversion(inversionCheckbox.isSelected());

        PixelSapator.invSapator.doSapat();
    }

    private enum Color{
        RED, GREEN, BLUE
    }

    private void onChangeColor(int deltaColor, Color color)
    {
        ImageParams.getInstance().setBcolor(0);
        ImageParams.getInstance().setRcolor(0);
        ImageParams.getInstance().setGcolor(0);
        switch (color)
        {
            case RED:
                ImageParams.getInstance().setRcolor(deltaColor);
                break;

            case GREEN:
                ImageParams.getInstance().setGcolor(deltaColor);
                break;

            case BLUE:
                ImageParams.getInstance().setBcolor(deltaColor);
                break;
        }


        System.out.println(deltaColor + " " + color.toString());

        PixelSapator.colorSapator.doSapat();

    }

    private void setCurrentImageOnView() {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try
        {
            ImageIO.write(ImageParams.getInstance().getObservableBufferedImage(),"jpg", bao);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());

        imageView.setImage(new Image(bis));
    }

    @FXML
    private void initialize()
    {
        BufferedImage img = null;

        ObservableBufferedImage obs = new ObservableBufferedImage(img);

        obs.addListener(this::setCurrentImageOnView);

        ImageParams.getInstance().setObservableBufferedImage(obs);

        try
        {
            img = ImageIO.read(
                    new FileInputStream(ImageParams.PIC_START));

            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ImageIO.write(img,"jpg", bao);
            ImageParams.getInstance().setBufferedImage(img);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        brightnessSlider.valueProperty().addListener(
                (observableValue, number, newNumber) ->
                        onChangeBright(newNumber.intValue()-number.intValue()));

        redSlider.valueProperty().addListener(
                (observableValue, number, newNumber) ->
                        onChangeColor(newNumber.intValue()-number.intValue(), Color.RED));

        greenSlider.valueProperty().addListener(
                (observableValue, number, newNumber) ->
                        onChangeColor(newNumber.intValue()-number.intValue(), Color.GREEN));

        blueSlider.valueProperty().addListener(
                (observableValue, number, newNumber) ->
                        onChangeColor(newNumber.intValue()-number.intValue(), Color.BLUE));

        saveButton.setOnAction((actionEvent) -> onClickSave());

        downloadButton.setOnAction((actionEvent -> onClickDownload()));

    }
}
