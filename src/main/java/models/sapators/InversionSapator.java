package models.sapators;

import models.ImageParams;
import java.awt.image.BufferedImage;

public class InversionSapator implements Sapator
{
    @Override
    public void doSapat()
    {
        BufferedImage img = ImageParams.getInstance().getObservableBufferedImage();
        int pixel;
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                pixel = img.getRGB(x, y);
                img.setRGB(x, y, ~pixel);
            }
        }

        ImageParams.getInstance().setBufferedImage(img);
    }

    private static InversionSapator ourInstance;

    public static InversionSapator getInstance()
    {
        if (ourInstance == null)
        {
            ourInstance = new InversionSapator();
        }

        return ourInstance;
    }

    private InversionSapator()
    {
    }
}
