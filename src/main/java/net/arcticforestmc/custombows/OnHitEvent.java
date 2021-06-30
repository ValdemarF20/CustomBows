package net.arcticforestmc.custombows;

import net.arcticforestmc.custombows.DataManagers.DataContainer;
import net.arcticforestmc.custombows.Utilities.Utils;
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
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class OnHitEvent implements Listener {
    private final CustomBows customBows;

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
        DataContainer dataContainer = Utils.getRightContainer();
        if(!(dataContainer.has(customBow, "Custom-Bow-Identifier"))){
            return;
        }

        Entity projectile = e.getEntity();

        dataContainer = Utils.getRightContainer();
        if(!(dataContainer.has(projectile, "Special-boy-arrow"))){
            return;
        }

        projectile.getWorld().playEffect(projectile.getLocation(), Effect.FIREWORK_SHOOT, 0);

        // Firework
        List<FireworkEffect> fireworkEffectList = new ArrayList<>();
        fireworkEffectList.add(FireworkEffect.builder().withColor(Color.WHITE).build());
        fireworkEffectList.add(FireworkEffect.builder().withColor(Color.BLUE).build());
        fireworkEffectList.add(FireworkEffect.builder().withColor(Color.RED).build());

        System.out.println("TEST");

        Firework firework = (Firework) projectile.getWorld().spawnEntity(projectile.getLocation(), EntityType.FIREWORK);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        fireworkMeta.addEffects(fireworkEffectList);
        fireworkMeta.setPower(1);

        String location = firework.getLocation().toString();
        System.out.println(location);

        firework.setFireworkMeta(fireworkMeta);

        dataContainer = Utils.getRightContainer();
        firework = (Firework) dataContainer.set(firework, "Special-boy-firework", "Arrow-from-4th-of-july");

        Firework finalFirework = firework;
        new BukkitRunnable(){
            @Override
            public void run() {
                finalFirework.detonate();
            }
        }.runTaskLater(customBows, 1);

        projectile.remove();
    }
}
