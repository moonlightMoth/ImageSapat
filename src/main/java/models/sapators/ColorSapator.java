package models.sapators;

public class ColorSapator implements Sapator
{

    @Override
    public void doSapat()
    {

    }

    private static ColorSapator ourInstance;

    public static ColorSapator getInstance()
    {
        if (ourInstance == null)
        {
            ourInstance = new ColorSapator();
        }

        return ourInstance;
    }

    private ColorSapator()
    {
    }
}
