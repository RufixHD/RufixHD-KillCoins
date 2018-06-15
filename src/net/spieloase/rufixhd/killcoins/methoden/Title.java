package net.spieloase.rufixhd.killcoins.methoden;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Title {
    //Methode um den Titel an den Spieler zu senden
    public static void sendTitle(Player p, String title, String subtitle) {
        PlayerConnection con = ((CraftPlayer)p).getHandle().playerConnection;
        IChatBaseComponent titlemsg = IChatBaseComponent.ChatSerializer.a("{'text':'" + title + "'}");
        IChatBaseComponent subtitlemsg = IChatBaseComponent.ChatSerializer.a("{'text':'" + subtitle + "'}");
        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titlemsg);
        PacketPlayOutTitle titlePacket2 = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitlemsg);
        con.sendPacket(titlePacket);
        con.sendPacket(titlePacket2);

    }
}
