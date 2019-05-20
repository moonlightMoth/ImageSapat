package models.sapators;

import controllers.MainController;
import javafx.stage.FileChooser;
import models.ImageParams;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SaveSapator implements Sapator
{
    @Override
    public void doSapat()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");

        File file = fileChooser.showSaveDialog(MainController.getStage());

        if (file != null) {
            try
            {
                ImageIO.write(ImageParams.getInstance().getObservableBufferedImage(), "jpg", file);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                //TODO: write alert
            }
        }

    }
    private static SaveSapator ourInstance;

    public static SaveSapator getInstance()
    {
        if (ourInstance == null)
        {
            ourInstance = new SaveSapator();
        }

        return ourInstance;
    }

    private SaveSapator()
    {
    }
}
