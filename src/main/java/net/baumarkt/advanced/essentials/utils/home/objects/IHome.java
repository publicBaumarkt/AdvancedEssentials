package net.baumarkt.advanced.essentials.utils.home.objects;

import org.bukkit.Location;

import java.util.UUID;

public interface IHome {

    Location getLocation();
    String getOwnerName();
    int getId();
    UUID getOwnerUniqueID();

}
