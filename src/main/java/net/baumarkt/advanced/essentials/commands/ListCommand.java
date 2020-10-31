package net.baumarkt.advanced.essentials.commands;

import com.google.common.collect.Lists;
import net.baumarkt.advanced.essentials.Essentials;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandExecutor;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ListCommand implements BetterCommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            final Player player = (Player) sender;

            if(Essentials.UTILITY.readConfigBoolean("commands.list.usePermission")){
                if(!player.hasPermission(Essentials.UTILITY.readConfigString("commands.list.permission"))){
                    player.sendMessage(Essentials.UTILITY.readConfigString("commands.list.noPermissionMessage"));
                    return true;
                }
            }

            final ArrayList<String> playerNameList = Lists.newArrayList();

            for (Player onlinePlayer : Bukkit.getOnlinePlayers())
                playerNameList.add(onlinePlayer.getDisplayName());

            final String idList = playerNameList.toString();
            final String replace = idList.substring(1, idList.length() -1).replace(", ", ",");

            player.sendMessage(Essentials.UTILITY.readConfigString("commands.list.listMessage")
                    .replaceAll("%CURRENT_PLAYERS%", String.valueOf(Bukkit.getOnlinePlayers().size()))
                    .replaceAll("%MAX_PLAYERS%", String.valueOf(Bukkit.getMaxPlayers()))
                    .replaceAll("%playerList%", replace));

        }else
            sender.sendMessage(Essentials.UTILITY.readConfigString("commands.list.consoleCannotExecuteCommandMessage"));

        return false;
    }

    @Override
    public BetterCommandType getType() {
        return BetterCommandType.BASIC;
    }
}
