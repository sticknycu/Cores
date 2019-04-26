package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.potion.Effect;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.DummyBossBar;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import nycuro.API;
import nycuro.database.Database;

import java.util.Map;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class CombatLoggerTask extends Task {

    private Object2IntMap<String> k = new Object2IntOpenHashMap<>();

    @Override
    public void onRun(int i) {
        for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            if (API.getMechanicAPI().isOnSpawn(player)) {
                if (!API.getMainAPI().isOnMobFarm.getBoolean(player)) {
                    Effect effect = Effect.getEffect(Effect.SPEED);
                    effect.setAmplifier(1);
                    effect.setDuration(20 * 3);
                    player.addEffect(effect);
                }
            }
        }
        API.getCombatAPI().inCombat.forEach((player, time) -> {
            if (k.getOrDefault(player.getName(), -1) == -1) k.put(player.getName(), 13);
            long count = k.getInt(player.getName());
            float procent = (float) ((int) (count * 100 / 13));
            Map<Long, DummyBossBar> dummyBossBar = player.getDummyBossBars();
            dummyBossBar.forEach(
                    (lb, db) -> {
                        db.setText("§7-§8=§7- §7CombatLogger: §6§l" + k.getInt(player.getName()) + " §7-§8=§7-");
                        if (k.getInt(player.getName()) <= 1) {
                            db.setLength(1F);
                        } else {
                            db.setLength(procent);
                        }
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
                                db.setLength(100F);
                            }
                        }
                        k.replace(player.getName(), k.getInt(player.getName()) - 1);
                    }
            );
        });
    }
}
