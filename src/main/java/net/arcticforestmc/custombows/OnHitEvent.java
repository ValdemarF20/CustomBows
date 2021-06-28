package net.arcticforestmc.custombows;

import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

public class OnHitEvent implements Listener {
    private CustomBows customBows;

    public OnHitEvent(CustomBows customBows){
        this.customBows = customBows;
    }

    @EventHandler
    public void onHit(ProjectileHitEvent e){
        if(!(e.getEntity().getShooter() instanceof Player)) { return; }

        Player player = (Player) e.getEntity().getShooter();
        ItemStack customBow = player.getInventory().getItemInMainHand();
        ItemMeta meta = customBow.getItemMeta();

        if(meta == null) { return; }
        PersistentDataContainer tagContainer = meta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(customBows, "Custom-Bow-Identifier");

        if(!(tagContainer.has(key, PersistentDataType.STRING))) { return; }

        Entity projectile = e.getEntity();

        NamespacedKey arrKey = new NamespacedKey(customBows, "Special-boy-arrow");
        PersistentDataContainer arrTagContainer = projectile.getPersistentDataContainer();

        if(!(arrTagContainer.has(arrKey, PersistentDataType.STRING))){return;}

        for(int i = 0; i <= 500; i++){
            Vector v =  Vector.getRandom().subtract(new Vector(0.5, 0.5, 0.5)).normalize().multiply(0.5);
            projectile.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, projectile.getLocation(), 0, v.getX(), v.getY(), v.getZ());
        }

        projectile.remove();
    }
}
