package net.baumarkt.advanced.essentials.commands;

import net.baumarkt.advanced.essentials.Essentials;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandExecutor;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PingCommand implements BetterCommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            final Player player = (Player) sender;

            if(Essentials.UTILITY.readConfigBoolean("commands.ping.usePermission")){
                if(!player.hasPermission(Essentials.UTILITY.readConfigString("commands.ping.permission"))){
                    player.sendMessage(Essentials.UTILITY.readConfigString("commands.ping.noPermissionMessage"));
                    return true;
                }
            }

            player.sendMessage(Essentials.UTILITY.readConfigString("commands.ping.pingMessage")
                    .replaceAll("%ping%", String.valueOf(((CraftPlayer)player).getHandle().ping)));

        }else
            sender.sendMessage(Essentials.UTILITY.readConfigString("commands.ping.consoleCannotExecuteCommandMessage"));


        return false;
    }

    @Override
    public BetterCommandType getType() {
        return BetterCommandType.BASIC;
    }
}
