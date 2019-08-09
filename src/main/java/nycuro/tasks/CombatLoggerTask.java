package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.potion.Effect;
import cn.nukkit.scheduler.Task;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

import java.util.UUID;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;
import static nycuro.api.API.mechanicAPI;
import static nycuro.api.API.combatAPI;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class CombatLoggerTask extends Task {

    private Object2IntMap<UUID> k = new Object2IntOpenHashMap<>();

    @Override
    public void onRun(int i) {
        for (Player player : mainAPI.getServer().getOnlinePlayers().values()) {
            if (mechanicAPI.isOnSpawn(player)) {
                Effect effect = Effect.getEffect(Effect.SPEED);
                effect.setAmplifier(1);
                effect.setDuration(20 * 3);
                effect.setVisible(false);
                player.addEffect(effect);
            }
        }
        combatAPI.inCombat.forEach((uuid, time) -> {
            if (k.getOrDefault(uuid, -1) == -1) k.put(uuid, 13);
            long count = k.getInt(uuid);
            float procent = (float) ((int) (count * 100 / 13));
            if (mainAPI.bossbar.get(uuid) != null) {
                mainAPI.bossbar.get(uuid).setText(messageAPI.messagesObject.translateMessage("combat.bossbar", mainAPI.emptyNoSpace + k.getInt(uuid)));
                if (k.getInt(uuid) <= 1) mainAPI.bossbar.get(uuid).setLength(1F);
                else mainAPI.bossbar.get(uuid).setLength(procent);
            }
            if (k.getInt(uuid) == 0) {
                mainAPI.getServer().getPlayer(uuid).ifPresent( (player) -> {
                    player.sendMessage(messageAPI.messagesObject.translateMessage("combat.exit"));
                    combatAPI.removeCombat(player);
                    k.removeInt(player.getUniqueId());
                    if (mainAPI.bossbar.get(uuid) != null) {
                        mainAPI.bossbar.get(player.getUniqueId()).setLength(100F);
                    }
                });
            }
            k.replace(uuid, k.getInt(uuid) - 1);
        });
    }
}
