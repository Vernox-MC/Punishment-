package VernoxMC.Punishment.Commands;

import VernoxMC.Punishment.Main;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.event.player.PlayerKickEvent;
import cn.nukkit.utils.TextFormat;
import java.util.Locale;


public class KickCommand extends PluginCommand<Main> {

    public KickCommand(Main owner) {
        super("kick", owner);
        this.setPermission("punishment.kick");
        this.commandParameters.clear();
        this.commandParameters.put("default",
                new CommandParameter[]{
                        new CommandParameter("player",   false),
                        new CommandParameter("reason", true)
                });
        this.setDescription("kick (player) (reason)");
    }

    public boolean execute(CommandSender sender, String s, String[] args) {
        if(!sender.hasPermission("punishment.kick")){
            sender.sendMessage(TextFormat.RED + "You do not have permission to use this command!");
            return false;
        }
        if(args.length < 2){
            sender.sendMessage(TextFormat.RED + "Correct Usage: /kick (name) (reason)");
            return false;
        }
        String name = args[0];
        String reason = "";
        for (int i = 1; i < args.length; i++) {
            reason += args[i] + " ";
        }
        if (reason.length() < 1) {
            sender.sendMessage(TextFormat.RED + "You must have a valid reason to kick!");
            return false;
        }

        reason = reason.substring(0, reason.length() - 1);



        Player player = sender.getServer().getPlayer(name);
        if(player != null) {
            player.kick(TextFormat.AQUA + "You have been Kicked from the Server"+ "\n" + TextFormat.RED + "Reason: " + reason);
            sender.getServer().broadcastMessage(TextFormat.RED + player.getName() + TextFormat.AQUA + " has been kicked from the server!");
            return false;
        }
        sender.sendMessage(TextFormat.RED + name + " is not online!");
        return false;
    }

}
