package sapator.image.models.sapators;
import org.junit.jupiter.api.Test;
import sapator.image.models.ImageParams;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class PixelSapatorTest
{
    @Test
    public void testInversion() {
        BufferedImage img = null;
        try
        {
            img = ImageIO.read(
                new FileInputStream(ImageParams.getInstance().PIC_START));

            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ImageIO.write(img,"jpg", bao);
            ImageParams.getInstance().setBufferedImage(img);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        PixelSapator.brightSapator.doSapat();
    }
}