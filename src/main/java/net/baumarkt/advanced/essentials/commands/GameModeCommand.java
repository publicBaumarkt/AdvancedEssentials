package net.baumarkt.advanced.essentials.commands;

import net.baumarkt.advanced.essentials.Essentials;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandExecutor;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandType;
import net.baumarkt.advanced.essentials.utils.enums.EssentialsGameMode;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeCommand implements BetterCommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            final Player player = (Player) sender;

            if(!player.hasPermission(Essentials.UTILITY.readConfigString("commands.gamemode.permission"))){
                player.sendMessage(Essentials.UTILITY.readConfigString("commands.gamemode.noPermissionMessage"));
                return true;
            }

            switch (args.length){
                case 1:
                    changeGameMode(player, args, null);
                    break;
                case 2:
                    if(Bukkit.getPlayer(args[1]) == null){
                        player.sendMessage(Essentials.UTILITY.readConfigString("commands.gamemode.playerNotFoundMessage"));
                        return true;
                    }

                    changeGameMode(player, args, Bukkit.getPlayer(args[1]));
                    break;
                default:
                    player.sendMessage(Essentials.UTILITY.readConfigString("commands.gamemode.usageMessage"));
                    break;
            }

        }else
            sender.sendMessage(Essentials.UTILITY.readConfigString("commands.gamemode.consoleCannotExecuteCommandMessage"));


        return false;
    }

    private void changeGameMode(final Player player, final String args[], final Player playerTarget){
        if(playerTarget == null){
            for (EssentialsGameMode value : EssentialsGameMode.values()) {

                if (args[0].equalsIgnoreCase(value.getName())
                        || args[0].equalsIgnoreCase(String.valueOf(value.getNumber()))) {

                    player.setGameMode(value.getGameMode());
                    player.sendMessage(Essentials.UTILITY.readConfigString("commands.gamemode.gameModeChangedMessage")
                            .replaceAll("%gameMode%", player.getGameMode().name()));
                    return;
                }
            }

            player.sendMessage(Essentials.UTILITY.readConfigString("commands.gamemode.gameModeNotFoundMessage"));
            return;
        }

        for (EssentialsGameMode value : EssentialsGameMode.values()) {

            if (args[0].equalsIgnoreCase(value.getName())
                    || args[0].equalsIgnoreCase(String.valueOf(value.getNumber()))) {

                playerTarget.setGameMode(value.getGameMode());
                player.sendMessage(Essentials.UTILITY.readConfigString("commands.gamemode.gameModeChangedFromPlayerMessage")
                        .replaceAll("%gameMode%", playerTarget.getGameMode().name()));
                return;
            }
        }

        player.sendMessage(Essentials.UTILITY.readConfigString("commands.gamemode.gameModeNotFoundMessage"));
    }

    @Override
    public BetterCommandType getType() {
        return BetterCommandType.ADMIN;
    }
}
