package net.baumarkt.advanced.essentials.commands;

import net.baumarkt.advanced.essentials.Essentials;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandExecutor;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandType;
import net.baumarkt.advanced.essentials.utils.Utility;
import net.baumarkt.advanced.essentials.utils.player.EssentialsPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements BetterCommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){

            final Player player = (Player) sender;
            final EssentialsPlayer essentialsPlayer = Utility.getPlayer(player.getUniqueId());

            if(!player.hasPermission(Essentials.UTILITY.readConfigString("commands.fly.permission"))){
                player.sendMessage(Essentials.UTILITY.readConfigString("commands.fly.noPermissionMessage"));
                return true;
            }

            switch (args.length){
                case 0:

                    if(essentialsPlayer.isCanFly()){
                        essentialsPlayer.setCanFly(false);
                        player.sendMessage(Essentials.UTILITY.readConfigString("commands.fly.flyUnactivatedMessage"));
                    }else{
                        essentialsPlayer.setCanFly(true);
                        player.sendMessage(Essentials.UTILITY.readConfigString("commands.fly.flyActivatedMessage"));
                    }

                    break;
                case 1:

                    if(Bukkit.getPlayer(args[0]) == null){
                        player.sendMessage(Essentials.UTILITY.readConfigString("commands.fly.playerNotFoundMessage"));
                        return true;
                    }

                    final Player target = Bukkit.getPlayer(args[0]);
                    final EssentialsPlayer essentialsTarget = Utility.getPlayer(target.getUniqueId());

                    if(essentialsTarget.isCanFly()){
                        player.sendMessage(Essentials.UTILITY.readConfigString("commands.fly.flyFromOtherPlayerUnactivatedMessage"));
                        essentialsTarget.setCanFly(false);
                    }else{
                        player.sendMessage(Essentials.UTILITY.readConfigString("commands.fly.flyFromOtherPlayerActivatedMessage"));
                        essentialsTarget.setCanFly(true);
                    }

                    break;
            }

        }else
            sender.sendMessage(Essentials.UTILITY.readConfigString("commands.fly.consoleCannotExecuteCommandMessage"));


        return false;
    }

    @Override
    public BetterCommandType getType() {
        return BetterCommandType.ADMIN;
    }
}
