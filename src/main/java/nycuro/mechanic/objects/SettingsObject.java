package nycuro.mechanic.objects;

import lombok.Getter;
import lombok.Setter;

public class SettingsObject {

    @Getter
    @Setter
    public boolean bossbarValue;

    @Getter
    @Setter
    public boolean scoreboardValue;

    @Getter
    @Setter
    public int renderDistance;

    public SettingsObject(boolean bossbarValue, boolean scoreboardValue, boolean popupValue, int renderDistance) {
        this.bossbarValue = bossbarValue;
        this.scoreboardValue = scoreboardValue;
        this.renderDistance = renderDistance;
    }
}
