package VernoxMC.Punishment.Commands;

import VernoxMC.Punishment.Main;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.permission.BanList;
import cn.nukkit.utils.TextFormat;
import java.util.Locale;


public class PardonCommand extends PluginCommand<Main> {

    public PardonCommand(Main owner) {
        super("pardon", owner);
        this.setPermission("punishment.pardon");
        this.commandParameters.clear();
        this.commandParameters.put("default",
                new CommandParameter[]{
                        new CommandParameter("player",   false),
                });
        this.setAliases(new String[]{"unban"});
        this.setDescription("unban (player)");
    }

    public boolean execute(CommandSender sender, String s, String[] args) {
        if(!sender.hasPermission("punishment.pardon")){
            sender.sendMessage(TextFormat.RED + "You do not have permission to use this command!");
            return false;
        }
        if(args.length < 1){
            sender.sendMessage(TextFormat.RED + "Correct Usage: /pardon (name)");
            return false;
        }
        String name = args[0].toLowerCase(Locale.ROOT);
        BanList banmanagement = sender.getServer().getNameBans();
        if(banmanagement.isBanned(name.toLowerCase())){
            sender.getServer().broadcastMessage(TextFormat.RED + name + TextFormat.AQUA + " has been unbanned from the server!");
            sender.getServer().getNameBans().remove(name);

            return false;
        }
        sender.sendMessage(TextFormat.RED + name + TextFormat.AQUA + " is not banned!");
        return false;
    }

}
