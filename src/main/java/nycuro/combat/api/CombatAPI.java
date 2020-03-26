package nycuro.combat.api;

import cn.nukkit.player.Player;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;

import java.util.Iterator;
import java.util.UUID;

import static nycuro.api.API.messageAPI;

public class CombatAPI {

    public Object2LongMap<UUID> inCombat = new Object2LongOpenHashMap<>();

    public boolean inCombat(Player player) {
        return inCombat.containsKey(player.getServerId());
    }

    public void removeCombat(Player player) {
        for(Iterator iterator = inCombat.keySet().iterator(); iterator.hasNext();) {
            UUID next = (UUID) iterator.next();
            if(!next.equals(player.getServerId())) continue;
            iterator.remove();
        }
    }

    public void setCombat(Player player) {
        if (!inCombat(player)) {
            player.sendMessage(messageAPI.messagesObject.translateMessage("combat.set"));
            inCombat.put(player.getServerId(), System.currentTimeMillis());
        }
    }
}
