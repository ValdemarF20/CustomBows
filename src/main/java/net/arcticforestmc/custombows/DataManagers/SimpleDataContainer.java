package net.arcticforestmc.custombows.DataManagers;

import net.arcticforestmc.custombows.CustomBows;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleDataContainer implements DataContainer {
    private JavaPlugin mainInstance;

    public SimpleDataContainer(CustomBows customBows){
        this.mainInstance = customBows;
    }

    @Override
    public Object set(Object obj, String key, byte...args) {
        if(obj instanceof PersistentDataHolder){
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();
            tagContainer.set(namespacedKey, PersistentDataType.BYTE_ARRAY, args);
        }
        return obj;
    }

    @Override
    public Object set(Object obj, String key, Double value) {
        if(obj instanceof PersistentDataHolder){
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();
            tagContainer.set(namespacedKey, PersistentDataType.DOUBLE, value);
        }
        return obj;
    }

    @Override
    public Object set(Object obj, String key, Float value) {
        if(obj instanceof PersistentDataHolder){
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();
            tagContainer.set(namespacedKey, PersistentDataType.FLOAT, value);
        }
        return obj;
    }

    @Override
    public Object set(Object obj, String key, int...args) {
        if(obj instanceof PersistentDataHolder){
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();
            tagContainer.set(namespacedKey, PersistentDataType.INTEGER_ARRAY, args);
        }
        return obj;
    }

    @Override
    public Object set(Object obj, String key, long...args) {
        if(obj instanceof PersistentDataHolder){
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();
            tagContainer.set(namespacedKey, PersistentDataType.LONG_ARRAY, args);
        }
        return obj;
    }

    @Override
    public Object set(Object obj, String key, Short value) {
        if(obj instanceof PersistentDataHolder){
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();
            tagContainer.set(namespacedKey, PersistentDataType.SHORT, value);
        }
        return obj;
    }

    @Override
    public Object set(Object obj, String key, String value) {
        if(obj instanceof PersistentDataHolder){
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();
            tagContainer.set(namespacedKey, PersistentDataType.STRING, value);
        }
        return obj;
    }

    @Override
    public Object get(Object obj, final String key, final Object type) {
        if(obj instanceof PersistentDataHolder) {
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();

            return tagContainer.get(namespacedKey, PersistentDataType.STRING);
        }
        return null;
    }

    @Override
    public boolean has(Object obj, String key) {
        PersistentDataHolder pdh = (PersistentDataHolder) obj;

        NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
        PersistentDataContainer arrTagContainer = pdh.getPersistentDataContainer();

        if (arrTagContainer.has(namespacedKey, PersistentDataType.STRING)) {
            return true;
        }

        return false;
    }
}
