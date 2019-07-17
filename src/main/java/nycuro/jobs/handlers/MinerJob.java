package nycuro.jobs.handlers;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import nycuro.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileFactions;

/**
 * Project: CHPECores
 * Author: Gabitzuu
 */
public class MinerJob implements Listener {
    @EventHandler
    public void onMinerBreak(BlockBreakEvent ev) {
        Player p = ev.getPlayer();
        Block block = ev.getBlock();

        ProfileFactions profile = Database.profileFactions.get(p.getName());
        if(API.getMechanicAPI().isOnSpawn(p) && !(profile.getJob() == 1)) return;

        switch (block.getId()) {
            case Block.COBBLESTONE:
            case Block.STONE:
            case Block.COAL_ORE:
            case Block.IRON_ORE:
            case Block.GOLD_ORE:
            case Block.DIAMOND_ORE:
            case Block.LAPIS_ORE:
            case Block.REDSTONE_ORE:
                profile.setDollars(profile.getDollars() + 2.5);
                profile.setExperience(profile.getExperience() + 2.0);
                p.sendMessage("spart blocuri la miner job");
                break;
        }

    }
}
