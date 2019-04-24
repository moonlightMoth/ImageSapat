package models.sapators;

import javafx.stage.FileChooser;
import models.ImageParams;

import java.io.File;

public class DownloadSapator implements Sapator
{
    @Override
    public void doSapat()
    {
        System.out.println("Down");
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

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
