package net.spieloase.rufixhd.killcoins.events;

import net.spieloase.rufixhd.killcoins.killcoins;
import net.spieloase.rufixhd.killcoins.methoden.mysql.methoden;
import net.spieloase.rufixhd.killcoins.scoreboard.setScoreboard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onjoin implements Listener{
    @EventHandler
    private void onjoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        //Wenn ein Spieler das erstemal joint, wird sein Eintrag in der Datenbank erstellt, damit im Scoreboard nicht -1 steht
        //und die Datnebank wird in eine HashMap geladen, damit man nicht immer auf die Datenbank zugreifen muss
        if(methoden.getCoins(p) != -1){
            killcoins.HMCoins.put(p.getUniqueId(), methoden.getCoins(p));
            setScoreboard.setBoard(p);
        } else {
            methoden.setCoins(p, 0);
            killcoins.HMCoins.put(p.getUniqueId(), 0);
            setScoreboard.setBoard(p);
        }
    }
}
