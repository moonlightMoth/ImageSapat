package models.sapators;

import controllers.MainController;
import javafx.stage.FileChooser;
import models.ImageParams;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class SaveSapator implements Sapator
{
    @Override
    public void doSapat()
    {
        System.out.println("anfhafb");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");

        File file = fileChooser.showSaveDialog(MainController.getStage());

        Path copied = Paths.get(file.getPath());
        Path originalPath = Paths.get(ImageParams.getInstance().getPicStart());

        try {
            Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
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
