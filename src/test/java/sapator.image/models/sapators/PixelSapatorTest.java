package sapator.image.models.sapators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import sapator.image.models.ImageParams;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.*;

import org.apache.commons.io.FileUtils;
import sapator.image.models.ObservableBufferedImage;

public class PixelSapatorTest
{
    @Test
    public void testInversion() throws IOException {
        BufferedImage img = null;

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
            new File("./src/main/resources/rose.jpg"), temp));
    }

    private static boolean compareImage(File fileA, File fileB) throws IOException {

            BufferedImage biA = ImageIO.read(fileA);
            DataBuffer dbA = biA.getData().getDataBuffer();
            int sizeA = dbA.getSize();

            BufferedImage biB = ImageIO.read(fileB);
            DataBuffer dbB = biB.getData().getDataBuffer();
            int sizeB = dbB.getSize();

            if(sizeA == sizeB)
            {
                for(int i=0; i<sizeA; i++)
                {
                    if(dbA.getElem(i) != dbB.getElem(i))
                    {
                        return false;
                    }
                }

                return true;
            }
            else
            {
                return false;
            }

        }
}