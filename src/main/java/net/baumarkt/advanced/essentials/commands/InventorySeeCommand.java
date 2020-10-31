package net.baumarkt.advanced.essentials.commands;

import net.baumarkt.advanced.essentials.Essentials;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandExecutor;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InventorySeeCommand implements BetterCommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if(sender instanceof Player){
            final Player player = (Player) sender;

            if(!player.hasPermission(Essentials.UTILITY.readConfigString("commands.inventorysee.permission"))){
                player.sendMessage(Essentials.UTILITY.readConfigString("commands.inventorysee.noPermissionMessage"));
                return true;
            }

            switch (args.length){
                case 1:

                    if(Bukkit.getPlayer(args[0]) == null){
                        player.sendMessage(Essentials.UTILITY.readConfigString("commands.inventorysee.playerNotFoundMessage"));
                        return true;
                    }

                    final Player target = Bukkit.getPlayer(args[0]);

                    player.openInventory(target.getInventory());
                    break;
                default:
                    player.sendMessage(Essentials.UTILITY.readConfigString("commands.inventorysee.usageMessage"));
                    break;
            }

        }else
            sender.sendMessage(Essentials.UTILITY.readConfigString("commands.inventorysee.consoleCannotExecuteCommandMessage"));



        return false;
    }

    @Override
    public BetterCommandType getType() {
        return BetterCommandType.ADMIN;
    }

}
