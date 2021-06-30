package net.arcticforestmc.custombows;

import net.arcticforestmc.custombows.DataManagers.DataContainer;
import net.arcticforestmc.custombows.DataManagers.LegacyDataContainer;
import net.arcticforestmc.custombows.DataManagers.SimpleDataContainer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class DamageByEntity implements Listener {
    private final CustomBows customBows;
    private DataContainer dataContainer = Utils.getRightContainer(null);

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

            dataContainer = Utils.getRightContainer(fw);
            if(dataContainer.has("Special-boy-firework")){
                e.setCancelled(true);
            }

            /*
            NamespacedKey fwKey = new NamespacedKey(customBows, "Special-boy-firework");
            PersistentDataContainer fwTagContainer = fw.getPersistentDataContainer();

            if(fwTagContainer.has(fwKey, PersistentDataType.STRING)){
                e.setCancelled(true);
            }
             */
        }
    }
}
