package net.baumarkt.advanced.essentials.commands;

import net.baumarkt.advanced.essentials.Essentials;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandExecutor;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandType;
import net.baumarkt.advanced.essentials.utils.Utility;
import net.baumarkt.advanced.essentials.utils.player.EssentialsPlayer;
import net.baumarkt.advanced.essentials.utils.teleport.ask.manager.TeleportAskManager;
import net.baumarkt.advanced.essentials.utils.teleport.ask.objects.TeleportAsk;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportAskCommand implements BetterCommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player){

            final Player player = (Player) commandSender;
            final EssentialsPlayer essentialsPlayer = Utility.getPlayer(player.getUniqueId());

            if(Essentials.UTILITY.readConfigBoolean("commands.teleportAsk.usePermission")){

                if(!player.hasPermission(Essentials.UTILITY.readConfigString("commands.teleportAsk.permission"))){
                    player.sendMessage(Essentials.UTILITY.readConfigString("commands.teleportAsk.noPermissionMessage"));
                    return true;
                }
            }

            switch (args.length){
                case 1:
                    if(Bukkit.getPlayer(args[0]) == null){
                        player.sendMessage(Essentials.UTILITY.readConfigString("commands.teleportAsk.playerNotFoundMessage"));
                        return true;
                    }

                    final Player target = Bukkit.getPlayer(args[0]);
                    final EssentialsPlayer targetEssentials = Utility.getPlayer(target.getUniqueId());

                    new TeleportAsk(essentialsPlayer, targetEssentials);
                    break;
                case 2:
                    if(args[0].equals("accept")){
                        for (TeleportAsk teleportAsk : TeleportAskManager.TELEPORT_ASKS) {
                            if(teleportAsk.getTo().getPlayer().getUniqueId().equals(player.getUniqueId())
                                    && teleportAsk.getPlayer().getPlayer().getName().equalsIgnoreCase(args[1])){

                                teleportAsk.acceptAsk();

                                return true;
                            }
                        }

                        player.sendMessage(Essentials.UTILITY.readConfigString("commands.teleportAsk.noAskFoundMessage"));

                        return true;
                    }
                    break;
                default:
                    player.sendMessage(Essentials.UTILITY.readConfigString("commands.teleportAsk.usageMessage"));
                    break;
            }
        }else
            commandSender.sendMessage(Essentials.UTILITY.readConfigString("commands.teleportAsk.consoleCannotExecuteCommandMessage"));

        return false;
    }

    @Override
    public BetterCommandType getType() {
        return BetterCommandType.BASIC;
    }
}
