package sapator.image.models;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ObservableBufferedImage
{
    private BufferedImage bufferedImage;
    private Runnable runnable;

    public ObservableBufferedImage(Runnable r)
    {
        runnable = r;

        try
        {
            bufferedImage = ImageIO.read(
                    new FileInputStream(ImageParams.PIC_START));

            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage,"jpg", bao);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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
