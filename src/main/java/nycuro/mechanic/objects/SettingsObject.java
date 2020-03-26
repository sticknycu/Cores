package nycuro.mechanic.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettingsObject {
    public boolean bossbarValue;

    public boolean scoreboardValue;

    public int renderDistance;

    public SettingsObject(boolean bossbarValue, boolean scoreboardValue, int renderDistance) {
        this.bossbarValue = bossbarValue;
        this.scoreboardValue = scoreboardValue;
        this.renderDistance = renderDistance;
    }
}
