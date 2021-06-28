package net.arcticforestmc.custombows;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class GiveCustomBow implements CommandExecutor {
    private CustomBows customBows;
    private int counter = 0;
    public GiveCustomBow(CustomBows customBows){
        this.customBows = customBows;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String Labels, String[] args){
        if(!(sender instanceof Player)){
            if(args.length < 1){
                Bukkit.getServer().getLogger().log(Level.WARNING, "You have to specify a player");
            } else {
                giveBow(Bukkit.getServer().getPlayer(args[0]));
            }
            return true;
        }

        if(args.length < 1){
            giveBow((Player) sender);
        } else{
            giveBow(Bukkit.getServer().getPlayer(args[0]));
        }

        return true;
    }
    public void giveBow(Player player){
        ItemStack customBow = new ItemStack(Material.BOW);
        customBow.addEnchantment(Enchantment.MENDING, 1);
        ItemMeta meta = customBow.getItemMeta();
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.translateAlternateColorCodes('&', "&5The Shot Heard Round The World"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&52021"));

        if(meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&l &4L&fi&1b&4e&fr&1t&4y"));
            meta.setLore(lore);

            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            //NBT
            NamespacedKey key = new NamespacedKey(customBows, "Custom-Bow-Identifier");
            PersistentDataContainer tagContainer = meta.getPersistentDataContainer();
            tagContainer.set(key, PersistentDataType.STRING, "Super-cool-bow");
            customBow.setItemMeta(meta);

            player.getInventory().addItem(customBow);
        }
    }
}
