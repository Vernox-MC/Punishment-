package VernoxMC.Punishment.Listeners;

import VernoxMC.Punishment.Main;
import VernoxMC.Punishment.Utils.Api;
import VernoxMC.Punishment.Utils.MuteList;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.permission.BanEntry;
import cn.nukkit.utils.TextFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;

public class Chat implements Listener {
    private final Main plugin;
    public Chat(Main plugin) {
        this.plugin = plugin;
    }


    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void on(PlayerChatEvent event) {
        MuteList Manager = Api.getNameMutes();
        if(Manager.isBanned(event.getPlayer().getName().toLowerCase(Locale.ROOT))){
            LinkedHashMap<String, BanEntry> getentry = Manager.getEntires();
            BanEntry baninfo = getentry.get(event.getPlayer().getName().toLowerCase());
            String reason = baninfo.getReason();
            Date expires = baninfo.getExpirationDate();
            if(expires == null){
                event.getPlayer().sendMessage(TextFormat.RED + "You are currently muted for " + TextFormat.AQUA + reason);
                event.setCancelled(true);
                return;
            }
            event.getPlayer().sendMessage(TextFormat.RED + "You are currently muted for " + TextFormat.AQUA + reason + "\n" + TextFormat.RED + "Mute Expires on: " + TextFormat.GREEN + expires);
            event.setCancelled(true);
        }
    }
}
