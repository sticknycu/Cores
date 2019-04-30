package nycuro.level.handlers;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.projectile.EntityArrow;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.ProjectileHitEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.TextFormat;
import nycuro.API;
import nycuro.gui.list.ResponseFormWindow;

import java.util.Map;
import java.util.function.Consumer;


/**
 * author: NycuRO
 * HubCore Project
 * API 1.0.0
 */
public class LevelHandlers implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        API.getMainAPI().getServer().getScheduler().scheduleDelayedTask(new Task() {
            @Override
            public void onRun(int i) {
                for (int k = 0; k <= 5; k++) {
                    API.getMechanicAPI().spawnFirework(new Vector3(4985, 4, 5026));
                    API.getMechanicAPI().spawnFirework(new Vector3(5009, 4, 5025));
                }
            }
        }, 20 * 6, true);
        //player.setNameTag("§7[§e" + JobsAPI.jobs.get(job) + "§7] " + "§a[§c" + level + "§a] §7" + player.getName());
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getAction() == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK || event.getAction() == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {
            Player player = event.getPlayer();
            PlayerInventory playerInventory = player.getInventory();
            int id = player.getInventory().getItemInHand().getId();
            int meta = player.getInventory().getItemInHand().getDamage();
            switch (id) {
                case Item.COMPASS:
                    sendFormServers(player);
                    break;
                case Item.DYE:
                    int itemMeta = 8;
                    switch (meta) {
                        case 8:
                            for (Player players : API.getMainAPI().getServer().getOnlinePlayers().values()) {
                                player.hidePlayer(players);
                            }
                            API.getMessageAPI().sendHidePlayersMessage(player);
                            itemMeta = 10;
                            break;
                        case 10:
                            for (Player players : API.getMainAPI().getServer().getOnlinePlayers().values()) {
                                player.showPlayer(players);
                            }
                            API.getMessageAPI().sendShowPlayersMessage(player);
                            itemMeta = 8;
                            break;
                    }
                    Item DYE = Item.get(Item.DYE, itemMeta, 1);
                    switch (itemMeta) {
                        case 8:
                            DYE.setCustomName(API.getMessageAPI().getDyeStageOneMessage(player));
                            break;
                        case 10:
                            DYE.setCustomName(API.getMessageAPI().getDyeStageTwoMessage(player));
                            break;
                    }
                    playerInventory.setItem(0, DYE);
                    break;
                case Item.FEATHER:
                    player.setMotion(new Vector3(0, 1 ,0));
                    break;
                case Item.NETHER_STAR:
                    sendFormRank(player);
                    break;

            }
        }
    }

    @EventHandler
    public void onHitProjectile(ProjectileHitEvent event) {
        Entity arrow = event.getEntity();
        if (arrow instanceof EntityArrow) {
            if (arrow.isCollided) {
                Entity player = ((EntityArrow) arrow).shootingEntity;
                if (player instanceof Player) {
                    double x = arrow.getX();
                    double y = arrow.getY();
                    double z = arrow.getZ();
                    player.teleport(new Location(x, y, z));
                }
            }
        }
    }

    private void sendFormServers(Player player) {
        FormWindowSimple utilsMenu = new FormWindowSimple(API.getMessageAPI().getTitleFormServers(player), API.getMessageAPI().getContentFormServers(player));
        utilsMenu.addButton(new ElementButton("SkyPvP", new ElementButtonImageData("url", "https://i.imgur.com/9xDeDwG.png")));
        utilsMenu.addButton(new ElementButton("Factions", new ElementButtonImageData("url", "https://i.imgur.com/9xDeDwG.png")));
        utilsMenu.addButton(new ElementButton("SkyBlock", new ElementButtonImageData("url", "https://i.imgur.com/9xDeDwG.png")));
        utilsMenu.addButton(new ElementButton("Close"));
        player.showFormWindow(new ResponseFormWindow(utilsMenu, new Consumer<Map<Integer,Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            //API.getMainAPI().getServer().dispatchCommand(player, "server skypvp");
                            return;
                        case 1:
                            player.sendMessage(TextFormat.GREEN + "Momentan foloseste /server factions");
                            return;
                        case 3:
                            //API.getMainAPI().getServer().dispatchCommand(player, "server skyblock");
                            return;
                        case 4:
                            break;
                    }
                }
            }
        }));
    }

    public static void sendFormRank(Player player) {
        FormWindowSimple utilsMenu = new FormWindowSimple(API.getMessageAPI().getTitleFormRanks(player), API.getMessageAPI().getContentFormRanks(player));
        utilsMenu.addButton(new ElementButton("Grad 1", new ElementButtonImageData("url", "https://i.imgur.com/UdZm6QB.png")));
        utilsMenu.addButton(new ElementButton("Grad 2", new ElementButtonImageData("url", "https://i.imgur.com/GuxFWI6.png")));
        utilsMenu.addButton(new ElementButton("Close"));
        player.showFormWindow(new ResponseFormWindow(utilsMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            // ..
                            return;
                        case 1:
                            //getWarpUtilsAPI().sendWarptOptionOfUtils(player);
                            return;
                        case 2:
                            break;
                    }
                }
            }
        }));
    }
}
