package sapator.image.models.sapators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sapator.image.models.ImageParams;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

import sapator.image.models.ObservableBufferedImage;

public class PixelSapatorTest
{
    @Test
    public void testInversion() throws IOException {
        /*BufferedImage img = null;
        ObservableBufferedImage obs = new ObservableBufferedImage(img);
        obs.addListener(() -> {});

        ImageParams.getInstance().setObservableBufferedImage(obs);

        img = ImageIO.read(
            new FileInputStream("./src/main/resources/rose.jpg"));

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ImageIO.write(img,"jpg", bao);
        ImageParams.getInstance().setBufferedImage(img);

        PixelSapator.invSapator.doSapat();
        PixelSapator.invSapator.doSapat();

        File temp = new File("./src/main/resources/temp.jpg");
        ImageIO.write(
            ImageParams.getInstance()
                .getObservableBufferedImage(),
            "jpg", temp);

        Assertions.assertTrue(compareImage(
            new File("./src/main/resources/rose.jpg"), temp));*/
    }

    private static boolean compareImage(File original, File copy) throws IOException {

        BufferedImage originalImage = ImageIO.read(original);
        BufferedImage copyImage = ImageIO.read(copy);

        if ((originalImage.getHeight() == copyImage.getHeight()) &&
            (originalImage.getWidth() == copyImage.getWidth()))
        {
            for (int x = 0; x < originalImage.getWidth(); x++) {
                for (int y = 0; y < originalImage.getHeight(); y++) {

                    if (originalImage.getRGB(x, y) != copyImage.getRGB(x, y))
                    {
                        System.out.println("x: " + x + " y: " + y + "original: "
                        + originalImage.getRGB(x, y) + " copy: " + copyImage.getRGB(x, y));
                        return false;
                    }

                }
            }
        } else
        {
            return false;
        }

        return true;
    }
}