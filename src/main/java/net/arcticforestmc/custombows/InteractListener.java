package net.arcticforestmc.custombows;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashSet;

public class InteractListener implements Listener {
    public static HashSet<Player> drawing = new HashSet<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(e.getItem() != null && e.getItem().getType() == Material.BOW) {
            if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                drawing.add(e.getPlayer());
            }
        }
    }
}
