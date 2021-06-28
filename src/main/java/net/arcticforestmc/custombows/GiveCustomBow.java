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

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class GiveCustomBow implements CommandExecutor {
    private CustomBows customBows;
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
        List<String> lore = new ArrayList<>();
        lore.add("Custom Bow from 4th of July 2021");

        ItemStack customBow = new ItemStack(Material.BOW);
        customBow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        ItemMeta meta = customBow.getItemMeta();

        if(meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cL&fi&9b&ce&fr&9t&cy"));
            meta.setLore(lore);

            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

            //NBT
            NamespacedKey key = new NamespacedKey(customBows, "Custom-Bow-Identifier");
            PersistentDataContainer tagContainer = meta.getPersistentDataContainer();
            tagContainer.set(key, PersistentDataType.STRING, "Super-cool-bow");
            customBow.setItemMeta(meta);

            player.getInventory().addItem(customBow);
        }
    }
}
