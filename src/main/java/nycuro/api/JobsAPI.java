package nycuro.api;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import nycuro.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileFactions;
import nycuro.gui.list.ResponseFormWindow;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class JobsAPI {
    public static Object2ObjectMap<Integer, String> jobs = new Object2ObjectArrayMap<>();
    private static HashMap<Player, HashMap<Integer, Integer>> minerObjects = new HashMap<>();
    private static Random randomGenerator = new Random();
    static {
        jobs.put(0, "");
        jobs.put(1, "Miner");
        jobs.put(2, "Butcher");
        jobs.put(3, "Farmer");
        jobs.put(4, "Fisherman");
    }

    private void sendInfoMessageJobs(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom("Info Job");
        infoMenu.addElement(new ElementLabel(API.getMessageAPI().sendInfoMessageJobs(player)));
        player.showFormWindow(infoMenu);
    }

    public void getJob(Player player) {
        ProfileFactions profile = Database.profileFactions.get(player.getName());
        FormWindowSimple jobsMenu = new FormWindowSimple("Job", API.getMessageAPI().sendJobPrincipalModal(player));
        jobsMenu.addButton(new ElementButton("Miner", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        jobsMenu.addButton(new ElementButton("Butcher", new ElementButtonImageData("url", "https://i.imgur.com/XFCYdCz.png")));
        jobsMenu.addButton(new ElementButton("Farmer", new ElementButtonImageData("url", "https://i.imgur.com/otMDlEU.png")));
        jobsMenu.addButton(new ElementButton("Fisherman", new ElementButtonImageData("url", "https://i.imgur.com/YHkAa4q.png")));
        jobsMenu.addButton(new ElementButton("Info", new ElementButtonImageData("url", "https://i.imgur.com/nujWKR3.png")));
        jobsMenu.addButton(new ElementButton("Without Job", new ElementButtonImageData("url", "https://i.imgur.com/YXBNPBc.png")));
        jobsMenu.addButton(new ElementButton("Close"));
        player.showFormWindow(new ResponseFormWindow(jobsMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            profile.setJob(1);
                            API.getMessageAPI().sendReceiveJobMessage(player, "Miner");
                            return;
                        case 1:
                            profile.setJob(2);
                            API.getMessageAPI().sendReceiveJobMessage(player, "Butcher");
                            return;
                        case 2:
                            profile.setJob(3);
                            API.getMessageAPI().sendReceiveJobMessage(player, "Farmer");
                            return;
                        case 3:
                            profile.setJob(4);
                            API.getMessageAPI().sendReceiveJobMessage(player, "Fisherman");
                        case 4:
                            sendInfoMessageJobs(player);
                            return;
                        case 5:
                            profile.setJob(0);
                            API.getMessageAPI().sendWithoutJobMessage(player);
                            return;
                        case 6:
                            break;
                    }
                }
            }
        }));
    }

    public int getOreType() {
        return randomGenerator.nextInt(4);
    }

    public HashMap<Player, HashMap<Integer, Integer>> getMinerObjects(Player player) {
        HashMap<Integer, Integer> requiredOres = new HashMap<>();

        for (int i = 0; i < 3; i++) {

        }

        minerObjects.put(player, requiredOres);
        return minerObjects;
    }
}
