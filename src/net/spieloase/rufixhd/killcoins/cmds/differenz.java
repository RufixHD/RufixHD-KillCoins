package net.spieloase.rufixhd.killcoins.cmds;

import net.spieloase.rufixhd.killcoins.killcoins;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class differenz implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player p = (Player) sender;
        Player po = Bukkit.getPlayer(args[0]);

        //Differenz berechnen
        Integer CoinsS = killcoins.HMCoins.get(p.getUniqueId());
        Integer CoinsO = killcoins.HMCoins.get(po.getUniqueId());
        Integer Coinsd = CoinsS - CoinsO;

        //Textausgabe über den Kommand
        if (args.length == 0) {
            p.sendMessage(killcoins.prefix + "Benutze§8: §c/differenz SPIELERNAME");
        } else if (args.length == 1) {
            p.sendMessage(killcoins.prefix + "Die Differenz zwischen den Coins von §cdir §7und §c" + po.getName() + " §7beträt §c" + Coinsd + " §7Coins§8!");
        }
        return false;
    }
}
