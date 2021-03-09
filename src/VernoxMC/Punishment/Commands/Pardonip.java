package VernoxMC.Punishment.Commands;

import VernoxMC.Punishment.Main;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.permission.BanList;
import cn.nukkit.utils.TextFormat;
import java.util.Locale;


public class Pardonip extends PluginCommand<Main> {

    public Pardonip(Main owner) {
        super("pardonip", owner);
        this.setPermission("punishment.pardonip");
        this.commandParameters.clear();
        this.commandParameters.put("default",
                new CommandParameter[]{
                        new CommandParameter("player",   false),
                        new CommandParameter("reason", true)
                });
        this.setAliases(new String[]{"unbanip", "pardon-ip", "unban-ip"});
        this.setDescription("pardonip (ip)");
    }

    public boolean execute(CommandSender sender, String s, String[] args) {
        if(!sender.hasPermission("punishment.pardonip")){
            sender.sendMessage(TextFormat.RED + "You do not have permission to use this command!");
            return false;
        }
        if(args.length < 1){
            sender.sendMessage(TextFormat.RED + "Correct Usage: /pardon (ip)");
            return false;
        }
        String name = args[0].toLowerCase(Locale.ROOT);
        BanList banmanagement = sender.getServer().getIPBans();
        if(banmanagement.isBanned(name.toLowerCase())){
            sender.getServer().broadcastMessage(TextFormat.RED + "----------------------" + TextFormat.AQUA + " has been ip unbanned from the server!");
            sender.getServer().getIPBans().remove(name);

            return false;
        }
        sender.sendMessage(TextFormat.RED + name + TextFormat.AQUA + " is not ip banned!");
        return false;
    }

}
