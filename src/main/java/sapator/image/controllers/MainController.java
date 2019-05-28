package sapator.image.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sapator.image.models.ImageParams;
import sapator.image.models.ObservableBufferedImage;
import sapator.image.models.sapators.*;

import javax.imageio.ImageIO;
import java.io.*;

public class MainController
{
    private static Stage stage = null;
    @FXML
    public Text textOnError;

    @FXML
    private Button openButton;

    @FXML
    private Button reloadButton;

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
    private ImageView imageView;


    public static Stage getStage()
    {
        return stage;
    }
    public static void setStage(Stage stage)
    {
        MainController.stage = stage;
    }

    private void smthGoingWrong()
    {
        setStartValues();

        imageView.setImage(new Image(ImageParams.CAT_PATH));
        ImageParams.PIC_PATH = ImageParams.CAT_PATH;

        setDisable(true);

    }

    private void setDisable(boolean state)
    {
        textOnError.setDisable(state);

        inversionCheckbox.setDisable(state);

        reloadButton.setDisable(state);
        saveButton.setDisable(state);

        greenSlider.setDisable(state);
        blueSlider.setDisable(state);
        redSlider.setDisable(state);

        brightnessSlider.setDisable(state);
    }

    private void onClickSave()
    {
        try {
            FileSapator.doSapat(MOOD.SAVE, getFile(MOOD.SAVE));
        } catch (IOException e) {
            smthGoingWrong();
        }
    }

    private void onClickOpen()
    {
        try {
            setStartValues();

            FileSapator.doSapat(MOOD.OPEN, getFile(MOOD.OPEN));

            setDisable(false);
        } catch (IOException e) {
            smthGoingWrong();
        }
    }

    void onClickReload()
    {
        try {
            setStartValues();

            FileSapator.doSapat(MOOD.OPEN, new File(ImageParams.PIC_PATH));
        } catch (IOException e)
        {
            smthGoingWrong();
        }
    }

    private File getFile(MOOD mood)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(mood.getDescription());

        return mood.getFile().apply(fileChooser);
    }

    public void onClickInversion()
    {
        ImageParams.getInstance().setInversion(inversionCheckbox.isSelected());

        PixelSapator.invSapator.doSapat();
    }

    private enum Color
    {
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

        PixelSapator.colorSapator.doSapat();

    }

    private void onChangeBright(int newBright)
    {
        ImageParams.getInstance().setBright(newBright);

        PixelSapator.brightSapator.doSapat();
    }

    private void setStartValues()
    {
        inversionCheckbox.setSelected(false);

        brightnessSlider.setValue(0);

        redSlider  .setValue(0);
        blueSlider .setValue(0);
        greenSlider.setValue(0);
    }

    private void setCurrentImageOnView()
    {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try
        {
            ImageIO.write(ImageParams.getInstance().getBufferedImage(),"jpg", bao);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());

        imageView.setImage(new Image(bis));
    }

    private void initImage()
    {
        ImageParams.getInstance().
                setObservableBufferedImage(
                        new ObservableBufferedImage
                                (this::setCurrentImageOnView));
        setCurrentImageOnView();
    }

    @FXML
    private void initialize()
    {

        initImage();

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

        openButton.setOnAction((actionEvent -> onClickOpen()));

        reloadButton.setOnAction((actionEvent -> onClickReload()));

//        forTest();

    }
}
