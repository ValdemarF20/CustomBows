package net.arcticforestmc.custombows;

import net.arcticforestmc.custombows.DataManagers.DataContainer;
import net.arcticforestmc.custombows.Utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class GiveCustomBow implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String Labels, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length < 1) {
                Bukkit.getServer().getLogger().log(Level.WARNING, "You have to specify a player");
            } else {
                giveBow(Bukkit.getServer().getPlayer(args[0]));
            }
            return true;
        }

        if (args.length < 1) {
            giveBow((Player) sender);
        } else {
            giveBow(Bukkit.getServer().getPlayer(args[0]));
        }

        return true;
    }

    private ItemStack customBow = new ItemStack(Material.BOW);

    public void giveBow(Player player) {
        customBow.addEnchantment(Enchantment.MENDING, 1);
        ItemMeta meta;
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&5The Shot Heard Round The World"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&52021"));

        DataContainer dataContainer = Utils.getRightContainer();
        customBow = (ItemStack) dataContainer.set(customBow, "Custom-Bow-Identifier", "Super-cool-bow");
        meta = customBow.getItemMeta();

        if (meta != null) {

            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&l &4L&fi&1b&4e&fr&1t&4y"));
            meta.setLore(lore);

            customBow.setItemMeta(meta);

            player.getInventory().addItem(customBow);
        }
    }
}