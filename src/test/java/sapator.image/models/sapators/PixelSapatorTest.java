package sapator.image.models.sapators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sapator.image.controllers.MOOD;
import sapator.image.models.ImageParams;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

import sapator.image.models.ObservableBufferedImage;

public class PixelSapatorTest
{
    private static final String testImg = "./src/test/resources/sa.jpg";

    @Test
    public void testInversion() throws IOException {
        BufferedImage img = null;

        ImageParams.getInstance().setObservableBufferedImage(
            new ObservableBufferedImage(() -> {}));

        img = ImageIO.read(
            new FileInputStream(testImg));

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ImageIO.write(img,"jpg", bao);
        ImageParams.getInstance().setBufferedImage(img);

        PixelSapator.invSapator.doSapat();
//        PixelSapator.invSapator.doSapat();

        File temp = new File("./src/test/resources/invertedSa.jpg");
        File itog = new File("./src/test/resources/invertedSaItog.jpg");
        ImageIO.write(
            ImageParams.getInstance()
                .getBufferedImage(),
            "jpg", itog);

        Assertions.assertTrue(compareImage(
            itog, temp));
    }

    @Test
    public void testColor() throws IOException
    {
        BufferedImage img;

        ImageParams.getInstance().setObservableBufferedImage(
                new ObservableBufferedImage(() -> {}));

        img = ImageIO.read(
                new FileInputStream(testImg));

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ImageIO.write(img,"jpg", bao);
        ImageParams.getInstance().setBufferedImage(img);

        ImageParams.getInstance().setRcolor(96);
        ImageParams.getInstance().setGcolor(96);
        ImageParams.getInstance().setBcolor(96);

        PixelSapator.colorSapator.doSapat();

        ImageParams.getInstance().setRcolor(0);
        ImageParams.getInstance().setGcolor(0);
        ImageParams.getInstance().setBcolor(0);

        File temp = new File("./src/test/resources/colouredSa.jpg");
        File itog = new File("./src/test/resources/colouredSaItog.jpg");
        ImageIO.write(
                ImageParams.getInstance()
                        .getBufferedImage(),
                "jpg", itog);

        Assertions.assertTrue(compareImage(
                itog, temp));
    }

    private static boolean compareImage(File original, File copy) throws IOException {

        BufferedImage originalImage = ImageIO.read(original);
        BufferedImage copyImage = ImageIO.read(copy);

        if ((originalImage.getHeight() == copyImage.getHeight()) &&
            (originalImage.getWidth() == copyImage.getWidth()))
        {
            for (int x = 0; x < originalImage.getWidth(); x++)
            {
                for (int y = 0; y < originalImage.getHeight(); y++)
                {

                    if (originalImage.getRGB(x, y) != copyImage.getRGB(x, y))
                    {
                        System.out.format("x: %H,  y: %H, original: %H, copy: %H", x, y,
                                originalImage.getRGB(x, y), copyImage.getRGB(x, y));
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

    private boolean equalsBufferedImage(BufferedImage originalImage, BufferedImage copyImage)
    {
        if ((originalImage.getHeight() == copyImage.getHeight()) &&
                (originalImage.getWidth() == copyImage.getWidth()))
        {
            for (int x = 0; x < originalImage.getWidth(); x++)
            {
                for (int y = 0; y < originalImage.getHeight(); y++)
                {

                    if (originalImage.getRGB(x, y) != copyImage.getRGB(x, y))
                    {
                        System.out.format("x: %H,  y: %H, original: %H, copy: %H", x, y,
                                originalImage.getRGB(x, y), copyImage.getRGB(x, y));
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

    @Test
    public void testOpen() throws IOException
    {
        File temp = new File(testImg);

        ImageParams.getInstance().setObservableBufferedImage(
                new ObservableBufferedImage(() -> {}));
        FileSapator.doSapat(MOOD.OPEN, temp);
        BufferedImage first = ImageIO.read(temp.getAbsoluteFile());
        BufferedImage second = ImageParams.getInstance().getBufferedImage();

        Assertions.assertTrue(equalsBufferedImage(first, second));
    }
}