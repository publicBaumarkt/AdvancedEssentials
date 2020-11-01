package net.baumarkt.advanced.essentials.utils.player;

import com.google.common.collect.Lists;
import net.baumarkt.advanced.essentials.Essentials;
import net.baumarkt.advanced.essentials.utils.home.objects.Home;
import net.baumarkt.advanced.essentials.utils.itemstack.ItemBuilder;
import net.baumarkt.advanced.essentials.utils.teleport.ask.objects.TeleportAsk;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class EssentialsPlayer {

    private final Player player;
    private boolean canFly;

    private final List<TeleportAsk> openTeleportAsks;
    private final List<Home> homes;

    public EssentialsPlayer(Player player) {
        this.player = player;

        this.canFly = false;

        this.openTeleportAsks = Lists.newArrayList();
        this.homes = Lists.newArrayList();

        this.initHomes();
    }

    private void initHomes(){


        for(int i = 1; i < 4; i++){
            if(Essentials.UTILITY.getHomeManager().getHomeConfig().getConfig().get("home." + player.getUniqueId() + "." + i) == null)
                return;

           homes.add(new Home(
                   Essentials.UTILITY.getHomeManager().getHomeConfig().getConfig().getString("home." + player.getUniqueId() + "." + i + ".owner"),
                   player.getUniqueId(), i, (Location) Essentials.UTILITY.getHomeManager().getHomeConfig().getConfig().get("home." + player.getUniqueId() + "." + i + ".location")));
        }
    }

    public void setHome(final int homeNumber){
        final Home home = new Home(player.getName(), player.getUniqueId(), homeNumber, player.getLocation());

        this.homes.add(home);

        Essentials.UTILITY.getHomeManager().getHomeConfig().set("home." + player.getUniqueId() + "." + homeNumber + ".owner", player.getName());
        Essentials.UTILITY.getHomeManager().getHomeConfig().set("home." + player.getUniqueId() + "." + homeNumber + ".location", player.getLocation());
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
        player.setAllowFlight(canFly);
    }


    public void openHomeUI(){
        final Inventory inventory = Bukkit.createInventory(null, 9, "§7Homes");

        for (Home home : homes) {
            inventory.addItem(new ItemBuilder(Material.ITEM_FRAME)
                    .setDisplayName("§a" + home.getId())
                    .addLore("§7X: " + String.valueOf(home.getLocation().getX()).split("\\.")[0],
                            "§7Y:"  +String.valueOf(home.getLocation().getY()).split("\\.")[0],
                            "§7Z: " + String.valueOf(home.getLocation().getZ()).split("\\.")[0])
                    .build());
        }

        player.openInventory(inventory);
    }

    public Home getHome(final int id){
        for (Home home : this.homes) {
            if(home.getId() == id)
                return home;
        }

        return null;
    }

    public boolean isCanFly() {
        return canFly;
    }

    public Player getPlayer() {
        return player;
    }

    public List<TeleportAsk> getOpenTeleportAsks() {
        return openTeleportAsks;
    }

    public List<Home> getHomes() {
        return homes;
    }
}
