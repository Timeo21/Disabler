package ch.timeo.disabler.listener;

import ch.timeo.disabler.Disabler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class CraftEvent implements Listener {
    @EventHandler
    public void onCraftEvent(InventoryClickEvent event) {
        if (event.getInventory() instanceof CraftingInventory && event.getCurrentItem() != null && Disabler.diamonds_stuff.contains(event.getCurrentItem().getType())) {
            if (event.getRawSlot() == 0){
                event.getWhoClicked().sendMessage(ChatColor.RED + "Ce craft est désactivé !");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onAnvilCraft(InventoryClickEvent event) {
        if (!(event.getInventory() instanceof AnvilInventory inventory)) return;

        InventoryView view = event.getView();

        int slot = event.getRawSlot();

        if (slot != view.convertSlot(slot)) return;

        if (slot != 2) return;

        ItemStack result = event.getCurrentItem();
        ItemStack source = inventory.getItem(0);

        if (!hasMending(source) && hasMending(result)) {
            if (event.getWhoClicked() instanceof Player player){
                player.sendMessage(ChatColor.RED+"Cette magie est trop compliquée pour vous !");
                player.sendMessage(ChatColor.RED+"Il semble que de la magie a été perdue.");
                player.giveExpLevels(2);
                event.getCurrentItem().removeEnchantment(Enchantment.MENDING);
            }
        }
    }

    private boolean hasMending(ItemStack item){
        if (item == null) return false;
        return item.containsEnchantment(Enchantment.MENDING);
    }

    @EventHandler
    public void onOpenLootChest(LootGenerateEvent event){
        for (ItemStack itemStack : event.getLoot()){
            if (hasMending(itemStack)){
                itemStack.removeEnchantment(Enchantment.MENDING);
                Bukkit.broadcastMessage("mending supress");
            }
        }
    }

}
