package ch.timeo.disabler;

import ch.timeo.disabler.listener.CraftEvent;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public final class Disabler extends JavaPlugin {

    public static List<Material> diamonds_stuff = Arrays.asList(
            Material.DIAMOND_HELMET, Material.DIAMOND_LEGGINGS, Material.DIAMOND_CHESTPLATE,
            Material.DIAMOND_BOOTS, Material.DIAMOND_SWORD, Material.DIAMOND_AXE, Material.DIAMOND_PICKAXE,
            Material.DIAMOND_SHOVEL, Material.DIAMOND_HOE, Material.DIAMOND_BLOCK);
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new CraftEvent(),this);
    }
}
