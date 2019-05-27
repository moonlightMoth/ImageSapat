package sapator.image.models;

import javafx.beans.property.*;
import sapator.image.controllers.MainController;

import java.awt.image.BufferedImage;

public class ImageParams
{
    private static String shabaka = "./src/main/resources/shabaka.jpg";
    private static String sa = "./src/test/resources/sa.jpg";
    public static final String CAT_PATH = "file:/home/moonlightmoth/IdeaProjects/ImageSapat/src/main/resources/failure.jpeg";
    public static String PIC_PATH = shabaka;
    private IntegerProperty bright = new SimpleIntegerProperty();
    private BooleanProperty inversion = new SimpleBooleanProperty();
    private IntegerProperty Rcolor = new SimpleIntegerProperty();
    private IntegerProperty Gcolor = new SimpleIntegerProperty();
    private IntegerProperty Bcolor = new SimpleIntegerProperty();
    private ObservableBufferedImage observableBufferedImage;

    private static ImageParams curState;

    private ImageParams() {
    }

    public static ImageParams getInstance()
    {
        if (curState == null)
        {
            curState = new ImageParams();
        }
        return curState;
    }




    public int getBright()
    {
        return bright.get();
    }

    public IntegerProperty brightProperty()
    {
        return bright;
    }

    public void setBright(int bright)
    {
        this.bright.set(bright);
    }

    public boolean isInversion()
    {
        return inversion.get();
    }

    public BooleanProperty inversionProperty()
    {
        return inversion;
    }

    public void setInversion(boolean inversion)
    {
        this.inversion.set(inversion);
    }

    public int getRcolor()
    {
        return Rcolor.get();
    }

    public IntegerProperty rcolorProperty()
    {
        return Rcolor;
    }

    public void setRcolor(int rcolor)
    {
        this.Rcolor.set(rcolor);
    }

    public int getGcolor()
    {
        return Gcolor.get();
    }

    public IntegerProperty gcolorProperty()
    {
        return Gcolor;
    }

    public void setGcolor(int gcolor)
    {
        this.Gcolor.set(gcolor);
    }

    public int getBcolor()
    {
        return Bcolor.get();
    }

    public IntegerProperty bcolorProperty()
    {
        return Bcolor;
    }

    public void setBcolor(int bcolor)
    {
        this.Bcolor.set(bcolor);
    }

    public void setObservableBufferedImage(ObservableBufferedImage observableBufferedImage)
    {
        this.observableBufferedImage = observableBufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage)
    {
        this.observableBufferedImage.setBufferedImage(bufferedImage);
    }

    public BufferedImage getBufferedImage()
    {
        return observableBufferedImage.getBufferedImage();
    }
}
