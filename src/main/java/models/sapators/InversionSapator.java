package models.sapators;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import models.ImageParams;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;

public class InversionSapator implements Sapator
{
    @Override
    public void doSapat()
    {
//        System.out.println("Hell"); //TODO sus

        Image image = ImageParams.getInstance().getImage();
        PixelReader reader = image.getPixelReader();
//        System.out.println(image.getUrl());

        // Load the Image into a Java FX Image Object //

        Image img = null;
        try {
            img = new Image(new FileInputStream("./src/main/resources/shabaka.jpg") );


        // Cache Width and Height to 'int's (because getWidth/getHeight return Double) and getPixels needs 'int's //

        int w = (int)img.getWidth();
        int h = (int)img.getHeight();

    // Create a new Byte Buffer, but we'll use BGRA (1 byte for each channel) //

        byte[] buf = new byte[w * h * 4];

    /* Since you can get the output in whatever format with a WritablePixelFormat,
       we'll use an already created one for ease-of-use. */

        img.getPixelReader().getPixels(0, 0, w, h, PixelFormat.getByteBgraInstance(), buf, 0, w * 4);

        for (byte b : buf)
        {
            b = (byte) ~b;
        }

        ByteArrayInputStream bis = new ByteArrayInputStream(buf);
        BufferedImage bImage2 = ImageIO.read(bis);

        ImageIO.write(bImage2, "jpg", new File("./src/main/resources/output.jpg") );
        System.out.println("image created");
        } catch (IOException e) {
            e.printStackTrace();
        }

    /* Second last parameter is byte offset you want to start in your buffer,
       and the last parameter is stride (in bytes) per line for your buffer. */

        /*int pixel;
        for (int i = 0; i < image.getWidth(); i++)
        {
            for (int j = 0; j < image.getHeight(); j++)
            {
                pixel = reader.getArgb(i, j);
                pixel = ~pixel;

            }
        }*/

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
