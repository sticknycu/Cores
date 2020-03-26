package nycuro.kits.handlers;


import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.player.Player;
import nycuro.kits.type.NameKit;

import static nycuro.api.API.databaseAPI;
import static nycuro.api.API.kitsAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class KitHandlers implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        databaseAPI.playerExist(player.getName(), (bool) -> {
            if (!bool) {
                kitsAPI.kits.get(NameKit.STARTER).sendKit(player);
            }
        });
    }
}
