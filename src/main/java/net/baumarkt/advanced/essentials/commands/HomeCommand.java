package net.baumarkt.advanced.essentials.commands;

import net.baumarkt.advanced.essentials.Essentials;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandExecutor;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandType;
import net.baumarkt.advanced.essentials.utils.Utility;
import net.baumarkt.advanced.essentials.utils.player.EssentialsPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements BetterCommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player){

            final Player player = (Player) commandSender;
            final EssentialsPlayer essentialsPlayer = Utility.getPlayer(player.getUniqueId());

            if(Essentials.UTILITY.readConfigBoolean("commands.home.usePermission")){

                if(!player.hasPermission(Essentials.UTILITY.readConfigString("commands.home.permission"))){
                    player.sendMessage(Essentials.UTILITY.readConfigString("commands.home.noPermissionMessage"));
                    return true;
                }
            }

            switch (args.length){
                case 0:

                    break;
                default:
                    if(Essentials.UTILITY.readConfigBoolean("commands.home.useUI")){

                    }



                    break;
            }


        }else
            commandSender.sendMessage(Essentials.UTILITY.readConfigString("commands.home.consoleCannotExecuteCommandMessage"));

        return false;
    }

    @Override
    public BetterCommandType getType() {
        return BetterCommandType.BASIC;
    }
}
