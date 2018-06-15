package net.spieloase.rufixhd.killcoins;

import net.spieloase.rufixhd.killcoins.commands.*;
import net.spieloase.rufixhd.killcoins.events.onjoin;
import net.spieloase.rufixhd.killcoins.events.onkill;
import net.spieloase.rufixhd.killcoins.events.onquit;
import net.spieloase.rufixhd.killcoins.methoden.Autonachricht;
import net.spieloase.rufixhd.killcoins.mysql.mysqlmethoden;
import net.spieloase.rufixhd.killcoins.scoreboard.setScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class killcoins extends JavaPlugin implements Listener{

    public static killcoins instance;

    @Override
    public void onEnable() {

        //instance auf diese Class setzen
        instance = this;

        //EventClassregister vereinfachen
        PluginManager re = Bukkit.getServer().getPluginManager();

        FileConfiguration config = this.getConfig();

        //EventClass registrieren
        re.registerEvents(this, this);
        re.registerEvents(new onjoin(), this);
        re.registerEvents(new onquit(), this);
        re.registerEvents(new onkill(), this);

        //Commands registrieren
        getServer().getPluginCommand("differenz").setExecutor(new differenz());
        getServer().getPluginCommand("punkte").setExecutor(new punkte());
        getServer().getPluginCommand("punkte").setExecutor(new punkte());
        getServer().getPluginCommand("resetpoints").setExecutor(new resetpoints());
        getServer().getPluginCommand("rpa").setExecutor(new resetpointsaccept());
        getServer().getPluginCommand("set").setExecutor(new set());

        //Startnachricht
        System.out.println("[KillCoins] Plugin wurde aktiviert!");

        //Configdefaults kopieren und speichern
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        //Datenbank verbinden und erstellen, wenn nicht vorhanden
        mysqlmethoden.connect();
        mysqlmethoden.createTable();

        //Start Scoreboardupdater
        setScoreboard.updateBoard();

        //Start Autonachricht
        Autonachricht.onautomessage();
    }

    @Override
    public void onDisable() {
        //Stopnachricht
        System.out.println("[KillCoins] Plugin wurde deaktiviert!");

        for(Player all : Bukkit.getOnlinePlayers()){
            if (killcoins.HMCoins.containsKey(all.getUniqueId())) {
                Integer newCoins = killcoins.HMCoins.get(all.getUniqueId());

                mysqlmethoden.setCoins(all, newCoins);
            }
        }
        //Datenbank trennen
        mysqlmethoden.disconnect();
    }

    public static killcoins getInstance() {
        return instance;
    }

    //HashMap um die Coins der Spieler zu speichern, damit das System performanter ist
    public static HashMap<UUID, Integer> HMCoins = new HashMap<>();

    //Prefix String
    public static String prefix = "§8» §4§lKillCoins§8 ● §7";
}
