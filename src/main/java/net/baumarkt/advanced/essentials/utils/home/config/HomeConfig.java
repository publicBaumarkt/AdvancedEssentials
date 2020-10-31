package net.baumarkt.advanced.essentials.utils.home.config;

import net.baumarkt.advanced.essentials.utils.home.objects.Home;
import net.baumarkt.advanced.essentials.utils.player.EssentialsPlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HomeConfig {

    private final File file;
    private final YamlConfiguration config;

    public HomeConfig(){

        this.file = new File("plugins/AdvancedEssentials/homes.yml");
        config = YamlConfiguration.loadConfiguration(file);

    }

    private void saveConfig(){
        try {
            this.config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void set(final String key, final Object value){
        this.config.set(key, value);
        this.saveConfig();
    }

    public Home getHome(final EssentialsPlayer essentialsPlayer, final int id){
        for (Home home : essentialsPlayer.getHomes()) {
            if(home.getId() == id)
                return home;
        }
        return null;
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public File getFile() {
        return file;
    }
}
