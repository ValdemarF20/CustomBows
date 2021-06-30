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
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class GiveCustomBow implements CommandExecutor {
    private final CustomBows customBows;
    private int counter = 1;
    private DataContainer dataContainer = Utils.getRightContainer();
    public HashMap<ItemStack, Boolean> runnableIsRunning = new HashMap<>();

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

    private ItemStack customBow = new ItemStack(Material.BOW);

    public void giveBow(Player player){
        customBow.addEnchantment(Enchantment.MENDING, 1);
        ItemMeta meta = customBow.getItemMeta();
        List<String> lore = new ArrayList<>();
        dataContainer = Utils.getRightContainer();
        customBow = (ItemStack) dataContainer.set(customBow, "Custom-Bow-Identifier", "Super-cool-bow");
        meta = customBow.getItemMeta();

        if(meta != null) {

            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&l &4L&fi&1b&4e&fr&1t&4y"));
            meta.setLore(lore);

            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            customBow.setItemMeta(meta);

            player.getInventory().addItem(customBow);

            ItemMeta finalMeta = meta;
            new BukkitRunnable(){
                private ItemStack item = customBow;
                @Override
                public void run() {
                    switch(counter){
                        case 1:

                            updateLore(item, lore, finalMeta, player, "&4The Shot Heard Round The World", "&42021");

                            counter = 2;
                            break;

                        case 2:

                            updateLore(item, lore, finalMeta, player, "&fThe Shot Heard Round The World", "&f2021");

                            counter = 3;
                            break;

                        case 3:

                            updateLore(item, lore, finalMeta, player, "&9The Shot Heard Round The World", "&92021");

                            counter = 1;
                            break;
                    }
                }
            }.runTaskTimer(customBows, 1, 60);
        }
    }

    private void updateLore(ItemStack item, List<String> lore, ItemMeta finalMeta, Player player, final String...args){
        if(InteractListener.drawing.contains(player)){
            return;
        }

        for (Player BPlayer : Bukkit.getOnlinePlayers()) {
            for (ItemStack is : BPlayer.getInventory()) {
                if((is == null) || (is.getItemMeta() == null)){ continue; }

                if(dataContainer.has(is, "Custom-Bow-Identifier") ) {
                    item = is;
                }
            }
        }

        lore.clear();

        for(String arg : args) {
            lore.add(ChatColor.translateAlternateColorCodes('&', arg));
        }

        finalMeta.setLore(lore);
        item.setItemMeta(finalMeta);

        if(player.getInventory().contains(item)) {
            player.getInventory().setItem(player.getInventory().first(item), item);
            //player.updateInventory();
        }
    }
}