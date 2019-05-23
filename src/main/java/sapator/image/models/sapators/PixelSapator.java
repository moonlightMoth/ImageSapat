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

//        System.out.format("%h ", r << 24);
//        System.out.format("%h ", ((g << 16) + 0x01000000));
//        System.out.format("%h ", ((b << 8) + 0x00010000));
//        System.out.format("%h       ", (((r << 24) + ((g << 16) + 0x01000000) + ((b << 8) + 0x00010000))>>8));
//
//        System.out.println();
//        System.out.println();
//
        r = (r - rS < -254 ? -254 : r - rS);
        g = (g - gS < -254 ? -254 : g - gS);
        b = (b - bS < -254 ? -254 : b - gS);

//        System.out.println(rS + " " + gS + " " + bS);

//        System.out.format("%h  ", pixel);
//        System.out.format("%h ", r);
//        System.out.format("%h ", g);
//        System.out.format("%h ", b);

//        System.out.format("%h ", r << 24);
//        System.out.format("%h ", ((g << 16) + 0x01000000));
//        System.out.format("%h ", ((b << 8) + 0x00010000));
//        System.out.format("%h       ", (((r << 24) + ((g << 16) + 0x01000000) + ((b << 8) + 0x00010000))>>8));
//
//        System.out.println();
//        System.out.println();

        return (((r << 24) + ((g << 16) + 0x01000000) + ((b << 8) + 0x00010000))>>8);
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
