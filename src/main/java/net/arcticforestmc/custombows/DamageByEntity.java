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

public class DamageByEntity implements Listener {
    private final CustomBows customBows;

    public DamageByEntity(CustomBows customBows){
        this.customBows = customBows;
    }

    @EventHandler
    public void damageByEntity(EntityDamageByEntityEvent e){
        DataContainer dataContainer = Utils.getRightContainer();
        if(e.getDamager() instanceof Arrow) {

            Arrow arr = (Arrow) e.getDamager();

            dataContainer = Utils.getRightContainer();

            if(dataContainer.has(arr, "Special-boy-arrow")){
                arr.remove();
            }
        }

        if(e.getDamager() instanceof Firework) {
            Firework fw = (Firework) e.getDamager();

            dataContainer = Utils.getRightContainer();
            if(dataContainer.has(fw , "Special-boy-firework")){
                e.setCancelled(true);
            }
        }
    }
}
