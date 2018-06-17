package net.spieloase.rufixhd.killcoins.events;

import net.spieloase.rufixhd.killcoins.killcoins;
import net.spieloase.rufixhd.killcoins.methoden.mysql.methoden;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class onquit implements Listener {
    @EventHandler
    public void onleave(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        //Die erspielten Coins in die Datenbank eintragen
        if (killcoins.HMCoins.containsKey(p.getUniqueId())) {
            Integer Coins = killcoins.HMCoins.get(p.getUniqueId());
            methoden.setCoins(p, Coins);
        }
    }
}
