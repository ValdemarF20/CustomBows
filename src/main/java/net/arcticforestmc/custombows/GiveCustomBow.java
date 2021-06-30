package net.arcticforestmc.custombows;

import net.arcticforestmc.custombows.DataManagers.DataContainer;
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
import java.util.List;
import java.util.logging.Level;

public class GiveCustomBow implements CommandExecutor {
    private final CustomBows customBows;
    private int counter = 1;
    private DataContainer dataContainer = Utils.getRightContainer(null);

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


        if(meta != null) {
            dataContainer = Utils.getRightContainer(meta);
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&l &4L&fi&1b&4e&fr&1t&4y"));
            meta.setLore(lore);

            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            dataContainer.set("Custom-Bow-Identifier", "Super-cool-bow");

            customBow.setItemMeta(meta);

            player.getInventory().addItem(customBow);

            new BukkitRunnable(){
                @Override
                public void run() {
                    switch(counter){
                        case 1:
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                for (ItemStack is : player.getInventory()) {
                                    if((is == null) || (is.getItemMeta() == null)){ continue; }

                                    if(dataContainer.has("Custom-Bow-Identifier") ) {
                                        customBow = is;
                                    }
                                }
                            }

                            lore.clear();
                            lore.add(ChatColor.translateAlternateColorCodes('&', "&4The Shot Heard Round The World"));
                            lore.add(ChatColor.translateAlternateColorCodes('&', "&42021"));
                            meta.setLore(lore);
                            customBow.setItemMeta(meta);

                            if(player.getInventory().contains(customBow)) {
                                player.getInventory().setItem(player.getInventory().first(customBow), customBow);
                                player.updateInventory();
                            }
                            counter = 2;
                            break;

                        case 2:
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                for (ItemStack is : player.getInventory()) {
                                    if((is == null) || (is.getItemMeta() == null)){ continue; }


                                    if(dataContainer.has("Custom-Bow-Identifier") ) {
                                        customBow = is;
                                    }
                                }
                            }

                            lore.clear();
                            lore.add(ChatColor.translateAlternateColorCodes('&', "&fThe Shot Heard Round The World"));
                            lore.add(ChatColor.translateAlternateColorCodes('&', "&f2021"));
                            meta.setLore(lore);
                            customBow.setItemMeta(meta);

                            if(player.getInventory().contains(customBow)) {
                                player.getInventory().setItem(player.getInventory().first(customBow), customBow);
                                player.updateInventory();
                            }
                            counter = 3;
                            break;
                        case 3:
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                for (ItemStack is : player.getInventory()) {
                                    if((is == null) || (is.getItemMeta() == null)){ continue; }

                                    if(dataContainer.has("Custom-Bow-Identifier") ) {
                                        customBow = is;
                                    }
                                }
                            }

                            lore.clear();
                            lore.add(ChatColor.translateAlternateColorCodes('&', "&9The Shot Heard Round The World"));
                            lore.add(ChatColor.translateAlternateColorCodes('&', "&92021"));
                            meta.setLore(lore);
                            customBow.setItemMeta(meta);

                            if(player.getInventory().contains(customBow)) {
                                player.getInventory().setItem(player.getInventory().first(customBow), customBow);
                                player.updateInventory();
                            }
                            counter = 1;
                            break;
                    }
                }
            }.runTaskTimer(customBows, 1, 60);
        }
    }
}
