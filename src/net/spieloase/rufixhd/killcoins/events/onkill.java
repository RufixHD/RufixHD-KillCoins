package net.spieloase.rufixhd.killcoins.events;

import net.spieloase.rufixhd.killcoins.killcoins;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class onkill implements Listener {
    @EventHandler
    public static void onkill(EntityDeathEvent e) {
        Player killer = e.getEntity().getKiller();

        if (killer instanceof Player) {
            if (e.getEntity().getType() == EntityType.COW || e.getEntity() instanceof Player) {
                if (e.getEntity() instanceof Player) {
                    Integer Coinskiller = killcoins.HMCoins.get(killer.getUniqueId());
                    Integer Coinsdeath = killcoins.HMCoins.get(e.getEntity().getUniqueId());
                    Integer Spielerkillcoins = killcoins.getInstance().getConfig().getInt("Spielerkillcoins");
                    Integer SpielerOpferremove = killcoins.getInstance().getConfig().getInt("SpielerOpferremove");
                    Integer newCoinskillerplayer = Coinskiller + Spielerkillcoins;
                    Integer Prozent;

                    //Berechnung Prozentualeranteil
                    if (SpielerOpferremove >= 100) {
                        Prozent = SpielerOpferremove;
                    } else {
                        Prozent = SpielerOpferremove / 100;
                    }
                    Integer newCoinsdeathplayer = Coinsdeath * Prozent;

                    //Dem Killer Coins hinzufügen un dem Opfer die Coins prozentual entfernen
                    killcoins.HMCoins.put(killer.getUniqueId(), newCoinskillerplayer);
                    killcoins.HMCoins.put(e.getEntity().getUniqueId(), newCoinsdeathplayer);
                    killer.sendMessage(killcoins.prefix + "Du hast für das töten von §c" + e.getEntity().getName() + " §6" + Spielerkillcoins + " §7Coins erhalten§8!");
                } else if (e.getEntity().getType().equals(EntityType.COW)) {
                    Integer Coinskiller = killcoins.HMCoins.get(killer.getUniqueId());
                    Integer Kühekillcoins = killcoins.getInstance().getConfig().getInt("Kühekillcoins");
                    Integer newCoinskillerkühe = Coinskiller + Kühekillcoins;

                    //Dem Killer Coins hinzufügen
                    killcoins.HMCoins.put(killer.getUniqueId(), newCoinskillerkühe);
                    killer.sendMessage(killcoins.prefix + "Du hast für das töten von einer §cKuh §6" + Kühekillcoins + " §7Coins erhalten§8!");
                }
            }
        }
    }
}
