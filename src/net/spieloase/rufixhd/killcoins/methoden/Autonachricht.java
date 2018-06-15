package net.spieloase.rufixhd.killcoins.methoden;

import net.spieloase.rufixhd.killcoins.killcoins;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Autonachricht {
    public static void onautomessage(){

        //unedliche Schleife, die jede 12000Tiks/600Sekunden ausgeführt wird
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    Integer CoinsSelf = killcoins.HMCoins.get(all.getUniqueId());

                    //Abfragen ob es eine ungerade oder gerade Zahl ist
                    if (CoinsSelf % 2 == 0) {
                        all.sendMessage(killcoins.prefix + "Du hast §c" + CoinsSelf + " §7Coins§8!");
                    } else if (CoinsSelf % 2 == 1) {
                        Title.sendTitle(all, " ", "§7Du hast §c" + CoinsSelf + " §7Coins§8!");
                    }
                }
            }
        }.runTaskTimer(killcoins.getInstance(), 0, 12000);
    }
}

