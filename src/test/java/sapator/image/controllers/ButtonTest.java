package sapator.image.controllers;

import org.junit.jupiter.api.Test;
import sapator.image.models.ImageParams;
import sapator.image.models.ObservableBufferedImage;
import sapator.image.models.sapators.FileSapator;

import java.io.File;
import java.io.IOException;

public class ButtonTest
{
    private static final String testImg = "./src/test/resources/sa.jpg";

    @Test
    public void testReload() throws IOException
    {
        File temp = File.createTempFile("temp", "", new File(testImg));

        ImageParams.getInstance().setObservableBufferedImage(
                new ObservableBufferedImage(() -> {}));
        FileSapator.doSapat(MOOD.OPEN, temp);

        temp.delete();




    }
}
