package nycuro.kits.handlers;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import nycuro.api.API;
import nycuro.kits.type.NameKit;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class KitHandlers implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            API.getKitsAPI().kits.get(NameKit.STARTER).sendKit(player);
        }
    }
}
