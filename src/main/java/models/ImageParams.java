package models;

import javafx.beans.property.*;
import javafx.scene.image.Image;

public class ImageParams
{
    //full path
    private StringProperty picSave = new SimpleStringProperty();
    //full path
    private StringProperty picStart = new SimpleStringProperty();
    private IntegerProperty bright = new SimpleIntegerProperty();
    private BooleanProperty inversion = new SimpleBooleanProperty();
    private IntegerProperty Rcolor = new SimpleIntegerProperty();
    private IntegerProperty Gcolor = new SimpleIntegerProperty();
    private IntegerProperty Bcolor = new SimpleIntegerProperty();
    private Image image = new Image("file:/src/main/resources/shabaka.jpg");

    public Image getImage()
    {
        return image;
    }

    public void setImage(Image image)
    {
        this.image = image;
    }

    private static ImageParams curState;

    private ImageParams() { }

    public static ImageParams getInstance()
    {
        if (curState == null)
        {
            curState = new ImageParams();
        }
        return curState;
    }

    public String getPicStart()
    {
        return picStart.get();
    }

    public StringProperty picStartProperty()
    {
        return picStart;
    }

    public void setPicStart(String picStart)
    {
        this.picStart.set(picStart);
    }

    public String getPicSave()
    {
        return picSave.get();
    }

    public StringProperty picSaveProperty()
    {
        return picSave;
    }

    public void setPicSave(String picSave)
    {
        this.picSave.set(picSave);
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
}
