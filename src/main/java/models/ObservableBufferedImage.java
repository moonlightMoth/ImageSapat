package models;

import java.awt.image.BufferedImage;

public class ObservableBufferedImage
{
    private BufferedImage bufferedImage;
    private Runnable runnable;

    public ObservableBufferedImage(BufferedImage bi)
    {
        bufferedImage = bi;
    }

    public void setBufferedImage(BufferedImage bufferedImage)
    {
        this.bufferedImage = bufferedImage;
        runnable.run();
    }

    public BufferedImage getBufferedImage()
    {
        return bufferedImage;
    }

    public void addListener(Runnable runnable)
    {
        this.runnable = runnable;
    }

}
