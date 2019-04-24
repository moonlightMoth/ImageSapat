package models.sapators;

import javafx.scene.image.Image;
import models.ImageParams;

import java.io.ByteArrayInputStream;

public class ColorSapator implements Sapator
{
    @Override
    public void doSapat()
    {
        Image im = ImageParams.getInstance().getImage();



        im = new Image("file:/home/moonlightmoth/Downloads/oie_mZBJJYG0Pzom.bmp");

        ImageParams.getInstance().setImage(im);

    }

    private static ColorSapator ourInstance;

    public static ColorSapator getInstance()
    {
        if (ourInstance == null)
        {
            ourInstance = new ColorSapator();
        }

        return ourInstance;
    }

    private ColorSapator()
    {

    }
}
