package nycuro.utils;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.level.Location;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import nycuro.API;
import nycuro.gui.list.ResponseFormWindow;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class WarpUtils {

    private static final Object2IntMap<String> type = new Object2IntOpenHashMap<>();

    static {
        type.put("PvP", 1);
        type.put("Mob Farm", 2);
        type.put("Crate Key", 3);
    }

    private void sendFormWindowCustomWithUtilsList(Player player, List<String> list) {
        FormWindowCustom warpMenu = new FormWindowCustom("Selecteaza Warp-ul la care vrei sa te teleportezi");
        warpMenu.addElement(new ElementLabel("Selecteaza Warp-ul"));
        warpMenu.addElement(new ElementDropdown("Warp-uri Disponibile", list, 0));
        sendFormWindowWarp(player, warpMenu);
    }

    public void sendWarptOptionOfUtils(Player player) {
        List<String> list = Arrays.asList("PvP", "Mob Farm", "Crate Key");
        sendFormWindowCustomWithUtilsList(player, list);
    }

    private void teleportToWarp(Player player, String firstDropDownType, int types) {
        API.getMessageAPI().sendTeleportWarpMessage(player, firstDropDownType);
        switch (types) {
            case 1:
                player.teleport(new Location(API.getMainAPI().getServer().getLevelByName("pvp").getSpawnLocation().getX(), API.getMainAPI().getServer().getLevelByName("pvp").getSpawnLocation().getY(), API.getMainAPI().getServer().getLevelByName("pvp").getSpawnLocation().getZ(), API.getMainAPI().getServer().getLevelByName("pvp")));
                break;
            case 2:
                player.teleport(new Location(121, 73, 56, API.getMainAPI().getServer().getDefaultLevel()));
                break;
            case 3:
                player.teleport(new Location(105, 74, 56, API.getMainAPI().getServer().getDefaultLevel()));
                break;
        }
    }

    private void sendFormWindowWarp(Player player, FormWindowCustom warpMenu) {
        player.showFormWindow(new ResponseFormWindow(warpMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                Iterator it = response.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    it.remove();
                    String firstDropDownType = pair.getValue().toString();
                    int types = type.getInt(firstDropDownType);
                    teleportToWarp(player, firstDropDownType, types);
                }
            }
        }));
    }
}
