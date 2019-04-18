package nycuro.tasks;

import cn.nukkit.scheduler.Task;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import nycuro.API;
import nycuro.database.Database;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class CombatLoggerTask extends Task {

    private Object2IntMap<String> k = new Object2IntOpenHashMap<>();

    @Override
    public void onRun(int i) {
        API.getCombatAPI().inCombat.forEach((player, time) -> {
            if (k.getOrDefault(player.getName(), -1) == -1) k.put(player.getName(), 13);
            long count = k.getInt(player.getName());
            float procent = (float) ((int) (count * 100 / 13));
            API.getMainAPI().bossbar.get(player.getName()).setText("§7-§8=§7- §7CombatLogger: §6§l" + k.getInt(player.getName()) + " §7-§8=§7-");
            if (k.getInt(player.getName()) <= 1) API.getMainAPI().bossbar.get(player.getName()).setLength(1F);
            else API.getMainAPI().bossbar.get(player.getName()).setLength(procent);
            if (k.getInt(player.getName()) == 0) {
                if (player.isOnline()) {
                    String message = "";
                    int lang = Database.profileHub.get(player.getUniqueId()).getLanguage();
                    if (lang == 0) {
                        message = "§aYou are no longer in combat, you may now logout and run commands.";
                    } else if (lang == 1) {
                        message = "§aDe acum nu mai esti in lupta. Acum poti sa te deloghezi si sa folosesti comenzile.";
                    }
                    player.sendMessage(message);
                    API.getCombatAPI().removeCombat(player);
                    k.removeInt(player.getName());
                    API.getMainAPI().bossbar.get(player.getName()).setLength(100F);
                }
            }
            k.replace(player.getName(), k.getInt(player.getName()) - 1);
        });
    }
}
