package net.baumarkt.advanced.essentials.listeners;

import net.baumarkt.advanced.essentials.Essentials;
import net.baumarkt.advanced.essentials.utils.Utility;
import net.baumarkt.advanced.essentials.utils.player.EssentialsPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void handle(final PlayerJoinEvent event){
        final Player player = event.getPlayer();

        if(Essentials.UTILITY.readConfigBoolean("events.playerJoinEvent.useJoinMessage"))
            event.setJoinMessage(Essentials.UTILITY.readConfigString("events.playerJoinEvent.joinMessage"));
        else
            event.setJoinMessage(null);

        Utility.ESSENTIALS_PLAYERS.add(new EssentialsPlayer(player));
    }
}
