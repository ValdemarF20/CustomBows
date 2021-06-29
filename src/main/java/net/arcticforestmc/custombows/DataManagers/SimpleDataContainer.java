package net.arcticforestmc.custombows.DataManagers;

import net.arcticforestmc.custombows.CustomBows;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleDataContainer implements DataContainer {
    private JavaPlugin mainInstance;
    private Object obj;

    public SimpleDataContainer(CustomBows customBows, Object obj){
        this.mainInstance = customBows;
        this.obj = obj;
    }

    @Override
    public void set(String key, byte...args) {
        if(obj instanceof PersistentDataHolder){
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();
            tagContainer.set(namespacedKey, PersistentDataType.BYTE_ARRAY, args);
        }
    }

    @Override
    public void set(String key, Double value) {
        if(obj instanceof PersistentDataHolder){
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();
            tagContainer.set(namespacedKey, PersistentDataType.DOUBLE, value);
        }
    }

    @Override
    public void set(String key, Float value) {
        if(obj instanceof PersistentDataHolder){
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();
            tagContainer.set(namespacedKey, PersistentDataType.FLOAT, value);
        }
    }

    @Override
    public void set(String key, int...args) {
        if(obj instanceof PersistentDataHolder){
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();
            tagContainer.set(namespacedKey, PersistentDataType.INTEGER_ARRAY, args);
        }
    }

    @Override
    public void set(String key, long...args) {
        if(obj instanceof PersistentDataHolder){
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();
            tagContainer.set(namespacedKey, PersistentDataType.LONG_ARRAY, args);
        }
    }

    @Override
    public void set(String key, Short value) {
        if(obj instanceof PersistentDataHolder){
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();
            tagContainer.set(namespacedKey, PersistentDataType.SHORT, value);
        }
    }

    @Override
    public void set(final String key, final String value) {
        if(obj instanceof PersistentDataHolder){
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();
            tagContainer.set(namespacedKey, PersistentDataType.STRING, value);
        }
    }

    @Override
    public Object get(final String key, final Object type) {
        if(obj instanceof PersistentDataHolder) {
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();

            return tagContainer.get(namespacedKey, PersistentDataType.STRING);
        }
        return null;
    }

    @Override
    public boolean has(String key) {
        PersistentDataHolder pdh = (PersistentDataHolder) obj;

        NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
        PersistentDataContainer arrTagContainer = pdh.getPersistentDataContainer();

        if (arrTagContainer.has(namespacedKey, PersistentDataType.STRING)) {
            return true;
        }

        return false;
    }
}
