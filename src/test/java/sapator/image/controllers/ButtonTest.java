package sapator.image.controllers;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sapator.image.MainApp;
import sapator.image.models.ImageParams;
import sapator.image.models.ObservableBufferedImage;
import sapator.image.models.sapators.FileSapator;


import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ButtonTest
{
    private static final String testImg = "src/test/resources/sa.jpg";
    private static final String testImgCopy = "src/test/resources/saCopy.jpg";

    @Test
    public void testReload()
    {

        File original = new File(testImg);
        File temp = new File(testImgCopy);

        try
        {
            FileUtils.copyFile(original, temp);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        ImageParams.getInstance().setObservableBufferedImage(
                    new ObservableBufferedImage(() -> {}));
        try
        {
            FileSapator.doSapat(MOOD.OPEN, temp);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        onClickReload();

        Assertions.assertEquals(ImageParams.CAT_PATH, ImageParams.PIC_PATH);


    }

    private void smthGoingWrong()
    {
        ImageParams.PIC_PATH = ImageParams.CAT_PATH;
    }

    private void onClickReload()
    {
        try {
            FileSapator.doSapat(MOOD.OPEN, new File(ImageParams.PIC_PATH));
        } catch (IOException e)
        {
            smthGoingWrong();
        }
    }
}
