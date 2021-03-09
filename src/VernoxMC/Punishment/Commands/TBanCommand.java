package VernoxMC.Punishment.Commands;
import VernoxMC.Punishment.Main;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.permission.BanList;
import cn.nukkit.utils.TextFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TBanCommand extends PluginCommand<Main> {

    public TBanCommand(Main owner) {
        super("tban", owner);
        this.setPermission("punishment.tban");
        this.commandParameters.clear();
        this.commandParameters.put("default",
                new CommandParameter[]{
                        new CommandParameter("player",   false),
                        new CommandParameter("reason", true)
                });
        this.setAliases(new String[]{"tempban"});
        this.setDescription("tban (player) (time) (reason)");
    }
    public static String extractNumber(final String str) {

        if(str == null || str.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for(char c : str.toCharArray()){
            if(Character.isDigit(c)){
                sb.append(c);
                found = true;
            } else if(found){
                break;
            }
        }

        return sb.toString();
    }
    public static Date add(int str, int time){
        Calendar cal = Calendar.getInstance();
        cal.add(time, str);
        Date newDate = cal.getTime();
        return newDate;
    }
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(!sender.hasPermission("punishment.tban")){
            sender.sendMessage(TextFormat.RED + "You do not have permission to use this command!");
            return false;
        }
        if(args.length < 2){
            sender.sendMessage(TextFormat.AQUA + "Correct usage: /tban (name) (time) (reason)");
            return false;
        }
        String name = args[0];
        String reason = "";
        for (int i = 2; i < args.length; i++) {
            reason += args[i] + " ";
        }
        if (reason.length() < 1) {
            sender.sendMessage(TextFormat.RED + "You must have a valid reason to ban!");
            return false;
        }
if(args[1].isEmpty()){
    sender.sendMessage(TextFormat.RED + "You must have a valid time to ban!");
    return false;
}
        reason = reason.substring(0, reason.length() - 1);

     if (args[1].toLowerCase(Locale.ROOT).contains("d") || args[1].toLowerCase(Locale.ROOT).contains("h") || args[1].toLowerCase(Locale.ROOT).contains("m") || args[1].toLowerCase(Locale.ROOT).contains("min")) {
            String timeString = args[1];
         Date finaltime = new Date();
            try {
                int time = Integer.parseInt(extractNumber(args[1]));
                int timetotal = 0;
                if (timeString.toLowerCase(Locale.ROOT).contains("d")) {
                    timetotal = time;
                    finaltime = add(timetotal, Calendar.DATE);
                }
                if (timeString.toLowerCase(Locale.ROOT).contains("h")){
                    timetotal = time;
                    finaltime = add(timetotal, Calendar.HOUR);
                }
                if (timeString.toLowerCase(Locale.ROOT).contains("y")){
                    timetotal = time;
                    finaltime = add(timetotal, Calendar.YEAR);
                }
                if (timeString.toLowerCase(Locale.ROOT).contains("min")){
                    timetotal = time;
                    finaltime = add(timetotal, Calendar.MINUTE);
                }
                if (timeString.toLowerCase(Locale.ROOT).contains("m")){
                    timetotal = time;
                    finaltime = add(timetotal, Calendar.MONTH);
                }

                Player player = sender.getServer().getPlayerExact(name);
                BanList banmanagement = sender.getServer().getNameBans();
                if(player != null) {
                    if(banmanagement.isBanned(player.getName().toLowerCase(Locale.ROOT))){
                        sender.sendMessage(TextFormat.RED + player.getName() + TextFormat.AQUA + " is already banned!");
                        return false;
                    }
                    sender.getServer().getNameBans().addBan(player.getName().toLowerCase(Locale.ROOT), reason, finaltime, sender.getName());
                    player.kick(TextFormat.AQUA + "You have been Banned from the Server"+ "\n" + TextFormat.RED + "Reason: " + reason);
                    sender.getServer().broadcastMessage(TextFormat.RED + player.getName() + TextFormat.AQUA + " has been banned from the server until " + finaltime);
                    return false;
                }
                if(banmanagement.isBanned(name.toLowerCase(Locale.ROOT))){
                        sender.sendMessage(TextFormat.RED + name + TextFormat.AQUA + " is already banned!");
                    return false;
                }
                sender.getServer().getNameBans().addBan(name.toLowerCase(Locale.ROOT), reason, finaltime, sender.getName());
                sender.getServer().broadcastMessage(TextFormat.RED + name + TextFormat.AQUA + " has been banned from the server until " + finaltime);

            }catch (NumberFormatException exception) {
                sender.sendMessage(TextFormat.AQUA + "Correct usage: /tban (name)(time)(d/h) (reason)");
            }
 } else{
     sender.sendMessage(TextFormat.AQUA + "Correct usage: /tban (name) (time)(d/h) (reason)");
   return false;
  }


        return false;
    }

}