package net.arcticforestmc.custombows;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class DamageByEntity implements Listener {
    private CustomBows customBows;
    public DamageByEntity(CustomBows customBows){
        this.customBows = customBows;
    }

    @EventHandler
    public void damageByEntity(EntityDamageByEntityEvent e){
        if(!(e.getDamager() instanceof Arrow)){return;}

        Arrow arr = (Arrow) e.getDamager();

        NamespacedKey key = new NamespacedKey(customBows, "Special-boy-arrow");

        PersistentDataContainer tagContainer = arr.getPersistentDataContainer();
        if(tagContainer.has(key, PersistentDataType.STRING)){
            e.setDamage(0);
            arr.remove();
        }
    }
}
