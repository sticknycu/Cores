package nycuro.jobs.handlers;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.item.Item;
import cn.nukkit.math.Vector3;
import nycuro.API;
import nycuro.api.UtilsAPI;
import nycuro.database.Database;
import nycuro.database.objects.ProfileFactions;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class JobsHandlers implements Listener {
    // TODO: pis pe el job prost :d
    /* Credits: @Nora. Thanks!
       FIX: When adding mobs */
    private void sendToRespawn(Entity entity, Player damager, EntityDamageEvent event) {
        if (!(entity instanceof Player)) return;
        Player player = (Player) entity;
        if (event.getDamage() > player.getHealth() && (damager != null)) {
            for (Item item : player.getInventory().getContents().values()) {
                player.getLevel().dropItem(new Vector3(player.getX(), player.getY() + 1, player.getZ()), item);
            }
            event.setCancelled();
            player.setHealth(20);
            player.getFoodData().setLevel(20);
            player.teleport(player.getServer().getDefaultLevel().getSpawnLocation());
            player.removeAllEffects();
            player.getInventory().clearAll();
            API.getMessageAPI().sendDeadMessage(player, damager);
            ProfileFactions profilePlayer = Database.profileFactions.get(player.getName());
            ProfileFactions profileDamager = Database.profileFactions.get(damager.getName()); // Todo: Zombies, Monsters.
            profilePlayer.setDeaths(profilePlayer.getDeaths() + 1);
            profileDamager.setKills(profileDamager.getKills() + 1);
            UtilsAPI.teleported = false;

        }
        if (player.getPosition().getY() < -3) {
            event.setCancelled();
            player.setHealth(20);
            player.getFoodData().setLevel(20);
            player.teleport(player.getServer().getDefaultLevel().getSpawnLocation());
            player.removeAllEffects();
            player.getInventory().clearAll();
            ProfileFactions profilePlayer = Database.profileFactions.get(player.getName());
            profilePlayer.setDeaths(profilePlayer.getDeaths() + 1);
            UtilsAPI.teleported = false;
        }
    }
}
