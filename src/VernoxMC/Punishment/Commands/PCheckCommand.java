package VernoxMC.Punishment.Commands;

import VernoxMC.Punishment.Main;
import VernoxMC.Punishment.Utils.Api;
import VernoxMC.Punishment.Utils.MuteList;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.permission.BanEntry;
import cn.nukkit.permission.BanList;
import cn.nukkit.utils.TextFormat;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;

public class PCheckCommand extends PluginCommand<Main> {
    public PCheckCommand(Main owner) {
        super("pcheck", owner);
        this.commandParameters.clear();
        this.commandParameters.put("default",
                new CommandParameter[]{
                        new CommandParameter("player",   false),
                });
        this.setDescription("/pcheck (name)");
    }

    public boolean execute(CommandSender sender, String s, String[] args) {
    if(args.length < 1){
        sender.sendMessage(TextFormat.GREEN + "You need to include a real username!");
        return false;
    }
    String player = args[0];


          sender.sendMessage(TextFormat.GREEN + "Punishment Checker:");
        sender.sendMessage(TextFormat.AQUA + "User: " + TextFormat.DARK_BLUE +  player);
          BanList banmanagement = sender.getServer().getNameBans();
          if(banmanagement.isBanned(player.toLowerCase())){
        LinkedHashMap<String, BanEntry> getentry = banmanagement.getEntires();
        BanEntry baninfo = getentry.get(player.toLowerCase());
        String reason = baninfo.getReason();
        String mod = baninfo.getSource();
        Date bannedon = baninfo.getCreationDate();
        if(String.valueOf(baninfo.getExpirationDate()) == "null") {
            sender.sendMessage(TextFormat.RED + "Perm Banned: Yes" + "\n" + TextFormat.RED + "Banned By: " + mod + "\n" + TextFormat.RED + "Reason: " + reason + "\n" + TextFormat.RED + "Banned On: " + bannedon);
        } else{
            sender.sendMessage(TextFormat.RED + "Banned: Yes" + "\n" + TextFormat.RED + "Banned By: " + mod + "\n" + TextFormat.RED + "Reason: " + reason + "\n" + TextFormat.RED + "Banned On: " + bannedon +"\n" + TextFormat.RED +"Expires: " + baninfo.getExpirationDate());
        }
    } else{
        sender.sendMessage(TextFormat.AQUA + "Banned: No");
    }
        MuteList mutemanagement = Api.getNameMutes();
          if(mutemanagement.isBanned(player.toLowerCase(Locale.ROOT))){
              LinkedHashMap<String, BanEntry> getentry = mutemanagement.getEntires();
              BanEntry muteinfo = getentry.get(player.toLowerCase());
              String reason = muteinfo.getReason();
              String mod = muteinfo.getSource();
              Date bannedon = muteinfo.getCreationDate();
              if(String.valueOf(muteinfo.getExpirationDate()) == "null") {
                  sender.sendMessage(TextFormat.RED + "Perm Muted: Yes" + "\n" + TextFormat.RED + "Muted By: " + mod + "\n" + TextFormat.RED + "Reason: " + reason + "\n" + TextFormat.RED + "Muted On: " + bannedon);
              } else{
                  sender.sendMessage(TextFormat.RED + "Muted: Yes" + "\n" + TextFormat.RED + "Muted By: " + mod + "\n" + TextFormat.RED + "Reason: " + reason + "\n" + TextFormat.RED + "Muted On: " + bannedon +"\n" + TextFormat.RED + "Expires: " + muteinfo.getExpirationDate());
              }
          }else{
              sender.sendMessage(TextFormat.AQUA + "Muted: No");
          }
          return true;
}
}

