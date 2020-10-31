package net.baumarkt.advanced.essentials.commands.etc;

import org.bukkit.command.CommandExecutor;

public interface BetterCommandExecutor extends CommandExecutor {

    BetterCommandType getType();

}
