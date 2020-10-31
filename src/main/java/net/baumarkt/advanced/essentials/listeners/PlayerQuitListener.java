package net.baumarkt.advanced.essentials.listeners;

import net.baumarkt.advanced.essentials.Essentials;
import net.baumarkt.advanced.essentials.utils.Utility;
import net.baumarkt.advanced.essentials.utils.player.EssentialsPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void handle(final PlayerQuitEvent event){
        final Player player = event.getPlayer();
        final EssentialsPlayer essentialsPlayer = Utility.getPlayer(player.getUniqueId());

        if(Essentials.UTILITY.readConfigBoolean("events.playerLeaveEvent.useLeaveMessage"))
            event.setQuitMessage(Essentials.UTILITY.readConfigString("events.playerLeaveEvent.leaveMessage"));
        else
            event.setQuitMessage(null);

        Utility.ESSENTIALS_PLAYERS.remove(essentialsPlayer);

    }
}
