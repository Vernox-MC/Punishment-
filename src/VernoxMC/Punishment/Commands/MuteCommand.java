package VernoxMC.Punishment.Commands;

import VernoxMC.Punishment.Main;
import VernoxMC.Punishment.Utils.Api;
import VernoxMC.Punishment.Utils.MuteList;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.permission.BanList;
import cn.nukkit.utils.TextFormat;
import java.util.Locale;



public class MuteCommand extends PluginCommand<Main> {

    public MuteCommand(Main owner) {
        super("mute", owner);
        this.setPermission("punishment.mute");
        this.commandParameters.clear();
        this.commandParameters.put("default",
                new CommandParameter[]{
                        new CommandParameter("player",   false),
                        new CommandParameter("reason", true)
                });
        this.setDescription("mute (player) (reason)");
    }

    public boolean execute(CommandSender sender, String s, String[] args) {
        if(!sender.hasPermission("punishment.ban")){
            sender.sendMessage(TextFormat.RED + "You do not have permission to use this command!");
            return false;
        }
        if(args.length < 2){
            sender.sendMessage(TextFormat.RED + "Correct Usage: /mute (name) (reason)");
            return false;
        }
        String name = args[0];
        String reason = "";
        for (int i = 1; i < args.length; i++) {
            reason += args[i] + " ";
        }
        if (reason.length() < 1) {
            sender.sendMessage(TextFormat.RED + "You must have a valid reason to mute!");
            return false;
        }

        reason = reason.substring(0, reason.length() - 1);
        MuteList Manager = Api.getNameMutes();


        Player player = sender.getServer().getPlayerExact(name);
        BanList banmanagement = Api.getNameMutes();

        if(player != null) {
            if(banmanagement.isBanned(player.getName().toLowerCase())){
                sender.sendMessage(TextFormat.RED + player.getName() + TextFormat.AQUA + " is already muted!");
                return false;
            }
            Manager.addMute(player.getName().toLowerCase(Locale.ROOT), reason, null, sender.getName());
            sender.getServer().broadcastMessage(TextFormat.RED + player.getName() + TextFormat.AQUA + " has been perm muted from the server!");
            return false;
        }
        if(banmanagement.isBanned(name.toLowerCase())){
            sender.sendMessage(TextFormat.RED + name + TextFormat.AQUA + " is already muted!");
            return false;
        }
        sender.getServer().broadcastMessage(TextFormat.RED + name + TextFormat.AQUA + " has been perm muted from the server!");
        Manager.addMute(name.toLowerCase(Locale.ROOT), reason, null, sender.getName());
        return false;
    }

}

