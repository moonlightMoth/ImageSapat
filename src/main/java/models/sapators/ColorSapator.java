package models.sapators;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;
import models.ImageParams;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class ColorSapator implements Sapator
{
    @Override
    public void doSapat()
    {
        Image im = ImageParams.getInstance().getImage();

        byte[] i = getPixels(im, 0,0);

        for (byte b : i)
        {
            b += 100;
        }


        im = new Image(new ByteArrayInputStream(i));

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

    private byte[] getPixels(Image img, int x, int y)
    {
        int w = (int) img.getWidth();
        int h = (int) img.getHeight();

        byte[] buf = new byte[w * h * 4];

        img.
                getPixelReader().
                getPixels(
                        0,
                        0,
                        w,
                        h,
                PixelFormat.
                        getByteBgraInstance(),
                buf,
                0, w * 4);

        return buf;
    }
}
