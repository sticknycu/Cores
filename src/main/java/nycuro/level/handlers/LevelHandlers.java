package nycuro.level.handlers;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByChildEntityEvent;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.level.Location;
import nycuro.API;
import nycuro.database.Database;
import org.itxtech.synapseapi.event.client.SynapseDataPacketSendEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class LevelHandlers implements Listener {

    private List<String> blocked = new ArrayList<>(Arrays.asList("/spawn", "/tp", "/tpa", "/warp"));

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (API.getCombatAPI().inCombat(player)) {
            player.kill();
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        API.getCombatAPI().removeCombat(event.getEntity());
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage().split(" ")[0].toLowerCase();
        String message = "";
        int lang = Database.profileHub.get(player.getUniqueId()).getLanguage();
        if (lang == 0) {
            message = "§cYou cannot use this command during combat";
        } else if (lang == 1) {
            message = "§cNu poti folosi aceasta comanda cand esti in lupta.";
        }
        if (API.getCombatAPI().inCombat(player)) {
            if (blocked.contains(command)) {
                event.setCancelled();
                player.sendMessage(message);
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
            if (entity.namedTag.getBoolean("npc")) {
                damager.teleport(new Location(1053, 69, 1237));
                damager.sendMessage(API.getMessageAPI().sendMobFarmMessage(damager));
            }
        }
    }

    /*@EventHandler
    public void onCreatureSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (API.getMechanicAPI().isOnSpawn(entity) || API.getMechanicAPI().isOnPvP(entity)) {
            event.setCancelled();
        }
    }*/
}
