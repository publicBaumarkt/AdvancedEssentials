package net.baumarkt.advanced.essentials.utils.enums;

import org.bukkit.GameMode;

public enum EssentialsGameMode {


    SURVIVAL(0, "survival", GameMode.SURVIVAL),
    CREATIVE(1, "creative", GameMode.CREATIVE),
    ADVENTURE(2, "adventure", GameMode.ADVENTURE),
    SPECTATOR(3, "spectator", GameMode.SPECTATOR);

    private final int number;
    private final String name;
    private final GameMode gameMode;

    EssentialsGameMode(int number, String name, final GameMode gameMode) {
        this.number = number;
        this.name = name;
        this.gameMode = gameMode;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
}
