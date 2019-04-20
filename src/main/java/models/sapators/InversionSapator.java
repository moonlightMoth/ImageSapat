package models.sapators;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import models.ImageParams;

public class InversionSapator implements Sapator
{
    @Override
    public void doSapat()
    {
        System.out.println("Hell"); //TODO sus

        PixelReader reader = ImageParams.getInstance().getImage().getPixelReader();
        Image image = ImageParams.getInstance().getImage();

        int pixel;
        for (int i = 0; i < image.getWidth(); i++)
        {
            for (int j = 0; j < image.getHeight(); j++)
            {
                pixel = reader.getArgb(i, j);
                pixel = ~pixel;

            }
        }

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
