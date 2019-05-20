package models.sapators;

import controllers.MainController;
import javafx.stage.FileChooser;
import models.ImageParams;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class DownloadSapator implements Sapator
{
    @Override
    public void doSapat()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open image");
        File selectedFile = fileChooser.showOpenDialog(MainController.getStage());

        if (selectedFile != null)
        {
            try
            {
                ImageParams.getInstance().setBufferedImage(ImageIO.read(selectedFile.getAbsoluteFile()));
            }
            catch (IOException e)
            {
                e.printStackTrace();
                //TODO: write alert
            }
        }

    }

    private static DownloadSapator ourInstance;

    public static DownloadSapator getInstance()
    {
        if (ourInstance == null)
        {
            ourInstance = new DownloadSapator();
        }

        return ourInstance;
    }

    private DownloadSapator()
    {
    }


}
