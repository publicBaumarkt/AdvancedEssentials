package net.baumarkt.advanced.essentials.commands;

import net.baumarkt.advanced.essentials.Essentials;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandExecutor;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandType;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimeCommand implements BetterCommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){

            final Player player = (Player) sender;

            if(!player.hasPermission(Essentials.UTILITY.readConfigString("commands.time.permission"))){
                player.sendMessage(Essentials.UTILITY.readConfigString("commands.time.noPermissionMessage"));
                return true;
            }


            switch (args.length){
                case 1:
                    if(args[0].equalsIgnoreCase("day")){
                        final World world = player.getWorld();

                        world.setFullTime(1000);
                        player.sendMessage(Essentials.UTILITY.readConfigString("commands.time.timeChangedMessage")
                                .replaceAll("%newTime%", "DAY"));

                        return true;
                    }

                    if(args[0].equalsIgnoreCase("night")){
                        final World world = player.getWorld();

                        world.setFullTime(13000);
                        player.sendMessage(Essentials.UTILITY.readConfigString("commands.time.timeChangedMessage")
                                .replaceAll("%newTime%", "NIGHT"));

                        return true;
                    }

                    try{
                        final int ticks = Integer.parseInt(args[0]);
                        final World world = player.getWorld();

                        world.setFullTime(ticks);
                        player.sendMessage(Essentials.UTILITY.readConfigString("commands.time.timeChangedMessage")
                                .replaceAll("%newTime%", String.valueOf(ticks)));

                    }catch (NumberFormatException exception){
                        player.sendMessage(Essentials.getInstance().getPrefix() + "§cerror: " + exception.getMessage());
                    }

                    break;
                case 2:
                    final World world = Bukkit.getWorld(args[1]);

                    if(args[0].equalsIgnoreCase("day")){

                        world.setFullTime(1000);
                        player.sendMessage(Essentials.UTILITY.readConfigString("commands.time.timeChangedMessage")
                                .replaceAll("%newTime%", "DAY"));

                        return true;
                    }

                    if(args[0].equalsIgnoreCase("night")){

                        world.setFullTime(13000);
                        player.sendMessage(Essentials.UTILITY.readConfigString("commands.time.timeChangedMessage")
                                .replaceAll("%newTime%", "NIGHT"));

                        return true;
                    }

                    try{
                        final int ticks = Integer.parseInt(args[0]);

                        world.setFullTime(ticks);
                        player.sendMessage(Essentials.UTILITY.readConfigString("commands.time.timeChangedMessage")
                                .replaceAll("%newTime%", String.valueOf(ticks)));

                    }catch (NumberFormatException exception){
                        player.sendMessage(Essentials.getInstance().getPrefix() + "§cerror: " + exception.getMessage());
                    }
                    break;
                default:
                    player.sendMessage(Essentials.UTILITY.readConfigString("commands.time.usageMessage"));
                    break;
            }

        }else
            sender.sendMessage(Essentials.UTILITY.readConfigString("commands.time.consoleCannotExecuteCommandMessage"));


        return false;
    }

    @Override
    public BetterCommandType getType() {
        return BetterCommandType.ADMIN;
    }
}
