package VernoxMC.Punishment.Utils;

import cn.nukkit.permission.BanEntry;

 class MuteEntry extends BanEntry {

     public MuteEntry(String name) {
         super(name);
         this.setCreationDate(null);
        this.setSource("Unknown");
         this.setExpirationDate(null);
        this.setReason("Banned by opererator");
     }

 }
