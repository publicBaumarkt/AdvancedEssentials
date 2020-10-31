package net.baumarkt.advanced.essentials.commands;

import net.baumarkt.advanced.essentials.commands.etc.BetterCommandExecutor;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Deprecated
public class ExpCommand implements BetterCommandExecutor {

    /**
     * TODO
     * @param commandSender
     * @param command
     * @param s
     * @param args
     * @return
     */
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player){

        }

        return false;
    }

    @Override
    public BetterCommandType getType() {
        return null;
    }
}
