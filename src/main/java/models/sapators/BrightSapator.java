package models.sapators;

public class BrightSapator implements Sapator
{
    @Override
    public void doSapat()
    {

    }

    private static BrightSapator ourInstance;

    public static BrightSapator getInstance()
    {
        if (ourInstance == null)
        {
            ourInstance = new BrightSapator();
        }

        return ourInstance;
    }

    private BrightSapator()
    {
    }
}
