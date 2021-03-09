package VernoxMC.Punishment;

import VernoxMC.Punishment.Commands.*;
import cn.nukkit.command.CommandMap;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import VernoxMC.Punishment.Listeners.*;
    public class Main extends PluginBase {




        public void onEnable() {

            this.getLogger().info(TextFormat.GREEN + "Punishment Enabled");
            this.plugin();

        }

        public void onDisable() {
            this.getLogger().info(TextFormat.RED + "Punishment Disabled!");
        }


        private void plugin() {

            CommandMap map = this.getServer().getCommandMap();
            map.register("Punishment", new BanCommand(this));
            map.register("Punishment", new TBanCommand(this));
            map.register("Punishment", new MuteCommand(this));
            map.register("Punishment", new TMuteCommand(this));
            map.register("Punishment", new PardonCommand(this));
            map.register("Punishment", new UnmuteCommand(this));
            map.register("Punishment", new KickCommand(this));
            map.register("Punishment", new PCheckCommand(this));
            map.register("Punishment", new IPBan(this));
            map.register("Punishment", new Pardonip(this));
            this.getServer().getPluginManager().registerEvents(new Chat(this), this);
            this.getServer().getPluginManager().registerEvents(new Join(), this);
        }

    }