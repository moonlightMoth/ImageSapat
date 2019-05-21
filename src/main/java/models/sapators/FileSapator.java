package models.sapators;

import controllers.MainController;
import javafx.stage.FileChooser;
import models.ImageParams;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class FileSapator
{
    public enum MOOD
    {
        SAVE ("Save Image", (chooser) -> {
            return chooser.showSaveDialog(MainController.getStage());
        }),
        OPEN ("Open Image", (chooser) -> {
            return chooser.showOpenDialog(MainController.getStage());
        });

        private String description;
        private Function<FileChooser, File> getFile;

        MOOD(String description, Function<FileChooser, File> getFile) {
            this.description = description;
            this.getFile = getFile;
        }
    }

    public static void doSapat(MOOD mood) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(mood.description);

        File file = mood.getFile.apply(fileChooser);

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
                    ImageParams.getInstance()
                        .setBufferedImage(ImageIO.read(file.getAbsoluteFile()));
                    break;
            }
        }

    }
}
