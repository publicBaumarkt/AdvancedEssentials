package net.baumarkt.advanced.essentials.utils.teleport.ask.objects;

import net.baumarkt.advanced.essentials.Essentials;
import net.baumarkt.advanced.essentials.utils.player.EssentialsPlayer;
import net.baumarkt.advanced.essentials.utils.teleport.ask.manager.TeleportAskManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.scheduler.BukkitRunnable;

public class TeleportAsk {

    private final EssentialsPlayer player, to;

    private int teleportCounter;

    public TeleportAsk(EssentialsPlayer player, EssentialsPlayer to) {
        this.player = player;
        this.to = to;

        this.teleportCounter = 3;

        this.sendAsk();
    }

    private void sendAsk(){
        final TextComponent textComponent = new TextComponent(Essentials.UTILITY.readConfigString("commands.teleportAsk.askMessage")
                .replaceAll("%player%", player.getPlayer().getName()));

        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpa accept " + player.getPlayer().getName()));

        to.getPlayer().spigot().sendMessage(textComponent);

        player.getPlayer().sendMessage(Essentials.UTILITY.readConfigString("commands.teleportAsk.askSendMessage"));

        TeleportAskManager.TELEPORT_ASKS.add(this);

        player.getOpenTeleportAsks().add(this);

        new BukkitRunnable() {
            @Override
            public void run() {
                if(player.getPlayer() == null){
                    cancel();
                    return;
                }

                TeleportAskManager.TELEPORT_ASKS.remove(TeleportAsk.this);
                player.getOpenTeleportAsks().remove(TeleportAsk.this);
            }
        }.runTaskLater(Essentials.getInstance(), 20*60);
    }

    public void acceptAsk(){

        to.getPlayer().sendMessage(Essentials.UTILITY.readConfigString("commands.teleportAsk.askAcceptedMessage"));
        player.getPlayer().sendMessage(Essentials.UTILITY.readConfigString("commands.teleportAsk.askAcceptedPlayerMessage").replaceAll("%player%", player.getPlayer().getName()));

        new BukkitRunnable() {
            @Override
            public void run() {

                switch (TeleportAsk.this.teleportCounter){
                    case 3: case 2: case 1:
                        player.getPlayer().sendMessage(Essentials.UTILITY.readConfigString("commands.teleportAsk.askAcceptedCountdownMessage")
                                .replaceAll("%counter%", String.valueOf(teleportCounter)));
                        break;
                    case 0:
                        player.getPlayer().teleport(to.getPlayer().getLocation());

                        TeleportAskManager.TELEPORT_ASKS.remove(TeleportAsk.this);
                        player.getOpenTeleportAsks().remove(TeleportAsk.this);

                        cancel();
                        break;
                }

                TeleportAsk.this.teleportCounter--;
            }
        }.runTaskTimer(Essentials.getInstance(), 0, 20);
    }

    public EssentialsPlayer getPlayer() {
        return player;
    }

    public EssentialsPlayer getTo() {
        return to;
    }
}
