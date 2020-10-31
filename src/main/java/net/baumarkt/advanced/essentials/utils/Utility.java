package net.baumarkt.advanced.essentials.utils;

import com.google.common.collect.Lists;
import net.baumarkt.advanced.essentials.Essentials;
import net.baumarkt.advanced.essentials.utils.home.manager.HomeManager;
import net.baumarkt.advanced.essentials.utils.player.EssentialsPlayer;
import org.bukkit.ChatColor;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

public class Utility {

    public static final Collection<EssentialsPlayer> ESSENTIALS_PLAYERS = Lists.newArrayList();

    private HomeManager homeManager;

    public Utility(){
        homeManager = new HomeManager();
    }

    public String readConfigString(final String path){
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(Essentials.getInstance().getConfig().getString(path)))
                .replaceAll("%prefix%", Essentials.getInstance().getPrefix());
    }

    public boolean readConfigBoolean(final String path){
        return Essentials.getInstance().getConfig().getBoolean(path);
    }

    public static EssentialsPlayer getPlayer(final UUID uuid){
        for (EssentialsPlayer essentialsPlayer : ESSENTIALS_PLAYERS) {
            if(essentialsPlayer.getPlayer().getUniqueId().equals(uuid))
                return essentialsPlayer;
        }

        return null;
    }

    public HomeManager getHomeManager() {
        return homeManager;
    }
}
