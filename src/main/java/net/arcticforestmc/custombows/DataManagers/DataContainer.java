package net.arcticforestmc.custombows.DataManagers;

public interface DataContainer {
    public Object set(final Object obj, final String key, final byte...args);
    public Object set(final Object obj, final String key, final Double value);
    public Object set(final Object obj, final String key, final Float value);
    public Object set(final Object obj, final String key, final int...args);
    public Object set(final Object obj, final String key, final long...args);
    public Object set(final Object obj, final String key, final Short value);
    public Object set(final Object obj, final String key, final String value);

    public Object get(final Object obj, final String key, final Object type);

    public boolean has(final Object obj, final String key);
}

