package net.arcticforestmc.custombows;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class ShootingEvent implements Listener {
    private CustomBows customBows;

    public ShootingEvent(CustomBows customBows) {
        this.customBows = customBows;
    }

    @EventHandler
    public void onShoot(ProjectileLaunchEvent e){
        if(!(e.getEntity().getShooter() instanceof Player) || !(e.getEntity() instanceof Arrow)) {return;}
        Player player = (Player) e.getEntity().getShooter();
        Arrow arrow = (Arrow) e.getEntity();

        NamespacedKey key = new NamespacedKey(customBows, "Special-boy-arrow");
        PersistentDataContainer tagContainer = arrow.getPersistentDataContainer();
        tagContainer.set(key, PersistentDataType.STRING, "Super-cool-arrow");

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
