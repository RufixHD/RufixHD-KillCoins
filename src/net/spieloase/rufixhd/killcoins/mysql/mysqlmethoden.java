package net.spieloase.rufixhd.killcoins.mysql;

import net.spieloase.rufixhd.killcoins.killcoins;
import org.bukkit.entity.Player;

import java.sql.*;

public class mysqlmethoden {
    public static Connection con;

    //Angaben aus der Config abfragen
    private static String DBhost = killcoins.getInstance().getConfig().getString("MySQL.DBhost");
    private static String DBname = killcoins.getInstance().getConfig().getString("MySQL.DBname");
    private static String DBport = killcoins.getInstance().getConfig().getString("MySQL.DBport");
    private static String DBuser = killcoins.getInstance().getConfig().getString("MySQL.DBuser");
    private static String DBpass = killcoins.getInstance().getConfig().getString("MySQL.DBpass");

    //Methode zum Abfragen ob die Datenbank schon verbunden ist
    public static boolean isConnect() {
        return (con == null ? false : true);
    }


    //Methode um die Datenbank zu verbinden
    public static void connect() {
        if(!isConnect()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + DBhost + ":" + DBport + "/" + DBname + "?autoReconnect=true", DBuser , DBpass);
                System.out.println("[MySQL] Verbinndung erfolgreich!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //Methode um die Datenbankverbindung zu trennen
    public static void disconnect() {
        if(isConnect()) {
            try {
                con.close();
                System.out.println("[MySQL] Verbinndung getrennt!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //Methode um die MySQLtabelle zu erstellen
    public static void createTable() {
        try {
            con.prepareStatement("CREATE TABLE IF NOT EXISTS COINS (UUID VARCHAR(100), COINS INT(16))").executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Methode um die Coins eines Spieler abzufragen
    public static int getCoins(Player p) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT COINS FROM COINS WHERE UUID = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return (rs.getInt("COINS"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    //Methode um die Coins eines Spieler zu setzen
    public static void setCoins(Player p, int anzahl) {

        if(getCoins(p) == -1) {
            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO COINS (UUID,COINS) VALUES (?,?)");
                ps.setString(1, p.getUniqueId().toString());
                ps.setInt(2, anzahl);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                PreparedStatement ps = con.prepareStatement("UPDATE COINS SET COINS = ? WHERE UUID = ?");
                ps.setString(2, p.getUniqueId().toString());
                ps.setInt(1, anzahl);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
