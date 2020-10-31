package net.baumarkt.advanced.essentials.commands;

import net.baumarkt.advanced.essentials.Essentials;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandExecutor;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandType;
import net.baumarkt.advanced.essentials.utils.itemstack.ItemBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MoreCommand implements BetterCommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){

            final Player player = (Player) sender;

            if(!player.hasPermission(Essentials.UTILITY.readConfigString("commands.more.permission"))){
                player.sendMessage(Essentials.UTILITY.readConfigString("commands.more.noPermissionMessage"));
                return true;
            }

            player.getInventory().getItem(player.getInventory().getHeldItemSlot()).setAmount(64);
            player.sendMessage(Essentials.UTILITY.readConfigString("commands.more.moreSuccessfully"));

        }else
            sender.sendMessage(Essentials.UTILITY.readConfigString("commands.more.consoleCannotExecuteCommandMessage"));


        return false;
    }

    @Override
    public BetterCommandType getType() {
        return BetterCommandType.ADMIN;
    }
}
