package sapator.image.controllers;

import javafx.stage.FileChooser;

import java.io.File;
import java.util.function.Function;

public enum MOOD
{
    SAVE ("Save Image", (chooser) ->
    {
        return chooser.showSaveDialog(MainController.getStage());
    }),

    OPEN ("Open Image", (chooser) ->
    {
        return chooser.showOpenDialog(MainController.getStage());
    }),

    RELOAD ("Reload current Image", (chooser) ->
    {
        return
    });

    private String description;
    private Function<FileChooser, File> getFile;

    MOOD(String description, Function<FileChooser, File> getFile)
    {
        this.description = description;
        this.getFile = getFile;
    }

    public String getDescription() {
        return description;
    }

    public Function<FileChooser, File> getFile() {
        return getFile;
    }
}