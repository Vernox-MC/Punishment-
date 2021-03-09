package VernoxMC.Punishment.Commands;

import VernoxMC.Punishment.Main;
import VernoxMC.Punishment.Utils.Api;
import VernoxMC.Punishment.Utils.MuteList;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.utils.TextFormat;
import java.util.Locale;


public class UnmuteCommand extends PluginCommand<Main> {

    public UnmuteCommand(Main owner) {
        super("unmute", owner);
        this.setPermission("punishment.unmute");
        this.commandParameters.clear();
        this.commandParameters.put("default",
                new CommandParameter[]{
                        new CommandParameter("player",   false)
                });
        this.setDescription("unmute (player)");
    }

    public boolean execute(CommandSender sender, String s, String[] args) {
        if(!sender.hasPermission("punishment.unmute")){
            sender.sendMessage(TextFormat.RED + "You do not have permission to use this command!");
            return false;
        }
        if(args.length < 1){
            sender.sendMessage(TextFormat.RED + "Correct Usage: /unmute (name)");
            return false;
        }
        String name = args[0].toLowerCase(Locale.ROOT);
        MuteList Manager = Api.getNameMutes();
        if(Manager.isBanned(name.toLowerCase())){
            sender.getServer().broadcastMessage(TextFormat.RED + name + TextFormat.AQUA + " has been unmuted from the server!");
            Manager.remove(name);

            return false;
        }

        sender.sendMessage(TextFormat.RED + name + TextFormat.AQUA + " is not muted!");

        return false;
    }

}
