package net.arcticforestmc.custombows;

import net.arcticforestmc.custombows.DataManagers.DataContainer;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class ShootingEvent implements Listener {
    private final CustomBows customBows;
    private DataContainer dataContainer = Utils.getRightContainer(null);

    public ShootingEvent(CustomBows customBows) {
        this.customBows = customBows;
    }

    @EventHandler
    public void onShoot(ProjectileLaunchEvent e){
        if(!(e.getEntity().getShooter() instanceof Player) || !(e.getEntity() instanceof Arrow)) {return;}

        Player player = (Player) e.getEntity().getShooter();
        ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
        if(meta == null){return;}
        Arrow arrow = (Arrow) e.getEntity();

        dataContainer = Utils.getRightContainer(meta);
        if(!(dataContainer.has("Custom-Bow-Identifier"))){
            return;
        }

        dataContainer = Utils.getRightContainer(arrow);
        dataContainer.set("Special-boy-arrow", "Super-cool-arrow");

        // BLUE
        double redOne = 0 / 255D;
        double greenOne = 0 / 255D;
        double blue = 255 / 255D;

        // WHITE
        double redTwo = 255 / 255D;
        double greenTwo = 255 / 255D;

        // RED
        double redThree = 255 / 255D;

        new BukkitRunnable(){
            @Override
            public void run() {
                if (!arrow.isValid()) {this.cancel();}
                for(int i = 0; i <= 3; i++){
                    arrow.getWorld().spawnParticle(Particle.SPELL_MOB, arrow.getLocation(), 0, redOne, greenOne, blue, 1); // BLUE
                    arrow.getWorld().spawnParticle(Particle.SPELL_MOB, arrow.getLocation(), 0, redTwo, greenTwo, blue, 1); // WHITE
                    arrow.getWorld().spawnParticle(Particle.SPELL_MOB, arrow.getLocation(), 0, redThree, greenOne, 0 / 255D, 1); // RED
                }
            }
        }.runTaskTimerAsynchronously(customBows, 1, 1);
    }
}
