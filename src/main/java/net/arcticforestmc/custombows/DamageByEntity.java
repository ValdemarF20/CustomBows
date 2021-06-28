package net.arcticforestmc.custombows;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Firework;
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
        if(e.getDamager() instanceof Arrow) {

            Arrow arr = (Arrow) e.getDamager();

            NamespacedKey arrKey = new NamespacedKey(customBows, "Special-boy-arrow");
            PersistentDataContainer arrTagContainer = arr.getPersistentDataContainer();

            if(arrTagContainer.has(arrKey, PersistentDataType.STRING)){
                arr.remove();
            }
        }

        if(e.getDamager() instanceof Firework) {
            Firework fw = (Firework) e.getDamager();

            NamespacedKey fwKey = new NamespacedKey(customBows, "Special-boy-firework");
            PersistentDataContainer fwTagContainer = fw.getPersistentDataContainer();

            if(fwTagContainer.has(fwKey, PersistentDataType.STRING)){
                e.setCancelled(true);
            }
        }
    }
}
