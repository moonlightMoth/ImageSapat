package sapator.image.models.sapators;

import sapator.image.models.ImageParams;
import java.awt.image.BufferedImage;
import java.util.function.Function;

public class PixelSapator
{
    public static final PixelSapator invSapator = new PixelSapator((pixel) -> ~pixel);

    public static final PixelSapator colorSapator = new PixelSapator((pixel) -> {
        int rS = ImageParams.getInstance().getRcolor();
        int gS = ImageParams.getInstance().getGcolor();
        int bS = ImageParams.getInstance().getBcolor();

        int b = pixel % 256;
        int g = (pixel >> 8) % 256;
        int r = (pixel >> 16) % 256;

//        System.out.format("%h  ", pixel);
//        System.out.format("%h ", r);
//        System.out.format("%h ", g);
//        System.out.format("%h ", b);
//
//        System.out.format("%h ", r << 24);
//        System.out.format("%h ", g << 24 >> 8);
//        System.out.format("%h ", b << 24 >> 16);
//        System.out.format("%h       ", (((r << 24) + (g << 24 >> 8) + (b << 24 >> 16))));
//        System.out.println();
//
//        System.out.format("%h  ", pixel);
//        System.out.format("%h ", r);
//        System.out.format("%h ", g);
//        System.out.format("%h ", b);

//        System.out.format("%h ", r);
//        System.out.format("%h ", ((g << 16) + 0x01000000));
//        System.out.format("%h ", ((b << 8) + 0x00010000));
//        System.out.format("%h       ", (((r << 24) + ((g << 16) + 0x01000000) + ((b << 8) + 0x00010000))>>8));
//
//        System.out.println();
//        System.out.println();
//
        if (r == 0x00000000)
        {
            r = 0xffffff00;
        }

        if (g == 0x00000000)
        {
            g = 0xffffff00;
        }

        if (b == 0x00000000)
        {
            b = 0xffffff00;
        }

        r = (r + rS > -1 ? -1 : r + rS);
        g = (g + gS > -1 ? -1 : g + gS);
        b = (b + bS > -1 ? -1 : b + bS);

//        if (r == 0xffffffff)
//            r = 0xffffff01;

//        System.out.println(rS + " " + gS + " " + bS);

//        System.out.format("%h ", r);
//        System.out.format("%h ", ((g << 16) + 0x01000000));
//        System.out.format("%h ", ((b << 8) + 0x00010000));
//        System.out.format("%h       ", (((r << 24) + ((g << 16) + 0x01000000) + ((b << 8) + 0x00010000))>>8) + 0xff000000);
//
//        System.out.println();
//        System.out.println();

        return (((r << 24) + ((g << 16) + 0x01000000) + ((b << 8)+ 0x00010000))>>8) + 0xff000000;
    });

    public static final PixelSapator brightSapator = new PixelSapator((pixel) -> {
        int bright = ImageParams.getInstance().getBright();
        ImageParams.getInstance().setRcolor(bright);
        ImageParams.getInstance().setGcolor(bright);
        ImageParams.getInstance().setBcolor(bright);
        return colorSapator.function.apply(pixel);
    });

    public void doSapat()
    {
        BufferedImage img = ImageParams.getInstance().getObservableBufferedImage();
        int pixel;
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                pixel = img.getRGB(x, y);
                img.setRGB(x, y, function.apply(pixel));
            }
        }

        ImageParams.getInstance().setBufferedImage(img);
    }

    public PixelSapator(Function<Integer, Integer> function)
    {
        this.function = function;
    }
    private Function<Integer, Integer> function;

}
