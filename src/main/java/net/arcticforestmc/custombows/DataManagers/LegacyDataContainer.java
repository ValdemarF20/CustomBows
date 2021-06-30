package net.arcticforestmc.custombows.DataManagers;

import net.arcticforestmc.custombows.CustomBows;
import net.arcticforestmc.custombows.ServerVersion;
import net.minecraft.server.v1_12_R1.EntityLiving;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagString;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class LegacyDataContainer implements DataContainer {
    private JavaPlugin mainInstance;

    public LegacyDataContainer(CustomBows customBows){
        this.mainInstance = customBows;
    }

    @Override
    public Object set(Object obj, String key, byte...args) {
        return false;
    }

    @Override
    public Object set(Object obj, String key, Double value) {
        return false;
    }

    @Override
    public Object set(Object obj, String key, Float value) {
        return false;
    }

    @Override
    public Object set(Object obj, String key, int...args) {
        return false;
    }

    @Override
    public Object set(Object obj, String key, long...args) {
        return false;
    }

    @Override
    public Object set(Object obj, String key, Short value) {
        return false;
    }

    @Override
    public Object set(Object obj, String key, String value) {
        if(obj instanceof ItemStack) {
            ItemStack is = (ItemStack) obj;
            if(is.getType() == Material.AIR) {return is;}

            net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(is);
            NBTTagCompound compound = (nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();

            if(compound == null) {return null;}
            compound.set(key, new NBTTagString(value));
            nmsItem.setTag(compound);

            is = CraftItemStack.asBukkitCopy(nmsItem);

            return is;
        }

        if(obj instanceof Entity){
            Entity entity = (Entity) obj;

            net.minecraft.server.v1_12_R1.Entity nmsEntity = ((CraftEntity) entity).getHandle();
            NBTTagCompound compound = new NBTTagCompound();
            nmsEntity.c(compound);

            ((EntityLiving)nmsEntity).a(compound);

            return entity;
        }
        return null;
    }

    @Override
    public Object get(Object obj, final String key, final Object type) {
        return null;
    }

    @Override
    public boolean has(Object obj, String key) {
        if(obj instanceof ItemStack) {
            ItemStack is = (ItemStack) obj;

            net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(is);
            NBTTagCompound compound = (nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();
            if(compound == null) {return false;}
            if(compound.hasKey(key)) {
                return true;
            }
        }
        return false;
    }
}
