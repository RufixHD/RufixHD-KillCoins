package net.spieloase.rufixhd.killcoins.cmds;

import net.spieloase.rufixhd.killcoins.killcoins;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class punkte implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player p = (Player) sender;

        //Abfrage wie viele Coins der Spieler hat
        Integer CoinsS = killcoins.HMCoins.get(p.getUniqueId());

        p.sendMessage(killcoins.prefix + "Du hast §c" + CoinsS + " §7Coins§8!");
        return false;
    }
}
