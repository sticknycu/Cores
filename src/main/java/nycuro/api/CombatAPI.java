package nycuro.api;

import cn.nukkit.Player;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import nycuro.database.Database;

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
        String messageCombat = "";
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        if (!inCombat(player)) {
            if (lang == 0) {
                messageCombat = "§eYou have entered combat, logging out now will cause your death. Please wait §613 §eseconds.";
            } else if (lang == 1) {
                messageCombat = "§eTocmai ai intrat in lupta. Daca te vei deloga, vei muri. Te rog asteapta §613 §esecunde.";
            }
            player.sendMessage(messageCombat);
            inCombat.put(player, System.currentTimeMillis());
        }
    }
}
