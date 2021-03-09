package VernoxMC.Punishment.Listeners;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerPreLoginEvent;
import cn.nukkit.permission.BanEntry;
import cn.nukkit.permission.BanList;
import cn.nukkit.utils.TextFormat;
import java.util.LinkedHashMap;

public class Join implements Listener {
    @EventHandler
    public void on(PlayerPreLoginEvent event) {
        BanList banmanagement = event.getPlayer().getServer().getNameBans();
        if(banmanagement.isBanned(event.getPlayer().getName().toLowerCase())){
            LinkedHashMap<String, BanEntry> getentry = banmanagement.getEntires();
            BanEntry baninfo = getentry.get(event.getPlayer().getName().toLowerCase());
            if(String.valueOf(baninfo.getExpirationDate()) == "null"){
            event.getPlayer().kick(TextFormat.RED + "You are currently Perm Banned from the server" + "\n" + TextFormat.AQUA + "Reason:" + baninfo.getReason());
        } else{
            event.getPlayer().kick(TextFormat.RED + "You are currently Banned from the server" + "\n" + TextFormat.AQUA + "Reason:" + baninfo.getReason() + "\n" + TextFormat.RED + "Expires:" + baninfo.getExpirationDate());

        }
    }
   BanList banipmanagement = event.getPlayer().getServer().getIPBans();
        if(banipmanagement.isBanned(event.getPlayer().getAddress())){
            LinkedHashMap<String, BanEntry> getipentry = banipmanagement.getEntires();
            BanEntry banipinfo = getipentry.get(event.getPlayer().getAddress());
            event.getPlayer().kick(TextFormat.RED + "You are currently IP Banned from the server" + "\n" + TextFormat.AQUA + "Reason: " + banipinfo.getReason() +"\n" + TextFormat.AQUA + "Banned By: " + banipinfo.getSource());

        }
}
}