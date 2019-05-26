package sapator.image.models.sapators;

import sapator.image.controllers.MOOD;
import sapator.image.models.ImageParams;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class FileSapator
{
    public static void doSapat(MOOD mood, File file) throws IOException
    {
        if (file != null)
        {
            switch (mood) {
                case SAVE:
                    ImageIO.write(
                        ImageParams.getInstance()
                            .getObservableBufferedImage(),
                        "jpg", file);
                    break;

                case OPEN:
                    ImageParams.PIC_PATH = file.getAbsolutePath();
                    ImageParams.getInstance()
                        .setBufferedImage(ImageIO.read(file.getAbsoluteFile()));
                    break;
            }
        }

    }
}
