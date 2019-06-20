package nycuro.api;

import cn.nukkit.Player;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import nycuro.API;

import java.util.Iterator;

public class CombatAPI {

    public Object2LongMap<Player> inCombat = new Object2LongOpenHashMap<>();

    public boolean inCombat(Player player) {
        return inCombat.containsKey(player);
    }

    public void removeCombat(Player player) {
        for(Iterator iterator = inCombat.keySet().iterator(); iterator.hasNext();) {
            Player next = (Player) iterator.next();
            if(!next.equals(player)) continue;
            iterator.remove();
        }
    }

    public void setCombat(Player player) {
        if (!inCombat(player)) {
            player.sendMessage(API.getMessageAPI().setCombatMessage(player));
            inCombat.put(player, System.currentTimeMillis());
        }
    }
}
