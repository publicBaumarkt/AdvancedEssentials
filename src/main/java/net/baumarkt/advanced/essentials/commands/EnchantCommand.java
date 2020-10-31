package net.baumarkt.advanced.essentials.commands;

import com.google.common.collect.Lists;
import net.baumarkt.advanced.essentials.Essentials;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandExecutor;
import net.baumarkt.advanced.essentials.commands.etc.BetterCommandType;
import org.bukkit.Bukkit;
import org.bukkit.TreeType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class EnchantCommand implements BetterCommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){

            final Player player = (Player) sender;

            if(!player.hasPermission(Essentials.UTILITY.readConfigString("commands.enchant.permission"))){
                player.sendMessage(Essentials.UTILITY.readConfigString("commands.enchant.noPermissionMessage"));
                return true;
            }

            final ItemStack itemInHand = player.getInventory().getItem(player.getInventory().getHeldItemSlot());

            switch (args.length){
                case 1:
                    if(args[0].equalsIgnoreCase("list")){
                        final Collection<String> enchantmentNameSpaces = Lists.newArrayList();

                        for (Enchantment value : Enchantment.values())
                            enchantmentNameSpaces.add(value.getKey().getKey());

                        final String idList = enchantmentNameSpaces.toString();
                        final String replace = idList.substring(1, idList.length() -1).replace(", ", ",");

                        player.sendMessage(Essentials.UTILITY.readConfigString("commands.enchant.listMessage").replaceAll("%enchantmentTypes%", replace));
                    }
                    break;
                case 2:

                    try {
                        final int level = Integer.parseInt(args[1]);

                        for (Enchantment value : Enchantment.values()) {
                            if (args[0].equals(value.getKey().getKey())) {
                                assert itemInHand != null;
                                itemInHand.addEnchantment(value, level);
                                player.sendMessage(Essentials.UTILITY.readConfigString("commands.enchant.itemEnchantedMessage"));
                                return true;
                            }
                        }

                        player.sendMessage(Essentials.UTILITY.readConfigString("commands.enchant.unknownEnchantmentTypeMessage"));

                    }catch (NullPointerException exception){
                        player.sendMessage(Essentials.getInstance().getPrefix() + "§cError: " + exception.getMessage());
                    }

                    break;
                case 3:

                    if(Bukkit.getPlayer(args[2]) == null){
                        player.sendMessage(Essentials.UTILITY.readConfigString("commands.enchant.playerNotFoundMessage"));
                        return true;
                    }

                    final Player target = Bukkit.getPlayer(args[2]);
                    final ItemStack targetItemInHand = target.getInventory().getItem(target.getInventory().getHeldItemSlot());

                    try {
                        final int level = Integer.parseInt(args[1]);

                        for (Enchantment value : Enchantment.values()) {
                            if (args[0].equals(value.getKey().getKey())) {
                                assert targetItemInHand != null;
                                targetItemInHand.addEnchantment(value, level);
                                player.sendMessage(Essentials.UTILITY.readConfigString("commands.enchant.itemEnchantedFromPlayerMessage"));
                                return true;
                            }
                        }

                        player.sendMessage(Essentials.UTILITY.readConfigString("commands.enchant.unknownEnchantmentTypeMessage"));

                    }catch (NullPointerException exception){
                        player.sendMessage(Essentials.getInstance().getPrefix() + "§cError: " + exception.getMessage());
                    }

                    break;
                default:
                    player.sendMessage(Essentials.UTILITY.readConfigString("commands.enchant.usageMessage"));
                    break;
            }

        }else
            sender.sendMessage(Essentials.UTILITY.readConfigString("commands.enchant.consoleCannotExecuteCommandMessage"));

        return false;
    }

    @Override
    public BetterCommandType getType() {
        return BetterCommandType.ADMIN;
    }
}
