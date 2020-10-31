package net.baumarkt.advanced.essentials.utils.home.manager;

import net.baumarkt.advanced.essentials.utils.home.config.HomeConfig;

public class HomeManager {

    private final HomeConfig homeConfig;

    public HomeManager(){
        this.homeConfig = new HomeConfig();
    }

    public HomeConfig getHomeConfig() {
        return homeConfig;
    }
}
