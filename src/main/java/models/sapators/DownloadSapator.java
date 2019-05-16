package models.sapators;

import controllers.MainController;
import javafx.stage.FileChooser;
import models.ImageParams;

import java.io.File;

public class DownloadSapator implements Sapator
{
    @Override
    public void doSapat()
    {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(MainController.getStage());

        if (selectedFile != null)
        {
            ImageParams.getInstance().setPicStart(selectedFile.getAbsolutePath());
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
