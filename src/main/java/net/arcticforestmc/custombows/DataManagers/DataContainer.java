package net.arcticforestmc.custombows.DataManagers;

import net.arcticforestmc.custombows.CustomBows;
import org.bukkit.persistence.PersistentDataType;

public interface DataContainer {
    public void set(final String key, final byte...args);
    public void set(final String key, final Double value);
    public void set(final String key, final Float value);
    public void set(final String key, final int...args);
    public void set(final String key, final long...args);
    public void set(final String key, final Short value);
    public void set(final String key, final String value);

    public Object get(final String key, final Object type);

    public boolean has(final String key);
}

