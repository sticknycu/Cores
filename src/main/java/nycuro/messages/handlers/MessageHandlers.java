package nycuro.messages.handlers;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.SetLocalPlayerAsInitializedPacket;
import cn.nukkit.player.Player;
import com.nukkitx.protocol.bedrock.packet.SetLocalPlayerAsInitializedPacket;

import static nycuro.api.API.mechanicAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class MessageHandlers implements Listener {

    @EventHandler
    public void onInitialized(DataPacketReceiveEvent event) {
        if (event.getPacket() instanceof SetLocalPlayerAsInitializedPacket) {
            Player player = event.getPlayer();
            mechanicAPI.createBossBar(player);
            mechanicAPI.createScoreboard(player);
        }
    }

    @EventHandler
    public void playerJoinMessages(PlayerJoinEvent event) {
        event.setJoinMessage("");
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        event.setQuitMessage("");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) { ;
        event.setDeathMessage("");
    }
}
