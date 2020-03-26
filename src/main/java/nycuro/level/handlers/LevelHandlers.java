package nycuro.level.handlers;


import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByChildEntityEvent;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static nycuro.api.API.combatAPI;
import static nycuro.api.API.messageAPI;
import static nycuro.api.API.mechanicAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class LevelHandlers implements Listener {

    private List<String> blocked = new ArrayList<>(Arrays.asList("/tp", "/tpa", "/warp"));

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (combatAPI.inCombat(player)) {
            player.kill();
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        combatAPI.removeCombat(event.getEntity());
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage().split(" ")[0].toLowerCase();
        if (combatAPI.inCombat(player)) {
            if (blocked.contains(command) || command.equals("/spawn")) {
                event.setCancelled();
                player.sendMessage(messageAPI.messagesObject.translateMessage("combat.block"));
            }
        }
        if (mechanicAPI.isOnArena(player)) {
            if (blocked.contains(command)) {
                event.setCancelled();
                player.sendMessage(messageAPI.messagesObject.translateMessage("combat.block"));
            }
        }
    }

    @EventHandler
    public void onHurt(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity == null) return;
        Player damager = null;
        if (event instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent ev = (EntityDamageByEntityEvent) event;
            if (ev instanceof EntityDamageByChildEntityEvent) {
                EntityDamageByChildEntityEvent evc = (EntityDamageByChildEntityEvent) ev;
                if (evc.getDamager() instanceof Player) damager = (Player) evc.getDamager();
            } else if (ev.getDamager() instanceof Player) damager = (Player) ev.getDamager();
            if (damager == null) return;

            if (entity instanceof Player) {
                for (Player pl : new Player[] { (Player) entity, damager }) {
                    if (mechanicAPI.isOnSpawn(pl)) break;
                    combatAPI.setCombat(pl);
                }
            }
        }
    }
}
