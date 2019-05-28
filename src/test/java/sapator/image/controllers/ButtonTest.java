package sapator.image.controllers;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sapator.image.models.ImageParams;
import sapator.image.models.ObservableBufferedImage;
import sapator.image.models.sapators.FileSapator;

import java.io.File;
import java.io.IOException;

public class ButtonTest
{
    private static final String testImg = "./src/test/resources/sa.jpg";
    private static final String testImgCopy = "./src/test/resources/saCopy.jpg";

    @Test
    public void testReload() throws IOException
    {
        File original = new File(testImg);
        File temp = new File(testImgCopy);

        FileUtils.copyFile(original, temp);

        ImageParams.getInstance().setObservableBufferedImage(
                new ObservableBufferedImage(() -> {}));
        FileSapator.doSapat(MOOD.OPEN, temp);

        temp.delete();

        new MainController().onClickReload();

        Assertions.assertEquals(ImageParams.PIC_PATH, ImageParams.CAT_PATH);


    }
}
