package models;

import java.awt.image.BufferedImage;

public class ObservableBufferedImage
{
    private BufferedImage bufferedImage;
    private Listener listener;

    public ObservableBufferedImage(BufferedImage bi)
    {
        bufferedImage = bi;
    }

    public void setBufferedImage(BufferedImage bufferedImage)
    {
        this.bufferedImage = bufferedImage;
        listener.onAction();
    }

    public BufferedImage getBufferedImage()
    {
        return bufferedImage;
    }

    public void addListener(Listener listener)
    {
        this.listener = listener;
    }
}
