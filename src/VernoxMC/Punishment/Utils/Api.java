package VernoxMC.Punishment.Utils;


public class Api {

    public static MuteList getNameMutes() {
        MuteList mute = new MuteList("muted-players.json");
        mute.load();
        return mute;
    }

}