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
                    Integer kcoins = killcoins.HMCoins.get(killer.getUniqueId());
                    Integer dcoins = killcoins.HMCoins.get(e.getEntity().getUniqueId());
                    Integer add = killcoins.getInstance().getConfig().getInt("Spieleraddcoins");
                    Integer remove = killcoins.getInstance().getConfig().getInt("Spielerremoveprozent");
                    Integer kcoinsnew = kcoins + add;
                    Integer Prozent;

                    //Berechnung Prozentualeranteil
                    if (remove >= 100) {
                        Prozent = remove;
                    } else {
                        Prozent = remove / 100;
                    }
                    Integer newCoinsdeathplayer = dcoins * Prozent;

                    //Dem Killer Coins hinzufügen un dem Opfer die Coins prozentual entfernen
                    killcoins.HMCoins.put(killer.getUniqueId(), kcoinsnew);
                    killcoins.HMCoins.put(e.getEntity().getUniqueId(), newCoinsdeathplayer);
                    killer.sendMessage(killcoins.prefix + "Du hast für das töten von §c" + e.getEntity().getName() + " §6" + add + " §7Coins erhalten§8!");
                } else if (e.getEntity().getType().equals(EntityType.COW)) {
                    Integer kcoins = killcoins.HMCoins.get(killer.getUniqueId());
                    Integer kkcoins = killcoins.getInstance().getConfig().getInt("Kühekillcoins");
                    Integer kcoinsnew = kcoins + kkcoins;

                    //Dem Killer Coins hinzufügen
                    killcoins.HMCoins.put(killer.getUniqueId(), kcoinsnew);
                    killer.sendMessage(killcoins.prefix + "Du hast für das töten von einer §cKuh §6" + kkcoins + " §7Coins erhalten§8!");
                }
            }
        }
    }
}
