package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.potion.Effect;
import cn.nukkit.scheduler.Task;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import nycuro.API;

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
            API.getMainAPI().bossbar.get(player.getName()).setText("§7-§8=§7- §7CombatLogger: §6§l" + k.getInt(player.getName()) + " §7-§8=§7-");
            if (k.getInt(player.getName()) <= 1) API.getMainAPI().bossbar.get(player.getName()).setLength(1F);
            else API.getMainAPI().bossbar.get(player.getName()).setLength(procent);
            if (k.getInt(player.getName()) == 0) {
                if (player.isOnline()) {
                    player.sendMessage(API.getMessageAPI().getMessageCombatLogger(player));
                    API.getCombatAPI().removeCombat(player);
                    k.removeInt(player.getName());
                    API.getMainAPI().bossbar.get(player.getName()).setLength(100F);
                }
            }
            k.replace(player.getName(), k.getInt(player.getName()) - 1);
        });
    }
}