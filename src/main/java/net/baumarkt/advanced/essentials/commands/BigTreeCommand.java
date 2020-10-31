package net.baumarkt.advanced.essentials.commands;

import net.baumarkt.advanced.essentials.Essentials;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandExecutor;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandType;
import org.bukkit.Bukkit;
import org.bukkit.TreeType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class BigTreeCommand implements BetterCommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){

            final Player player = (Player) sender;

            if(!player.hasPermission(Essentials.UTILITY.readConfigString("commands.bigTree.permission"))){
                player.sendMessage(Essentials.UTILITY.readConfigString("commands.bigTree.noPermissionMessage"));
                return true;
            }

            switch (args.length){
                case 1:

                    if(args[0].equalsIgnoreCase("list")){
                        final String idTreeList = Arrays.asList(TreeType.values()).toString();
                        final String replace = idTreeList.substring(1, idTreeList.length() -1).replace(", ", ",");

                        player.sendMessage(Essentials.UTILITY.readConfigString("commands.bigTree.listMessage").replaceAll("%tressesTypes%", replace));

                        return true;
                    }

                    for (TreeType value : TreeType.values()) {

                        if (args[0].equals(value.name())) {

                            player.getWorld().generateTree(player.getLocation(), value);
                            player.sendMessage(Essentials.UTILITY.readConfigString("commands.bigTree.generatedMessage"));

                            return true;
                        }
                    }

                    player.sendMessage(Essentials.UTILITY.readConfigString("commands.bigTree.unknownTreeTypeMessage"));

                    break;
                case 2:
                    if(Bukkit.getPlayer(args[1]) == null){
                        player.sendMessage(Essentials.UTILITY.readConfigString("commands.enchant.playerNotFoundMessage"));
                        return true;
                    }

                    final Player target = Bukkit.getPlayer(args[1]);

                    for (TreeType value : TreeType.values()) {

                        if (args[0].equals(value.name())) {

                            target.getWorld().generateTree(target.getLocation(), value);
                            player.sendMessage(Essentials.UTILITY.readConfigString("commands.bigTree.generatedMessageAtPlayer"));

                            return true;
                        }
                    }

                    player.sendMessage(Essentials.UTILITY.readConfigString("commands.bigTree.unknownTreeTypeMessage"));

                    break;
                default:
                    player.sendMessage(Essentials.UTILITY.readConfigString("commands.bigTree.usageMessage"));
                    break;
            }

        }else
            sender.sendMessage(Essentials.UTILITY.readConfigString("commands.bigTree.consoleCannotExecuteCommandMessage"));

        return false;
    }

    @Override
    public BetterCommandType getType() {
        return BetterCommandType.ADMIN;
    }
}
