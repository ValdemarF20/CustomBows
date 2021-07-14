package net.arcticforestmc.custombows;

import net.arcticforestmc.custombows.DataManagers.DataContainer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class CustomBows extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getLogger().log(Level.INFO, "CustomBows has been enabled");

        //Commands
        this.getCommand("custombow").setExecutor(new GiveCustomBow());

        //Events
        this.getServer().getPluginManager().registerEvents(new OnHitEvent(this), this);
        this.getServer().getPluginManager().registerEvents(new DamageByEntity(), this);
        this.getServer().getPluginManager().registerEvents(new ShootingEvent(this), this);
        this.getServer().getPluginManager().registerEvents(new InteractListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
