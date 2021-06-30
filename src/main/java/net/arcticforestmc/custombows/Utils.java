package net.arcticforestmc.custombows;

import net.arcticforestmc.custombows.DataManagers.DataContainer;
import net.arcticforestmc.custombows.DataManagers.LegacyDataContainer;
import net.arcticforestmc.custombows.DataManagers.SimpleDataContainer;
import org.bukkit.plugin.java.JavaPlugin;

public final class Utils {
    private Utils(){}

    public static DataContainer getRightContainer() {
        CustomBows customBows = JavaPlugin.getPlugin(CustomBows.class);

        if (ServerVersion.CURRENT_VERSION.isOlderThan(ServerVersion.V1_14_R1)) {return new LegacyDataContainer(customBows);}
        return new SimpleDataContainer(customBows);
    }
}
