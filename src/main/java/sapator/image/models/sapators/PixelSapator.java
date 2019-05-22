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

        r = (r + rS > 255 ? 255 : r + rS);
        g = (g + gS > 255 ? 255 : g + gS);
        b = (b + bS > 255 ? 255 : b + gS);
        return (r<<16) + (g<<8) + b;
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
