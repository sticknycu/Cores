package nycuro.level.handlers;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.scheduler.Task;
import com.massivecraft.factions.Conf;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.event.FPlayerJoinEvent;
import com.massivecraft.factions.event.FPlayerLeaveEvent;
import nycuro.API;
import nycuro.api.JobsAPI;
import nycuro.database.Database;
import nycuro.database.objects.Profile;
import nycuro.mechanic.handlers.MechanicHandlers;


/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class LevelHandlers implements Listener {

    @EventHandler
    public void onFPlayerJoinFaction(FPlayerJoinEvent event) {
        FPlayer fPlayer = event.getFPlayer();
        Player player = fPlayer.getPlayer();
        Faction faction = event.getFaction();
        Conf.prefixAdmin = "**";
        Conf.prefixMod = "*";
        int level = Database.profile.get(player.getUniqueId()).getLevel();
        int job = Database.profile.get(player.getUniqueId()).getJob();
        if (job == 0) {
            player.setNameTag("§a[§c" + level + "§a] §7" + fPlayer.getRole().getPrefix() + faction.getTag() + " §3" + fPlayer.getName());
        } else {
            player.setNameTag("§7[§e" + JobsAPI.jobs.get(job) + "§7] " + "§a[§c" + level + "§a] §7" + fPlayer.getRole().getPrefix() + faction.getTag() + " §3" + fPlayer.getName());
        }
    }

    /*@EventHandler
    public void onCreatureSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (API.getMechanicAPI().isOnSpawn(entity) || API.getMechanicAPI().isOnPvP(entity)) {
            event.setCancelled();
        }
    }*/

    @EventHandler
    public void onFPlayerLeaveFaction(FPlayerLeaveEvent event) {
        FPlayer fPlayer = event.getFPlayer();
        Player player = fPlayer.getPlayer();
        if (!fPlayer.isOnline()) return;
        int level = Database.profile.get(player.getUniqueId()).getLevel();
        int job = Database.profile.get(player.getUniqueId()).getJob();
        if (job == 0) {
            player.setNameTag("§a[§c" + level + "§a] §7" + player.getName());
        } else {
            player.setNameTag("§7[§e" + JobsAPI.jobs.get(job) + "§7] " + "§a[§c" + level + "§a] §7" + player.getName());
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FPlayer fPlayer = FPlayers.i.get(player);
        Faction faction = fPlayer.getFaction();
        Conf.prefixAdmin = "**";
        Conf.prefixMod = "*";
        int level = 0;
        int job = 0;
        Profile profile = Database.profile.get(player.getUniqueId());
        if (profile != null) {
            level = profile.getLevel();
            job = profile.getJob();
        }
        if (job == 0) {
            if (fPlayer.hasFaction()) {
                player.setNameTag("§a[§c" + level + "§a] §7" + fPlayer.getRole().getPrefix() + faction.getTag() + " §3" + fPlayer.getName());
            } else {
                player.setNameTag("§a[§c" + level + "§a] §7" + player.getName());
            }
        } else {
            if (fPlayer.hasFaction()) {
                player.setNameTag("§7[§e" + JobsAPI.jobs.get(job) + "§7] " + "§a[§c" + level + "§a] §7" + fPlayer.getRole().getPrefix() + faction.getTag() + " §3" + fPlayer.getName());
            } else {
                player.setNameTag("§7[§e" + JobsAPI.jobs.get(job) + "§7] " + "§a[§c" + level + "§a] §7" + player.getName());
            }
        }
    }
}
