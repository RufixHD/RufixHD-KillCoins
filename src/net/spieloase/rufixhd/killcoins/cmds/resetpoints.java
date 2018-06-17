package net.spieloase.rufixhd.killcoins.cmds;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.spieloase.rufixhd.killcoins.killcoins;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class resetpoints implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player p = (Player) sender;

        //Klickbarer TextComponent erstellen und senden
        TextComponent tc = new TextComponent();
        tc.setText(killcoins.prefix + "Hier Klicken um deine Coins zu reseten§8!");
        tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/rpa"));
        p.spigot().sendMessage(tc);

        return false;
    }
}
