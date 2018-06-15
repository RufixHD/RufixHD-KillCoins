package net.spieloase.rufixhd.killcoins.commands;

import net.spieloase.rufixhd.killcoins.killcoins;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class set implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player p = (Player) sender;

        //Befehl um die config ingame einzustellen
        if (p.isOp()) {
            if (args.length == 0) {
                p.sendMessage(killcoins.prefix + "Benutze§8: §c/set DBname/DBhost/DBport/DBuser/DBpass STRING");
                p.sendMessage(killcoins.prefix + "Benutze§8: §c/set kühecoins INT");
                p.sendMessage(killcoins.prefix + "Benutze§8: §c/set spielercoins INT");
                p.sendMessage(killcoins.prefix + "Benutze§8: §c/set removeprozent INT");
            } else if (args.length == 2 && args[0].equals("DBname")) {
                killcoins.getInstance().getConfig().set("MySQL.DBname", args[1]);
                killcoins.getInstance().saveConfig();
                sender.sendMessage(killcoins.prefix + "Datenbankname wurder auf §c" + args[1] + " §7gesetzt§8!");
            } else if (args.length == 2 && args[0].equals("DBhost")) {
                killcoins.getInstance().getConfig().set("MySQL.DBhost", args[1]);
                killcoins.getInstance().saveConfig();
                sender.sendMessage(killcoins.prefix + "Datenbankhost wurder auf §c" + args[1] + " §7gesetzt§8!");
            } else if (args.length == 2 && args[0].equals("DBport")) {
                killcoins.getInstance().getConfig().set("MySQL.DBport", args[1]);
                killcoins.getInstance().saveConfig();
                sender.sendMessage(killcoins.prefix + "Datenbankport wurder auf §c" + args[1] + " §7gesetzt§8!");
            } else if (args.length == 2 && args[0].equals("DBuser")) {
                killcoins.getInstance().getConfig().set("MySQL.DBuser", args[1]);
                killcoins.getInstance().saveConfig();
                sender.sendMessage(killcoins.prefix + "Datenbankuser wurder auf §c" + args[1] + " §7gesetzt§8!");
            } else if (args.length == 2 && args[0].equals("DBpass")) {
                killcoins.getInstance().getConfig().set("MySQL.DBpass", args[1]);
                killcoins.getInstance().saveConfig();
                sender.sendMessage(killcoins.prefix + "Datenbankpass wurder auf §c" + args[1] + " §7gesetzt§8!");
            } else if (args.length == 2 && args[0].equals("kühecoins")) {
                killcoins.getInstance().getConfig().set("Kühekillcoins", Integer.valueOf(args[1]));
                killcoins.getInstance().saveConfig();
                sender.sendMessage(killcoins.prefix + "Jetzt bekommt man beim töten einer Kuh §c" + killcoins.getInstance().getConfig().getInt("Kühekillcoins") + " §7Coins§8!");
            } else if (args.length == 2 && args[0].equals("spielercoins")) {
                killcoins.getInstance().getConfig().set("Spielerkillcoins", Integer.valueOf(args[1]));
                killcoins.getInstance().saveConfig();
                sender.sendMessage(killcoins.prefix + "Jetzt bekommt man beim töten eines Spieler §c" + killcoins.getInstance().getConfig().getInt("Spielerkillcoins") + " §7Coins§8!");
            } else if (args.length == 2 && args[0].equals("removeprozent")) {
                killcoins.getInstance().getConfig().set("Spielerprozentremove", Integer.valueOf(args[1]));
                killcoins.getInstance().saveConfig();
                p.sendMessage(killcoins.prefix + "Jetzt werden beim beim getötetten Spieler §c" + killcoins.getInstance().getConfig().getInt("Spielerprozentremove") + "% §7entfernt§8!");
            }
        }
        return false;
    }
}
