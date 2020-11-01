package net.baumarkt.advanced.essentials.commands;

import net.baumarkt.advanced.essentials.Essentials;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandExecutor;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExpCommand implements BetterCommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player){

            final Player player = (Player) commandSender;

            if(Essentials.UTILITY.readConfigBoolean("commands.exp.usePermission")){
                if(!player.hasPermission(Essentials.UTILITY.readConfigString("commands.exp.permission"))){
                    player.sendMessage(Essentials.UTILITY.readConfigString("commands.exp.noPermissionMessage"));
                    return true;
                }
            }

            switch (args.length){
                case 2:

                    if(args[0].equalsIgnoreCase("get")){
                        if(Bukkit.getPlayer(args[1]) == null){
                            player.sendMessage(Essentials.UTILITY.readConfigString("commands.exp.playerNotFoundMessage"));
                            return true;
                        }

                        final Player target = Bukkit.getPlayer(args[1]);

                        player.sendMessage(Essentials.UTILITY.readConfigString("commands.exp.getExpLevelMessage")
                                .replaceAll("%level%", String.valueOf(target.getLevel())));
                    }

                    break;
                case 3:
                    if(args[0].equalsIgnoreCase("set")){
                        if(Bukkit.getPlayer(args[1]) == null){
                            player.sendMessage(Essentials.UTILITY.readConfigString("commands.exp.playerNotFoundMessage"));
                            return true;
                        }

                        final Player target = Bukkit.getPlayer(args[1]);

                        try{
                            final int chosenExpLevel = Integer.parseInt(args[2]);

                            target.setLevel(chosenExpLevel);
                            player.sendMessage(Essentials.UTILITY.readConfigString("commands.exp.playerChangesLevelMessage"));

                        }catch (NumberFormatException exception){
                            player.sendMessage(Essentials.getInstance().getPrefix() + "§cerror: " + exception.getMessage());
                        }
                    }
                    break;
                default:
                    player.sendMessage(Essentials.UTILITY.readConfigString("commands.exp.usageMessage"));
                    break;
            }
        }else
            commandSender.sendMessage(Essentials.UTILITY.readConfigString("commands.exp.consoleCannotExecuteCommandMessage"));

        return false;
    }

    @Override
    public BetterCommandType getType() {
        return null;
    }
}
