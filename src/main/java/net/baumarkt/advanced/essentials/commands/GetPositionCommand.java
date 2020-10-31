package net.baumarkt.advanced.essentials.commands;

import net.baumarkt.advanced.essentials.Essentials;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandExecutor;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetPositionCommand implements BetterCommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){

            final Player player = (Player) sender;

            if(Essentials.UTILITY.readConfigBoolean("commands.getposition.usePermission")){
                if(!player.hasPermission(Essentials.UTILITY.readConfigString("commands.more.permission"))){
                    player.sendMessage(Essentials.UTILITY.readConfigString("commands.more.noPermissionMessage"));
                    return true;
                }
            }

            sender.sendMessage(Essentials.UTILITY.readConfigString("commands.getposition.positionMessage")
                    .replaceAll("%X%", String.valueOf(player.getLocation().getX()))
                    .replaceAll("%Y%", String.valueOf(player.getLocation().getY()))
                    .replaceAll("%Z%", String.valueOf(player.getLocation().getZ())));
        }else
            sender.sendMessage(Essentials.UTILITY.readConfigString("commands.getposition.consoleCannotExecuteCommandMessage"));

        return false;
    }

    @Override
    public BetterCommandType getType() {
        return BetterCommandType.BASIC;
    }
}
