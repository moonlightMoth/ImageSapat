package models.sapators;

import models.ImageParams;
import java.awt.image.BufferedImage;

public class ColorSapator implements Sapator
{
    @Override
    public void doSapat()
    {

        BufferedImage img = ImageParams.getInstance().getObservableBufferedImage();

            int pixel;
            int r = 0,g = 0,b = 0;

            int rS = ImageParams.getInstance().getRcolor();
            int gS = ImageParams.getInstance().getGcolor();
            int bS = ImageParams.getInstance().getBcolor();

            for (int x = 0; x < img.getWidth(); x++) {
                for (int y = 0; y < img.getHeight(); y++) {
                    pixel = img.getRGB(x, y);
                    if (x == 0)
                    System.out.format("%H ",pixel);
//                    System.out.println(pixel);
//                    img.getColorModel().getRed(r);
//                    img.getColorModel().getGreen(g);
//                    img.getColorModel().getBlue(b);
                    r = pixel % 255;
                    g = pixel >> 8 % 255;
                    b = pixel >> 16 % 255;

                    r = (r + rS > 255 ? 255 : r + rS);
                    g = (g + gS > 255 ? 255 : g + gS);
                    b = (b + bS > 255 ? 255 : b + bS);
                    img.setRGB(x, y,pixel - b - (g<<8) - (r<<16)) ;
                }
            }

            ImageParams.getInstance().setBufferedImage(img);
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
