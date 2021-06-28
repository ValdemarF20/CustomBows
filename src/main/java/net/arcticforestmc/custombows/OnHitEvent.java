package net.arcticforestmc.custombows;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

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

        projectile.getWorld().playEffect(projectile.getLocation(), Effect.FIREWORK_SHOOT, 0);

        // Firework
        List<FireworkEffect> fireworkEffectList = new ArrayList<>();
        fireworkEffectList.add(FireworkEffect.builder().withColor(Color.WHITE).build());
        fireworkEffectList.add(FireworkEffect.builder().withColor(Color.BLUE).build());
        fireworkEffectList.add(FireworkEffect.builder().withColor(Color.RED).build());

        Firework firework = (Firework) projectile.getWorld().spawnEntity(projectile.getLocation(), EntityType.FIREWORK);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        fireworkMeta.addEffects(fireworkEffectList);
        fireworkMeta.setPower(0);

        firework.setFireworkMeta(fireworkMeta);
        firework.detonate();

        NamespacedKey fwKey = new NamespacedKey(customBows, "Special-boy-firework");
        PersistentDataContainer fwTagContainer = firework.getPersistentDataContainer();
        fwTagContainer.set(fwKey, PersistentDataType.STRING, "Arrow-from-4th-of-july");

        projectile.remove();
    }
}
