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

//        System.out.println("inversion");

        Image image = ImageParams.getInstance().getImage();
        PixelReader reader = image.getPixelReader();

        BufferedImage img = null;
        try {
            img = ImageIO.read(
                new FileInputStream(ImageParams.getInstance().getPicStart()));

            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ImageIO.write(img,"jpg", bao);

            int pixel;
            for (int x = 0; x < img.getWidth(); x++) {
                for (int y = 0; y < img.getHeight(); y++) {
                    pixel = img.getRGB(x, y);
                    img.setRGB(x, y, ~pixel);
                }
            }

            File tmp = new File("./src/main/resources/tmp.jpg");
            ImageIO.write(img, "jpg", tmp);
            ImageParams.getInstance().setPicStart("./src/main/resources/tmp.jpg");


        } catch (IOException e) {
            e.printStackTrace();
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
