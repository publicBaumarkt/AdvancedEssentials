package net.baumarkt.advanced.essentials;

import net.baumarkt.advanced.essentials.commands.*;
import net.baumarkt.advanced.essentials.listeners.PlayerJoinListener;
import net.baumarkt.advanced.essentials.listeners.PlayerQuitListener;
import net.baumarkt.advanced.essentials.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Essentials extends JavaPlugin {

    private static Essentials instance;

    public static Utility UTILITY;

    private String prefix;
    
    @Override
    public void onEnable() {
        saveDefaultConfig();

        init();
        registerCommands();
        registerListeners();

    }

    private void init(){
        instance = this;

         UTILITY = new Utility();

        prefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(Essentials.getInstance().getConfig().getString("prefix")));
    }

    private void registerListeners(){
        final PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);

    }

    private void registerCommands(){
        getCommand("bigtree").setExecutor(new BigTreeCommand());
        getCommand("enchant").setExecutor(new EnchantCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("time").setExecutor(new TimeCommand());
        getCommand("gamemode").setExecutor(new GameModeCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("more").setExecutor(new MoreCommand());
        getCommand("getposition").setExecutor(new GetPositionCommand());
        getCommand("list").setExecutor(new ListCommand());
        getCommand("inventorysee").setExecutor(new InventorySeeCommand());
        getCommand("ping").setExecutor(new PingCommand());
        getCommand("teleportask").setExecutor(new TeleportAskCommand());
    }

    public String getPrefix() {
        return prefix;
    }

    public static Essentials getInstance() {
        return instance;
    }
}
