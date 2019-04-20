package models.sapators;

public class DownloadSapator implements Sapator
{
    @Override
    public void doSapat()
    {
        System.out.println("Down");

    }

    private static DownloadSapator ourInstance;

    public static DownloadSapator getInstance()
    {
        if (ourInstance == null)
        {
            ourInstance = new DownloadSapator();
        }

        return ourInstance;
    }

    private DownloadSapator()
    {
    }


}
