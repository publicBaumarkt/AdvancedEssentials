package net.baumarkt.advanced.essentials.listeners;

import net.baumarkt.advanced.essentials.Essentials;
import net.baumarkt.advanced.essentials.utils.Utility;
import net.baumarkt.advanced.essentials.utils.home.objects.Home;
import net.baumarkt.advanced.essentials.utils.player.EssentialsPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void handle(final InventoryClickEvent event){

        final Player player = (Player) event.getWhoClicked();
        final EssentialsPlayer essentialsPlayer = Utility.getPlayer(player.getUniqueId());

        if(event.getClickedInventory() == null) return;
        if(event.getCurrentItem() == null) return;
        if(event.getCurrentItem().getItemMeta() == null) return;

        switch (event.getView().getTitle()){
            case "§7Homes":
                event.setCancelled(true);

                final int id = Integer.parseInt(event.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§a", ""));

                for (Home home : essentialsPlayer.getHomes()) {
                    if(home.getId() == id)
                        player.teleport(home.getLocation());

                }
                break;
        }
    }
}
