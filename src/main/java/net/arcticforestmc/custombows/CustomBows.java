package net.arcticforestmc.custombows;

import net.arcticforestmc.custombows.DataManagers.DataContainer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class CustomBows extends JavaPlugin {
    private DataContainer dataContainer;

    @Override
    public void onEnable() {
        this.getServer().getLogger().log(Level.INFO, "CustomBows has been enabled");

        //Commands
        this.getCommand("custombow").setExecutor(new GiveCustomBow(this));

        //Events
        this.getServer().getPluginManager().registerEvents(new OnHitEvent(this), this);
        this.getServer().getPluginManager().registerEvents(new DamageByEntity(this), this);
        this.getServer().getPluginManager().registerEvents(new ShootingEvent(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
