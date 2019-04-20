package models.sapators;

public abstract class SingletonSapator<T> implements Sapator
{
    protected T ourInstance = (T) new Object();

    public T getInstance()
    {
        return ourInstance;
    }

    protected SingletonSapator()
    {
    }
}
