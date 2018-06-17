package net.spieloase.rufixhd.killcoins.scoreboard;

import net.spieloase.rufixhd.killcoins.killcoins;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;

public class setScoreboard{

    public static HashMap<Scoreboard, Player> Scoreboards = new HashMap<>();

    //Methode um das Scoreboard zu setzen
    public static void setBoard(Player p){
        //Neues Scorebaord erstellen
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("aaa", "bbb");

        //Einstellungen für das Scorebaord festlegen
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§8» §2" + p.getName());

        //Zeile mit Prefix und Suffix setzen, damit man das Scoreabord leicht ohne flackern updaten lassen kann
        Team Coins = board.registerNewTeam("Coins");
        Coins.setPrefix(" §8➥ ");
        Coins.setSuffix("§a" + killcoins.HMCoins.get(p.getUniqueId()));
        Coins.addEntry(ChatColor.RED.toString());

        obj.getScore(ChatColor.RED.toString()).setScore(0);

        Scoreboards.put(board, p);

        p.setScoreboard(board);
    }

    //Methode um das Scoreabord flackerfrei zu updaten (könnte man auch nur ausführen lassen, wenn sich was an den Coins ändert (noch performanter)
    public static void updateBoard() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Scoreboard board : Scoreboards.keySet()) {
                    Player p = Scoreboards.get(board);
                    //Suffix des Teams wird geändert
                    board.getTeam("Coins").setSuffix("§a" + killcoins.HMCoins.get(p.getUniqueId()));
                }
            }
        }.runTaskTimer(killcoins.getInstance(), 0, 10);
    }
}
