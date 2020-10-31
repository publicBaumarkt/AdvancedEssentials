package net.baumarkt.advanced.essentials.utils.home.objects;

import org.bukkit.Location;

import java.util.UUID;

public class Home implements IHome{

    private final String ownerName;
    private final UUID ownerUniqueID;
    private final int id;
    private final Location location;

    public Home(String ownerName, UUID ownerUniqueID, int id, Location location) {
        this.ownerName = ownerName;
        this.ownerUniqueID = ownerUniqueID;
        this.id = id;
        this.location = location;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public UUID getOwnerUniqueID() {
        return ownerUniqueID;
    }
}
