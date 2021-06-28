package net.arcticforestmc.custombows;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class CustomBows extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getLogger().log(Level.INFO, "CustomBows has been enabled");

        //Commands
        this.getCommand("custombow").setExecutor(new GiveCustomBow(this));

        //Events
        this.getServer().getPluginManager().registerEvents(new ShootEvent(this), this);
        this.getServer().getPluginManager().registerEvents(new onHitEvent(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
