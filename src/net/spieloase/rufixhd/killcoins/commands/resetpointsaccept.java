package net.spieloase.rufixhd.killcoins.commands;

import net.spieloase.rufixhd.killcoins.killcoins;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class resetpointsaccept implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player p = (Player) sender;

        //Durch den Klick auf den Textcomponent werde die Coins auf 0 gesetzt
        killcoins.HMCoins.put(p.getUniqueId(), 0);
        p.sendMessage(killcoins.prefix + "Deine Coins wurden resetet§8!");
        return false;
    }
}
