package models.sapators;

public class SaveSapator implements Sapator
{
    @Override
    public void doSapat()
    {
        System.out.println("anfhafb");


    }
    private static SaveSapator ourInstance;

    public static SaveSapator getInstance()
    {
        if (ourInstance == null)
        {
            ourInstance = new SaveSapator();
        }

        return ourInstance;
    }

    private SaveSapator()
    {
    }
}
