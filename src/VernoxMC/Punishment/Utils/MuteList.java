package VernoxMC.Punishment.Utils;

import cn.nukkit.permission.BanEntry;
import cn.nukkit.permission.BanList;

import java.util.Date;

public class MuteList extends BanList {

    public MuteList(String file) {
        super(file);
    }

    public void addd(BanEntry entry) {

        if (entry instanceof MuteEntry) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        add(entry);
    }

    public MuteEntry addMute(String target, String reason, Date expires, String source){
        MuteEntry entry = new MuteEntry(target);
        entry.setReason(reason != null ? reason : entry.getReason());
        entry.setExpirationDate(expires);
        entry.setSource(source != null ? source : entry.getSource());
        addBan(entry.getName(), entry.getReason(), entry.getExpirationDate(), entry.getSource());
        return entry;
    }
}